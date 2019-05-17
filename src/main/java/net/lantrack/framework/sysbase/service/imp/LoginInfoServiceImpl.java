package net.lantrack.framework.sysbase.service.imp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.StrUtil;
import net.lantrack.framework.security.session.SessionDAO;
import net.lantrack.framework.security.web.ValidateCodeServletImg;
import net.lantrack.framework.shiro.PhoneToken;
import net.lantrack.framework.shiro.UsernamePasswordToken;
import net.lantrack.framework.sms.tencent.TencentSmsUtil;
import net.lantrack.framework.sysbase.dao.LoginInfoMapper;
import net.lantrack.framework.sysbase.enm.SysConfigEnum;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.entity.LoginInfoExample;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.entity.LoginInfoExample.Criteria;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.service.SysConfigService;
import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.util.UserUtil;

@Service
public class LoginInfoServiceImpl extends BaseService implements LoginInfoService {

	@Autowired
	private LoginInfoMapper loginInfoMapper;

	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private SysUserService userService;

	@Override
	public LoginInfo getInfo(String loginName) {
		try {
			LoginInfoExample example = new LoginInfoExample();
			Criteria cr = example.createCriteria();
			cr.andLoginNameEqualTo(loginName);
			List<LoginInfo> list = this.loginInfoMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				LoginInfo loginInfo = list.get(0);
				return loginInfo;
			} else {
				LoginInfo info = new LoginInfo();
				info.setLoginName(loginName);
				insertInfo(info);
				return info;
			}
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void insertInfo(LoginInfo info) {
		try {
			this.loginInfoMapper.insert(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateInfo(LoginInfo info) {
		LoginInfoExample example = new LoginInfoExample();
		Criteria cr = example.createCriteria();
		cr.andLoginNameEqualTo(info.getLoginName());
		try {
			this.loginInfoMapper.updateByExample(info, example);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getMsgCodeByPhone(String phone) {
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("手机号不能为空");
		}
		try {
			SysUser user = UserUtil.getByPhone(phone);
			if (user == null) {
				throw new ServiceException("该手机号暂未绑定");
			}
			// 生成6位随机验证码
			int code = new Random().nextInt(999999);
			if (code < 100000) {
				code += 100000;
			}
			// 发送该验证码
			TencentSmsUtil.smsSingleSenderMsgCode(phone, code + "");
			Date sendDate = new Date();
			SysConfig smsMinut = sysConfigService.getConfigByName(SysConfigEnum.SMSMINUT.getConfName());
			Date endDate = DateUtil.addMinutes(sendDate, Integer.valueOf(smsMinut.getConfValue()));
			LoginInfo info = getInfo(user.getLoginName());
			info.setSendmsgTime(sendDate);
			info.setEndmsgTime(endDate);
			info.setMsgCode(code + "");
			updateInfo(info);
		} catch (Exception e) {
			throw printException(e);
		}

	}

	@Override
	public Map<String, Object> validMsgCodeToLogin(String code, String phone,String deviceType) {
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
			throw new ServiceException("手机号和验证码不能为空");
		}
		try {
			SysUser user = UserUtil.getByPhone(phone);
			if (user == null) {
				throw new ServiceException("该手机号暂未绑定");
			}
			//校验当前用户状态
			this.validUserStatus(user.getLoginName());
			//获取当前用户登录信息，校验验证码时效性
			LoginInfo info = getInfo(user.getLoginName());
			Date endmsgTime = info.getEndmsgTime();
			if (new Date().before(endmsgTime)) {
				String msgCode = info.getMsgCode();
				if(code.equals(msgCode)) {
					Map<String, Object> map = new HashMap<String, Object>();
					//认证
					excuteLoginByPhoneMsgCode(user.getLoginName(), phone, msgCode, deviceType.toLowerCase());
					String lastSession = null;
					//看短信验证码登录的是PC还是APP
					if("app".equals(deviceType.toLowerCase())) {
						lastSession = info.getStand1();
						info.setStand1(UserUtil.getSession().getId().toString());
					}
					if("pc".equals(deviceType.toLowerCase())) {
						lastSession = info.getStand2();
						info.setStand2(UserUtil.getSession().getId().toString());
					}
					this.deleteSession(lastSession);
					map.put("currentUser", user.getLoginName());
					map.put("sn", UserUtil.getSession().getId());
					map.put("userId", user.getId());
					info.setLastSucTime(new Date());
					info.setLastDevice(deviceType);
					//验证码验证成功后清空
					info.setMsgCode("");
					updateInfo(info);
					return map;
				}else {
					throw new ServiceException("验证码错误");
				}
			}else {
				throw new ServiceException("验证码失效，请重新发送");
			}
		} catch (Exception e) {
			throw printException(e);
		}

	}

	//二维码登录状态
	private static final String SCAN_STATUS = "scanStatus";
	//存在当前Session的用户信息
	private static final String USER_INFO = "loginName";
	//查看当前用户是否点击过确认操作
	private static final String HAS_SUBMIT = "has_submit";

	@Override
	public String getAppScanCode() {
		try {
			Session session = UserUtil.getSession();
			if(session!=null) {
				Serializable id = session.getId();
				session.setAttribute(SCAN_STATUS, "0");
				return id+"";
			}
			return null;
		} catch (Exception e) {
			throw printException(e);
		}

	}

	
	
	@Override
	public void allowAppScanCode(String loginName,String authCode) {
		try {
			if(StringUtils.isBlank(loginName)||StringUtils.isBlank(authCode)) {
				throw new ServiceException("请求参数异常");
			}
			Session session = sessionDAO.readSession(authCode);
			if(session!=null) {
				Object status = session.getAttribute(SCAN_STATUS);
				session.setAttribute(HAS_SUBMIT, "yes");
				if("0".equals(status)) {
					session.setAttribute(SCAN_STATUS, "1");
					session.setAttribute(USER_INFO, loginName);
				}else {
					throw new ServiceException("登录失败，请刷新二维码后再试！");
				}
			}else {
				throw new ServiceException("登录失败，请刷新二维码后再试！");
			}
		} catch (Exception e) {
			throw printException(e);
		}
	}

	/**
	 * 校验用户禁用状态
	 * @param loginName
	 * @throws Exception
	 * @date 2019年1月21日
	 */
	@Override
	public void validUserStatus(String loginName) {
		//校验当前账户是否被禁用
		LoginInfo loginInfo = this.getInfo(loginName);
		if("1".equals(loginInfo.getIfForbidden())) {
			System.err.println(loginName+"被禁用");
			throw new DisabledAccountException("账户被禁用");
		}
		//校验账户是否被锁定
		if("1".equals(loginInfo.getIfLock())) {
			Date lockEndTime = loginInfo.getLockEndTime();
			Date currentDate = new Date();
			if(currentDate.before(lockEndTime)) {
				String betweenDate = DateUtil.getBetweenDate(lockEndTime, currentDate);
				System.err.println(loginName+"被锁定,剩余解锁时间："+betweenDate);
				throw new LockedAccountException("账户被锁定,剩余解锁时间："+betweenDate);
			}else {
				loginInfo.errCount(0);
				loginInfo.setIfLock("0");
				loginInfo.setLockEndTime(null);
				loginInfo.setLockStartTime(null);
				this.updateInfo(loginInfo);
			}
		}
	}

	@Override
	public Map<String, Object> appLogin(HttpServletRequest req,String username, String password, String deviceType) {
		Map<String, Object> resultMap = new HashMap<>();
		SysUser user =  new SysUser();
		
		try {
			//登录方式：1username为登录名时
		    user = UserUtil.getByLoginName(username);
			if(user == null) {
				//2 username为电话号时
				user = UserUtil.getByPhone(username);
				if(user == null) {
				  throw new ServiceException("账户不存在");
				}
				username = user.getLoginName();
			}
			//校验用户状态信息
			validUserStatus(username);
			//当前账户登录状态信息
			LoginInfo loginInfo = this.getInfo(username);
			//校验账户名密码是否匹配
			if(SysUserServiceImp.validatePassword(password,user.getPassword())) {
				//去认证
				excuteLoginByPhoneMsgCode(username, "", "", deviceType);
				//认证成功后删除上次的Session
				this.deleteSession(loginInfo.getStand1());
				resultMap.put("currentUser", user.getLoginName());
				resultMap.put("sn", UserUtil.getSession().getId());
				resultMap.put("userId", user.getId());
				loginInfo.errCount(0);
				loginInfo.setLockStartTime(null);
				loginInfo.setLockEndTime(null);
				loginInfo.setLastSucTime(new Date());
				loginInfo.setLastSucIp(req.getRemoteAddr());
				loginInfo.setLastDevice(deviceType);
				loginInfo.setStand1(UserUtil.getSession().getId().toString());
				this.updateInfo(loginInfo);
			}else {
				int remainCount = this.loginErrorInfo(loginInfo);
				resultMap.put("remainCount", remainCount);
			}
		} catch (Exception e) {
			throw printException(e);
		}
		return resultMap;
	}
	/**
	 * 执行伪登录
	 * @param req
	 * @param username
	 * @param password
	 * @param deviceType
	 */
	@SuppressWarnings("unused")
	private void excuteLoginByUserNamePassword(HttpServletRequest req, String username,String password,String deviceType) {
		UsernamePasswordToken token = new UsernamePasswordToken();
		if(req!=null) {
			String host = StrUtil.getRemoteAddr(req);
			token.setHost(host);
		}
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		token.setDeviceType(deviceType);
		UserUtil.getSubject().login(token);
	}
	
	/**
	 * 执行伪登录
	 * @param req
	 * @param username
	 * @param password
	 * @param deviceType
	 */
	@Override
	public void excuteLoginByPhoneMsgCode(String loginName,String phone,String msgCode,String deviceType) {
		PhoneToken token = new PhoneToken();
		token.setLoginName(loginName);
		token.setPhone(phone);
		token.setMsgcode(msgCode);
		token.setDeviceType(deviceType);
		UserUtil.getSubject().login(token);
	}
	
	

	@Override
	public int loginErrorInfo(LoginInfo loginInfo) {
		//剩余失败次数
		int remainCount = 0;
		try {
			if(loginInfo!=null) {
				Integer errCount = loginInfo.getErrCount();
				SysConfig lockCount = this.sysConfigService.getConfigByName(SysConfigEnum.LOCKCOUNT.getConfName());
				SysConfig lockHour = this.sysConfigService.getConfigByName(SysConfigEnum.LOCKHOUR.getConfName());
				//查看账户失败次数
				Integer lockCountValue = Integer.valueOf(lockCount.getConfValue());
				if((errCount+1)>=lockCountValue) {
					loginInfo.setIfLock("1");
					Date lockStart = new Date();
					loginInfo.setLockStartTime(lockStart);
					Integer hour = Integer.valueOf(lockHour.getConfValue());
					Date lockEnd = DateUtil.addHours(lockStart, hour);
					loginInfo.setLockEndTime(lockEnd);
				}else {
					remainCount =  lockCountValue - errCount - 1;
					loginInfo.errCount(errCount+1);
				}
				this.updateInfo(loginInfo);
			}
			//登录失败超过3次，短信通知当前账户主人
			Integer errCount = loginInfo.getErrCount();
			SysConfig errTips = this.sysConfigService.getConfigByName(SysConfigEnum.OPEN_LOGIN_ERROR_TIPS.getConfName());
			if(errTips!=null&&"1".equals(errTips.getConfValue())&&errCount==3) {
				SysUser sysUser = this.userService.getByLoginName(loginInfo.getLoginName());
				if(sysUser!=null && StringUtils.isNotBlank(sysUser.getMobile())) {
					System.err.println("登录失败达3次");
					TencentSmsUtil.smsSingleSenderLoginError(sysUser.getMobile(), 
							loginInfo.getLoginName(), errCount+"", remainCount+"");
				}
			}
			return remainCount;
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public Map<String, String> hasAllowLogin(HttpServletRequest req,String authCode) {
		Map<String, String> map = new HashMap<>();
		try {
			//查找二维码生成的登录SESSION，根据authCode
			Session session = sessionDAO.readSession(authCode);
			if(session!=null) {
				//APP登录确认时添加的用户信息
				Object status = session.getAttribute(SCAN_STATUS);
				Object loginName = session.getAttribute(USER_INFO);
				Object has = session.getAttribute(HAS_SUBMIT);
				if("1".equals(status)&&loginName!=null) {
					//如果当前状态为APP确认过，则执行登录
					SysUser user = UserUtil.getByLoginName(loginName.toString());
					//校验当前用户状态
					LoginInfo info = getInfo(loginName.toString());
					if(user != null && info!=null) {
						this.validUserStatus(user.getLoginName());
						//认证
						excuteLoginByPhoneMsgCode(user.getLoginName(), "", "", "pc");
						//更新登录信息,查看是否为重复登录
						if(!authCode.equals(info.getStand2())) {
							this.deleteSession(info.getStand2());
						}
						map.put("sn", UserUtil.getSession().getId().toString());
						map.put("loginName", user.getLoginName());
						info.setLastDevice("pc");
						info.setLastSucTime(new Date());
						info.setStand2(authCode);
						this.updateInfo(info);
						return map;
					}else {
						throw new ServiceException("登录失败，账户不存在");
					}
				}
				//查找二维码生成的登录SESSION，根据authCode
				map.put("submit", has==null?"no":has.toString());
				return map;
			}else {
				throw new ServiceException("二维码失效");
			}
		} catch (Exception e) {
			throw printException(e);
		}
	}


	//验证图片验证码
	@Override
	public void validImgCode(String token, String imgCode) {
		if(StringUtils.isBlank(token)||StringUtils.isBlank(imgCode)) {
			throw new ServiceException("验证码错误");
		}
		try {
			//二维码生成方式为将图片转为Base64,并将真正的验证码存放在Session中，因此需要将验证码从Session中取出
			Session session = this.sessionDAO.readSession(token);
			if(session==null || !imgCode.toUpperCase().equals(session.getAttribute(ValidateCodeServletImg.VALIDATE_CODE))) {
				throw new ServiceException("验证码错误");
			}
		} catch (Exception e) {
			throw printException(e,false);
		}
		
	}

	@Override
	public Map<String, Object> faceLogin(String userId) {
		try {
			Map<String, Object> map = new HashMap<>();
			//查询当前刷脸登录人
			SysUser user = this.userService.queryById(userId);
			if(user==null) {
				throw new ServiceException("认证失败");
			}
			this.validUserStatus(user.getLoginName());
			//执行认证
			excuteLoginByPhoneMsgCode(user.getLoginName(), "", "", "app");
			map.put("currentUser", user.getLoginName());
			map.put("sn", UserUtil.getSession().getId());
			map.put("userId", user.getId());
			//删除之前登录SESSION，更新新的登录时间
			LoginInfo info = this.getInfo(user.getLoginName());
			this.deleteSession(info.getStand1());
			info.setLastDevice("app");
			info.setLastSucTime(new Date());
			info.setStand1(UserUtil.getSession().getId().toString());
			this.updateInfo(info);
			return map;
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public void validMsgCode(String code, String phone) {
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
			throw new ServiceException("手机号和短信验证码不能为空");
		}
		try {
			SysUser user = UserUtil.getByPhone(phone);
			if (user == null) {
				throw new ServiceException("该手机号暂未绑定");
			}
			//获取当前用户登录信息，校验验证码时效性
			LoginInfo info = getInfo(user.getLoginName());
			Date endmsgTime = info.getEndmsgTime();
			if (new Date().before(endmsgTime)) {
				String msgCode = info.getMsgCode();
				if(!code.equals(msgCode)) {
					throw new ServiceException("短信验证码错误");
				}
			}else {
				throw new ServiceException("验证码失效，请重新发送");
			}
		} catch (Exception e) {
			throw printException(e);
		}
		
	}

	@Override
	public void deleteSession(String sessionID) {
		if(StringUtils.isBlank(sessionID)) {
			return;
		}
		try {
			Session session = this.sessionDAO.readSession(sessionID);
			if(session!=null) {
				this.sessionDAO.delete(session);
			}
		} catch (Exception e) {
			System.err.println("删除Session失效");
		}
		
	}

}
