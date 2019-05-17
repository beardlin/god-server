package net.lantrack.framework.sysbase.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.enm.SysConfigEnum;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.model.user.LoginParm;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.service.SysConfigService;
import net.lantrack.framework.sysbase.service.SysUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录管理
 * 
 * @date 2019年1月21日
 */
@RestController
@RequestMapping("/otherlogin")
public class LoginOtherController extends BaseController {

	@Autowired
	private LoginInfoService loginInfoService;

	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private SysUserService sysUserService;

	// 刷脸登录后认证  /otherlogin/facelogin
	@RequestMapping("/facelogin")
	public ReturnEntity facelogin(ReturnEntity info, String userId) {
		try {
			Map<String, Object> map = loginInfoService.faceLogin(userId);
			info.success("登录成功");
			info.setResult(map);
		} catch (Exception e) {
			info.failed("登录失败:",e.getMessage());
		}
		return info;
	}

	// 前台二维码状态查询 /otherlogin/scanStatus
	@RequestMapping("/scanStatus")
	public ReturnEntity scanStatus(ReturnEntity info, HttpServletRequest req, String authCode) {
		Map<String, Object> resultMap = new HashMap<>();
//		System.out.println(authCode+"----");
		try {
			Map<String, String> map = loginInfoService.hasAllowLogin(req, authCode);
			if (map.get("loginName") != null) {
				info.success("登录成功");
				resultMap.put("currentUser", map.get("loginName"));
				resultMap.put("sn", map.get("sn"));
			} else {
				String has = map.get("submit");
				if("yes".equals(has)) {
					info.failed("登录失败,请重新扫描二维码");
				}
				if("no".equals(has)) {
					info.failed("等待APP确认");
				}
				resultMap.put("button", has);
			}
		} catch (Exception e) {
			info.failed("登录失败:"+e.getMessage());
		}
		info.setResult(resultMap);
		return info;
	}

	// APP扫码确认 /otherlogin/allow
	@RequestMapping("/allow")
	public ReturnEntity allow(ReturnEntity info, String loginName, String authCode, String functionFlag) {
		try {
			loginInfoService.allowAppScanCode(loginName, authCode);
			info.success("确认成功");
			info.setResult(loginName);
		} catch (Exception e) {
			info.failed("确认失败");
		}
		return info;
	}

	// 返回二维码信息 /otherlogin/scancode
	@RequestMapping("/scancode")
	public ReturnEntity scanCode(ReturnEntity info) {
		try {
			String scanCodeAuth = loginInfoService.getAppScanCode();
			info.success("生成成功");
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("key", scanCodeAuth);
			resultMap.put("function", "scanLogin");
			info.setResult(resultMap);
		} catch (Exception e) {
			info.failed("生成失败");
		}
		return info;
	}

	// 获取登录方式 /otherlogin/logintype
	@RequestMapping("/logintype")
	public ReturnEntity loginType(ReturnEntity info) {
		try {
			SysConfig loginType = sysConfigService.getConfigByName(SysConfigEnum.LOGINTYPE.getConfName());
			info.setResult(loginType.getConfValue());
		} catch (Exception e) {
			info.failed("获取登录方式失败");
		}
		return info;
	}

	// 短信验证码登录 校验短信验证码 /otherlogin/validmsgcode
	@RequestMapping("/validmsgcode")
	public ReturnEntity validMsgcode(ReturnEntity info, String deviceType,String code, String phone) {
		try {
			Map<String, Object> validMsgCode = this.loginInfoService.validMsgCodeToLogin(code, phone,deviceType);
			info.success("登录成功");
			info.setResult(validMsgCode);
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}

	// 获取短信验证码 /otherlogin/getmsgcode
	@RequestMapping("/getmsgcode")
	public ReturnEntity getMsgCode(ReturnEntity info, String phone) {
		try {
			this.loginInfoService.getMsgCodeByPhone(phone);
			info.success("发送成功");
		} catch (Exception e) {
			info.failed("发送失败：" + e.getMessage());
		}
		return info;
	}



	// APP账号密码登录 /otherlogin/byapp
	@RequestMapping(value = "/byapp")
	public ReturnEntity byApp(ReturnEntity info, HttpServletRequest request, @RequestBody LoginParm login) {
		try {
			// LoginParm login = getFromJson(json, LoginParm.class);
			Map<String, Object> appLogin = this.loginInfoService.appLogin(request, login.getUsername(),
					login.getPassword(), login.getDeviceType());
			if (appLogin.get("sn") == null) {
				info.failed("登录失败,剩余次数" + appLogin.get("remainCount"));
			} else {
				info.success("登录成功");
				info.setResult(appLogin);
			}

		} catch (Exception e) {
			info.failed("登录失败:" + e.getMessage());
		}
		return info;
	}

	
	// 校验短信验证码 /otherlogin/validmsg
	@RequestMapping("/validmsg")
	public ReturnEntity validmsg(ReturnEntity info, String code, String phone) {
		try {
			this.loginInfoService.validMsgCode(code, phone);
			info.success("校验成功");
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}

	// 验证图片验证码符合后再发送短信验证码
	@RequestMapping("/validCodeAndPhoneToPc")
	public ReturnEntity validCodeAndPhoneToPc(ReturnEntity info, String phone, 
			String validateCode,String imgToken) {
		// 校验参数信息
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(validateCode) || StringUtils.isBlank(imgToken)) {
			info.failed("参数不能为空");
			return info;
		}
		try {
			this.loginInfoService.validImgCode(imgToken, validateCode);
			// 验证成功发送短信验证码
			this.loginInfoService.getMsgCodeByPhone(phone);
			info.success("发送成功");
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}

	// 获取手机号和密码 保存密码
	@RequestMapping("/resetPassword")
	public ReturnEntity resetPassword(ReturnEntity info, String password, String phone) {
		Map<String, String> map = new HashMap<String, String>();
		// 校验参数信息
		if (StringUtils.isBlank(password) || StringUtils.isBlank(phone)) {
			info.failed("手机号或密码不能为空");
			return info;
		}
		try {
			// 获得验证码与手机号
			SysUser sysUser = this.sysUserService.updataPassworByPhone(password, phone);
			if (sysUser.getId() == null) {
				info.failed("重置密码失败");
				return info;
			}
			info.success("重置密码成功");
			map.put("loginName", sysUser.getLoginName());
			//密码重置后清空之前所有登录的APP和PC的session
			LoginInfo loginInfo = this.loginInfoService.getInfo(sysUser.getLoginName());
			if(loginInfo!=null) {
				String appToken = loginInfo.getStand1();
				String pcToken = loginInfo.getStand2();
				this.loginInfoService.deleteSession(appToken);
				this.loginInfoService.deleteSession(pcToken);
			}
			info.setResult(map);
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}

}
