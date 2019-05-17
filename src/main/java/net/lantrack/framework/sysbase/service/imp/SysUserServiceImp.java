package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.MapEntityService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.Encodes;
import net.lantrack.framework.core.util.IdGen;
import net.lantrack.framework.core.util.PatternUtil;
import net.lantrack.framework.security.Digests;
import net.lantrack.framework.springplugin.PropertyPlaceholder;
import net.lantrack.framework.sysbase.dao.DutyMenuDao;
import net.lantrack.framework.sysbase.dao.RoleMenuDao;
import net.lantrack.framework.sysbase.dao.SysMenuDao;
import net.lantrack.framework.sysbase.dao.SysUserDao;
import net.lantrack.framework.sysbase.dao.UserMenuDao;
import net.lantrack.framework.sysbase.dao.UserRoleDao;
import net.lantrack.framework.sysbase.enm.SysConfigEnum;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.entity.SysUserExample;
import net.lantrack.framework.sysbase.entity.SysUserExample.Criteria;
import net.lantrack.framework.sysbase.entity.UserRole;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.model.office.OfficeModel;
import net.lantrack.framework.sysbase.model.user.UserAuthModel;
import net.lantrack.framework.sysbase.model.user.UserModel;
import net.lantrack.framework.sysbase.model.user.UserOfficeIdsModel;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.service.SysConfigService;
import net.lantrack.framework.sysbase.service.SysOfficeTreeService;
import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.util.UserUtil;
import net.lantrack.framework.sysbase.util.http.SsoApiUtil;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 用户管理Service 2018年1月17日
 * 
 * @author hww
 */
@Service("sysUserServiceImp")
public class SysUserServiceImp extends BaseService implements SysUserService,MapEntityService {

	@Autowired
	SysUserDao sysUserDao;

	@Autowired
	SysMenuDao sysMenuDao;

	@Autowired
	UserRoleDao userRoleDao;

	@Autowired
	DutyMenuDao dutyMenuDao;

	@Autowired
	RoleMenuDao roleMenuDao;

	@Autowired
	UserMenuDao userMenuDao;

	@Autowired
	protected PageService pageService;

	@Autowired
	protected BaseDao baseDao;
	@Autowired
	protected LoginInfoService loginInfoService;
	
	@Autowired
	SysConfigService sysConfigService;
	
	@Autowired
	SysOfficeTreeService sysOfficeTreeService;
	
	/**
	 * 
	 * @param plainPassword
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return encodeHex(salt) + encodeHex(hashPassword);
	}
	

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}
	
	/**
     * 校验用户登录名重复性
     * @param user
     * @return
     * @date 2018年8月2日
     */
    private ServiceException validateRepeat(SysUser user) {
    	if(user==null) {
    		return new ServiceException("请求参数异常");
    	}
    	SysUserExample example = new SysUserExample();
    	//验证登录名
    	Criteria cr = example.createCriteria();
    	cr.andLoginNameEqualTo(user.getLoginName());
    	cr.andDelFlagEqualTo("0");
    	List<SysUser> list = sysUserDao.selectByExample(example);
    	if(list!=null&&list.size()>0) {
    		SysUser sysUser = list.get(0);
    		if(!sysUser.getId().equals(user.getId())) {
    			return new ServiceException("登录名"+user.getLoginName()+"已存在");
    		}
    	}
    	example.clear();
    	//验证手机号
    	Criteria cr1 = example.createCriteria();
    	cr1.andMobileEqualTo(user.getMobile());
    	cr1.andDelFlagEqualTo("0");
    	List<SysUser> list1 = sysUserDao.selectByExample(example);
    	if(list1!=null&&list1.size()>0) {
    		SysUser sysUser = list1.get(0);
    		if(!sysUser.getId().equals(user.getId())) {
    			return new ServiceException("联系方式"+user.getMobile()+"已存在");
    		}
    	}
    	example.clear();
    	//验证邮箱
    	Criteria cr2 = example.createCriteria();
    	cr2.andEmailEqualTo(user.getEmail());
    	cr2.andDelFlagEqualTo("0");
    	List<SysUser> list2 = sysUserDao.selectByExample(example);
    	if(list2!=null&&list2.size()>0) {
    		SysUser sysUser = list2.get(0);
    		if(!sysUser.getId().equals(user.getId())) {
    			return new ServiceException("邮箱"+user.getEmail()+"已存在");
    		}
    	}
    	return null;
    }
    
    //校验数据格式
    private String  validateUserData(SysUser entity){
    	
		StringBuffer errMsg = new StringBuffer();
		if(StringUtils.isNotBlank(entity.getEmail())&&
				!PatternUtil.isEmail(entity.getEmail())) {
			errMsg.append("邮箱格式错误  ");
		}
		if(StringUtils.isNotBlank(entity.getMobile())&&
				!PatternUtil.isMobile(entity.getMobile())) {
			errMsg.append("手机格式错误  ");
		}
		if(StringUtils.isNotBlank(entity.getBirthday())&&
				!PatternUtil.isBirthDay(entity.getBirthday())) {
			errMsg.append("生日格式错误  ");
		}
    	return errMsg.toString();
    }
    
	/**
	 * 新增用户
	 */
	@Override
	public SysUser save(SysUser entity) throws ServiceException {
		ServiceException exception = validateRepeat(entity);
		if(exception!=null) {
			throw exception;
		}
		String msg = validateUserData(entity);
		if(msg.length()>0) {
			throw new ServiceException(msg);
		}
		try {
			entity.setId(IdGen.uuid());
			SysConfig defaultPass = this.sysConfigService.getConfigByName(SysConfigEnum.DEFAULT_PASS.getConfName());
			entity.setPassword(entryptPassword(defaultPass.getConfValue()));
			entity.setSyncFlag("4");
			this.sysUserDao.insert(entity);
			LoginInfo info = new LoginInfo();
			info.setLoginName(entity.getLoginName());
			this.loginInfoService.insertInfo(info);
		} catch (Exception e) {
			throw printException(e);
		}
		return entity;
	}

	/**
	 * 修改用户
	 */
	@Override
	public SysUser update(SysUser entity) throws ServiceException {
		ServiceException repeat = validateRepeat(entity);
		if(repeat!=null) {
			throw repeat;
		}
		String msg = validateUserData(entity);
		if(msg.length()>0) {
			throw new ServiceException(msg);
		}
		try {
			//用户名密码和登录名不可改
			entity.setPassword(null);
			entity.setLoginName(null);
			this.sysUserDao.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw printException(e);
		}
		
		return entity;
	}

	/**
	 * 根据id获取指定用户
	 * 
	 * @param id
	 *            String类型
	 * @return 用户记录 SysUser类型
	 */
	@Override
	public SysUser queryById(Object id) {
		if (id == null || StringUtils.isBlank(id.toString())) {
			throw new ServiceException("id can not be null or empty!");
		}
		return this.sysUserDao.selectByPrimaryKey(id.toString());
	}

	/**
	 * 根据主键id获取指定用户
	 * 
	 * @param id
	 *            String类型
	 * @return 用户模型 UserModel类型
	 */
	@Override
	public UserModel queryByPk(String id) {
		if (id == null || StringUtils.isBlank(id.toString())) {
			throw new ServiceException("id can not be null or empty!");
		}
		return this.sysUserDao.selectModelByPrimaryKey(id);
	}

	@Override
	public void deleteById(Object id, String upBy, boolean ifLogic) throws ServiceException {
		if (id == null || StringUtils.isBlank(id.toString())) {
			throw new ServiceException("id can not be null or empty!");
		}
		if (ifLogic) {
			// 逻辑删除
			SysUser entity = new SysUser();
			entity.setCreateDate(null);
			entity.setUpdateBy(upBy);
			entity.setDelFlag(BaseEntity.YES);
			entity.setId(id.toString());
			this.sysUserDao.updateByPrimaryKeySelective(entity);
		} else {
			// 物理删除
			this.sysUserDao.deleteByPrimaryKey(id.toString());
		}
	}

	@Override
	public void deleteByIds(String ids, String upBy, boolean ifLogic) throws ServiceException {
		if(StringUtils.isBlank(ids)) {
			throw printException(new ServiceException("用户id不能为空"));
		}
		String[] split = ids.split(",");
		//删除时将用户踢掉，并清空用户的缓存
		UserUtil.delSession(split);
		UserUtil.deleteCache(split);
		List<String> idList = Arrays.asList(split);
		try {
			SysUserExample example = new SysUserExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(idList);
			List<SysUser> list = this.sysUserDao.selectByExample(example);
			for(SysUser user:list) {
				this.sysUserDao.deleteByPrimaryKey(user.getId());
				this.baseDao.deleteSql("delete from login_info where login_name = '"+user.getLoginName()+"'");
			}
			
		} catch (Exception e) {
			throw printException(e, true);
		}
	}

	@Override
	public void getPage(SysUser entity, PageEntity pageentity) {
		this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
		//如果查询条件中没有机构id时，则默认查看当前用户所在机构及下属机构人员信息
		if(StringUtils.isBlank(entity.getOfficeId())) {
			entity.setOfficeId(UserUtil.getUser().getOfficeId());
		}
		try {
			List<UserModel> result = this.sysUserDao.getUserListPage(pageentity, entity);
			pageentity.setContent(result);
		} catch (Exception e) {
			throw printException(e);
		}
		
	}

	@Override
	public void getUserByOfficeIdListPage(UserOfficeIdsModel entity, PageEntity pageentity) {
		this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
		Integer count = this.sysUserDao.getCountByOfficeId(entity.getOfficeIdList(), entity.getUserName());
		if (count != 0L) {
			pageentity.setTotalCount(count);
		}
		Integer startIndex = (pageentity.getCurrentPage() - 1) * pageentity.getPerPageCount();
		String ifAdmin = null, id = null;
		SysUser cUser = UserUtil.getUser();
		if ("0".equals(cUser.getIfAdmin())) {
			ifAdmin = "0";
			if ("0".equals(cUser.getAccreditFlag())) {
				id = cUser.getId();
			}
		}
		List<UserModel> result = this.sysUserDao.getPageByOfficeId(entity.getOfficeIdList(), entity.getUserName(),
				ifAdmin, id, startIndex, pageentity.getPerPageCount());
//		if ("0".equals(cUser.getIfAdmin())) {
//			Iterator<UserModel> iterator = result.iterator();
//			while (iterator.hasNext()) {
//				UserModel model = iterator.next();
//				if (StringUtils.isNotBlank(cUser.getOfficeId()) && !cUser.getOfficeId().equals(model.getOfficeId())) {
//					iterator.remove();
//				}
//			}
//		}
		pageentity.setContent(result);
	}

	@Override
	public void getPageRe(SysUser entity, PageEntity pageentity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIdsRe(String ids, String update_by) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据属性获取符合条件的用户列表，类似于queryByWhere
	 */
	@Override
	public List<SysUser> getAll(SysUser entity) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria cr = example.createCriteria();
		if (StringUtils.isNotBlank(entity.getDelFlag())) {
			cr.andDelFlagEqualTo(entity.getDelFlag());
		}
		if (StringUtils.isNotBlank(entity.getOfficeId())) {
			cr.andOfficeIdEqualTo(entity.getOfficeId());
		}
		if (StringUtils.isNotBlank(entity.getLoginName())) {
			cr.andLoginNameEqualTo(entity.getLoginName());
		}
		if (StringUtils.isNotBlank(entity.getUserCode())) {
			cr.andUserCodeLike(entity.getUserCode());
		}
		if (StringUtils.isNotBlank(entity.getUserName())) {
			cr.andUserNameLike(entity.getUserName());
		}
		if (StringUtils.isNotBlank(entity.getSex())) {
			cr.andSexEqualTo(entity.getSex());
		}
		if (StringUtils.isNotBlank(entity.getMobile())) {
			cr.andMobileLike(entity.getMobile());
		}

		try {
			return this.sysUserDao.selectByExample(example);
		} catch (Exception e) {
			this.logger.error("sysUserServiceImp getAll has Error:", e);
			throw new ServiceException("获取全部用户时发生异常");
		}
	}

	/**
	 * 同步用户数据
	 */
	@Override
	public String extractUserFromSso(String sn) throws Exception {
		String responseStr = "", rmg = "";
		try {
			responseStr = SsoApiUtil.getInstance()
					.getBasicDataList(PropertyPlaceholder.getProperty("sso.service")
							+ PropertyPlaceholder.getProperty("sso.extractUserUrl") + "?appId="
							+ PropertyPlaceholder.getProperty("sso.appId") + "&sn=" + sn);
			JsonParser parser = new JsonParser();
			JsonObject jo = (JsonObject) parser.parse(responseStr);
			JsonArray jsonArray = jo.get("result").getAsJsonArray();
			List<SysUser> list = new ArrayList<SysUser>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonElement element = jsonArray.get(i);
				JsonObject jobt = (JsonObject) parser.parse(element.toString());
				SysUser sysUser = new SysUser();
				sysUser.setId(jobt.get("id").toString().substring(1, jobt.get("id").toString().length() - 1));// id
				sysUser.setOfficeId(
						jobt.get("sys_office_id") == null || "null".equals(jobt.get("sys_office_id").toString()) ? null
								: jobt.get("sys_office_id").toString().substring(1,
										jobt.get("sys_office_id").toString().length() - 1));
				sysUser.setLoginName(
						jobt.get("login_name") == null || "null".equals(jobt.get("login_name").toString()) ? null
								: jobt.get("login_name").toString().substring(1,
										jobt.get("login_name").toString().length() - 1));
				sysUser.setUserCode(jobt.get("no") == null || "null".equals(jobt.get("no").toString()) ? null
						: jobt.get("no").toString().substring(1, jobt.get("no").toString().length() - 1));
				sysUser.setDutyName(jobt.get("user_duty") == null || "null".equals(jobt.get("user_duty").toString())
						? null
						: jobt.get("user_duty").toString().substring(1, jobt.get("user_duty").toString().length() - 1));
				sysUser.setPassword(jobt.get("password") == null || "null".equals(jobt.get("password").toString())
						? null
						: jobt.get("password").toString().substring(1, jobt.get("password").toString().length() - 1));
				sysUser.setUserName(jobt.get("name") == null || "null".equals(jobt.get("name").toString()) ? null
						: jobt.get("name").toString().substring(1, jobt.get("name").toString().length() - 1));
				sysUser.setSex(jobt.get("sex") == null || "null".equals(jobt.get("sex").toString()) ? null
						: jobt.get("sex").toString().substring(1, jobt.get("sex").toString().length() - 1));
				sysUser.setIfAdmin(jobt.get("if_admin") == null || "null".equals(jobt.get("if_admin").toString()) ? "0"
						: jobt.get("if_admin").toString().substring(1, jobt.get("if_admin").toString().length() - 1));
				sysUser.setAccreditFlag("1".equals(sysUser.getIfAdmin()) ? "1" : "0");
				sysUser.setEmail(jobt.get("email") == null || "null".equals(jobt.get("email").toString()) ? null
						: jobt.get("email").toString().substring(1, jobt.get("email").toString().length() - 1));
				sysUser.setMobile(jobt.get("mobile") == null || "null".equals(jobt.get("mobile").toString()) ? null
						: jobt.get("mobile").toString().substring(1, jobt.get("mobile").toString().length() - 1));
				sysUser.setUserDuty(1);
				sysUser.setSyncFlag("0");
				sysUser.setCreateBy(jobt.get("create_by") == null || "null".equals(jobt.get("create_by").toString())
						? UserUtil.getCurrentUser()
						: jobt.get("create_by").toString().substring(1, jobt.get("create_by").toString().length() - 1));
				sysUser.setCreateDate(
						jobt.get("create_date") == null || "null".equals(jobt.get("create_date").toString())
								? DateUtil.getDateTime()
								: jobt.get("create_date").toString().substring(1,
										jobt.get("create_date").toString().length() - 1));
				sysUser.setUpdateBy(jobt.get("update_by") == null || "null".equals(jobt.get("update_by").toString())
						? UserUtil.getCurrentUser()
						: jobt.get("update_by").toString().substring(1, jobt.get("update_by").toString().length() - 1));
				sysUser.setUpdateDate(
						jobt.get("update_date") == null || "null".equals(jobt.get("update_date").toString())
								? DateUtil.getDateTime()
								: jobt.get("update_date").toString().substring(1,
										jobt.get("update_date").toString().length() - 1));
				sysUser.setRemarks(jobt.get("remarks") == null || "null".equals(jobt.get("remarks").toString()) ? null
						: jobt.get("remarks").toString().substring(1, jobt.get("remarks").toString().length() - 1));
				list.add(sysUser);
			}
			List<SysUser> localList = this.getAllLocalUserList();
			if ((localList != null && localList.size() == 0) || localList == null) {
				sysUserDao.insertList(list);
				rmg = "全量同步" + list.size() + "条用户数据成功";
				System.out.println(DateUtil.getDateTime() + rmg);
			} else {
				// 通过循环区分出哪些是需要新增的、哪些是需要修改的、哪些是需要删除的、哪些是原封不动的
				Map<String, List<SysUser>> map = separateCrudUser(list, localList);
				List<SysUser> insertList = map.get("insertList");
				List<SysUser> deleteList = map.get("deleteList");
				List<SysUser> updateList = map.get("updateList");
				List<SysUser> nochangeList = map.get("nochangeList");
				if (insertList != null && insertList.size() > 0) {
					sysUserDao.insertList(insertList);
				}
				if (deleteList != null && deleteList.size() > 0) {
					for (SysUser user : deleteList) {
						sysUserDao.updateByPrimaryKeySelective(user);
					}
				}
				if (updateList != null && updateList.size() > 0) {
					for (SysUser user : updateList) {
						sysUserDao.updateByPrimaryKeySelective(user);
					}
				}
				if (nochangeList != null && nochangeList.size() > 0) {
					for (SysUser user : nochangeList) {
						sysUserDao.updateByPrimaryKeySelective(user);
					}
				}
				if (insertList != null && deleteList != null && updateList != null && nochangeList != null) {
					rmg = "用户数据同步结果为：新增" + insertList.size() + "条，更新" + updateList.size() + "条，删除" + deleteList.size()
							+ "条，未变化的为" + nochangeList.size() + "条";
					System.out.println(DateUtil.getDateTime() + rmg);
				}
			}
			// 同步成功后延后12个小时再将新增（0）和修改（2）都置为不变（3），再将删除（1）的del_flag置为1
			new Thread(new DelayResetThread(sysUserDao)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rmg;
	}

	/**
	 * 获取本地所有用户的集合
	 * 
	 * @return list List<SysUser>类型
	 */
	public List<SysUser> getAllLocalUserList() {
		SysUserExample example = new SysUserExample();
		return this.sysUserDao.selectByExample(example);
	}

	/**
	 * 区分出哪些是需要新增的、哪些是需要修改的、哪些是需要删除的、哪些是原封不动的用户
	 * 
	 * @param source
	 *            从单点登录系统抽取的全部SysUser集合
	 * @param target
	 *            本地系统目前数据里的全部SysUser集合
	 * @return
	 */
	public Map<String, List<SysUser>> separateCrudUser(List<SysUser> source, List<SysUser> target) {
		Map<String, List<SysUser>> map = new HashMap<String, List<SysUser>>();
		List<SysUser> insertList = new ArrayList<SysUser>();// 新增的list
		List<SysUser> deleteList = new ArrayList<SysUser>();// 删除的list
		List<SysUser> updateList = new ArrayList<SysUser>();// 修改的list
		List<SysUser> nochangeList = new ArrayList<SysUser>();// 没变的list
		List<SysUser> updateAndNochangeList = new ArrayList<SysUser>();// 交集中源头的list
		List<SysUser> updateAndNcTargetList = new ArrayList<SysUser>();// 交集中目的端list
		if (source == null || target == null) {
			return map;
		}
		// 分离出两个list的交集，即两边都有的sourceList
		for (int i = 0; i < source.size(); i++) {
			SysUser sourceUser = source.get(i);
			for (int j = 0; j < target.size(); j++) {
				SysUser targetUser = target.get(j);
				if (targetUser.getId().equals(sourceUser.getId())) {
					updateAndNochangeList.add(sourceUser);
					updateAndNcTargetList.add(targetUser);
				}
			}
		}
		// 再分离出需要新增的list
		for (int i = 0; i < source.size(); i++) {
			SysUser sourceUser = source.get(i);
			String sourceId = sourceUser.getId();
			boolean isInsert = true;
			for (int j = 0; j < updateAndNochangeList.size(); j++) {
				SysUser tempUser = updateAndNochangeList.get(j);
				if (sourceId.equals(tempUser.getId())) {
					isInsert = false;
					break;
				}
			}
			if (isInsert) {
				// 同步标记 0新增
				sourceUser.setSyncFlag("0");
				insertList.add(sourceUser);
			}
		}
		map.put("insertList", insertList);
		// 再分离出需要删除的list
		for (int i = 0; i < target.size(); i++) {
			SysUser targetUser = target.get(i);
			String sourceId = targetUser.getId();
			boolean isDelete = true;
			for (int j = 0; j < updateAndNochangeList.size(); j++) {
				SysUser tempUser = updateAndNochangeList.get(j);
				if (sourceId.equals(tempUser.getId())) {
					isDelete = false;
				}
			}
			if (isDelete && !"1".equals(targetUser.getSyncFlag())) {
				// 同步标记 1删除
				targetUser.setSyncFlag("1");
				deleteList.add(targetUser);
			}
		}
		map.put("deleteList", deleteList);
		// 最后分离出需要修改的list和没有任何变化的list,都可以是需要修改的list
		for (int i = 0; i < updateAndNochangeList.size(); i++) {
			SysUser sourceUser = updateAndNochangeList.get(i);
			boolean isChanged = true;
			for (int j = 0; j < updateAndNcTargetList.size(); j++) {
				SysUser targetUser = updateAndNcTargetList.get(j);
				if ((sourceUser.getId() != null && sourceUser.getId().equals(targetUser.getId()))
						&& (sourceUser.getOfficeId() != null
								&& sourceUser.getOfficeId().equals(targetUser.getOfficeId()))
						&& (sourceUser.getLoginName() != null
								&& sourceUser.getLoginName().equals(targetUser.getLoginName()))
						&& ((sourceUser.getUserCode() != null
								&& sourceUser.getUserCode().equals(targetUser.getUserCode()))
								|| (sourceUser.getUserCode() == null && targetUser.getUserCode() == null))
						&& (sourceUser.getUserName() != null
								&& sourceUser.getUserName().equals(targetUser.getUserName()))
						&& ((sourceUser.getSex() != null && sourceUser.getSex().equals(targetUser.getSex()))
								|| (sourceUser.getSex() == null && targetUser.getSex() == null))
						&& ((sourceUser.getEmail() != null && sourceUser.getEmail().equals(targetUser.getEmail()))
								|| (sourceUser.getEmail() == null && targetUser.getEmail() == null))
						&& ((sourceUser.getMobile() != null && sourceUser.getMobile().equals(targetUser.getMobile()))
								|| (sourceUser.getMobile() == null && targetUser.getMobile() == null))
						&& (sourceUser.getUserDuty() == targetUser.getUserDuty())
						&& ((sourceUser.getRemarks() != null && sourceUser.getRemarks().equals(targetUser.getRemarks()))
								|| (sourceUser.getRemarks() == null && targetUser.getRemarks() == null))
						&& ((sourceUser.getDutyName() != null
								&& sourceUser.getDutyName().equals(targetUser.getDutyName()))
								|| (sourceUser.getDutyName() == null && targetUser.getDutyName() == null))
						&& ((sourceUser.getIfAdmin() != null && sourceUser.getIfAdmin().equals(targetUser.getIfAdmin()))
								|| (sourceUser.getIfAdmin() == null && targetUser.getIfAdmin() == null))) {
					// 同步标记 3不变
					sourceUser.setSyncFlag("3");
					nochangeList.add(sourceUser);
					isChanged = false;
				}
			}
			if (isChanged) {
				// 同步标记 2修改
				sourceUser.setSyncFlag("2");
				updateList.add(sourceUser);
			}
		}
		map.put("updateList", updateList);
		map.put("nochangeList", nochangeList);
		return map;
	}

	/**
	 * 保存已指派好职务或角色的用户权限关系数据
	 * 
	 * @param id
	 *            用户id
	 * @param dutyIdArray
	 *            职务id数组
	 * @param roleIdArray
	 *            角色id数组
	 * @return list List<MenuTreeCheckedModel>
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public boolean saveUserDutyRoleMenu(UserAuthModel model) throws ServiceException {
		// 先判断当前登录的用户是否是管理员，如果是管理员则只处理角色，若不是管理员则只处理菜单
//		String userId = model.getId();
//		List<String> uList = new ArrayList<String>(1);
//		uList.add(userId);
//		String accreditFlag = model.getAccreditFlag();
//		if (StringUtils.isNotBlank(accreditFlag)) {
//			SysUser sysUser = this.sysUserDao.selectByPrimaryKey(userId);
//			if (!"1".equals(sysUser.getIfAdmin())) {
//				sysUser.setAccreditFlag(accreditFlag);
//				this.sysUserDao.updateByPrimaryKeySelective(sysUser);
//			}
//		}
//		// 先删除该用户对应的所有角色的关系
//		this.userRoleDao.deleteByUserId(uList);
//		// 先删除该用户对应的所有权限的关系
//		this.userMenuDao.deleteByUserId(uList);
//		// 管理员
//		if (UserUtil.ifAdmin()) {
//			String roles = model.getRoles();
//			if (StringUtils.isNotBlank(roles)) {
//				// 再保存用户和角色的对应关系
//				List<UserRole> urlist = new ArrayList<UserRole>();
//				String[] roleIdArray = roles.split(",");
//				for (int i = 0; i < roleIdArray.length; i++) {
//					Integer roleId = Integer.valueOf(roleIdArray[i]);
//					UserRole userRole = new UserRole(userId, roleId);
//					urlist.add(userRole);
//				}
//				this.userRoleDao.insertList(urlist);
//			}
//		} else {
//			String menus = model.getMenus();
//			if (StringUtils.isNotBlank(menus)) {
//				// 最后保存用户和权限的对应关系
//				List<UserMenu> umlist = new ArrayList<UserMenu>();
//				String[] menuIdArray = menus.split(",");
//				for (int i = 0; i < menuIdArray.length; i++) {
//					Integer menuId = Integer.valueOf(menuIdArray[i]);
//					UserMenu userMenu = new UserMenu(userId, menuId);
//					umlist.add(userMenu);
//				}
//				this.userMenuDao.insertList(umlist);
//			}
//		}
//
		return true;
	}

	@Override
	public SysUser getByLoginName(String loginName) throws ServiceException {
		try {
			SysUserExample userExample = new SysUserExample();
			SysUserExample.Criteria cr = userExample.createCriteria();
			cr.andLoginNameEqualTo(loginName);
			List<SysUser> list = this.sysUserDao.selectByExample(userExample);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				logger.info("can not find the user:" + loginName);
				return null;
			}
		} catch (Exception e) {
			throw printException(e);
		}
		
	}

	@Override
	public List<MenuTreeModel> getUserMenus(String userId) {
		List<MenuTreeModel> menuList = new ArrayList<MenuTreeModel>();
		Set<Integer> menus = new HashSet<Integer>();
		// 查询所拥有角色的权限
		List<Integer> rolelist = this.userRoleDao.queryRoleListByUser(userId);
		if (rolelist != null && rolelist.size() > 0) {
			// 只有角色
			List<Integer> listByRole = this.roleMenuDao.queryMenuListByRoles(rolelist);
			if (listByRole != null) {
				for (Integer menuid : listByRole) {
					menus.add(menuid);
				}
			}
		} else {
			// 只有菜单
			List<Integer> listByUser = this.userMenuDao.queryMenuListByUser(userId);
			if (listByUser != null) {
				for (Integer menuid : listByUser) {
					menus.add(menuid);
				}
			}
		}
		if (menus.size() > 0) {
			List<SysMenuModel> sysMenuModels = new ArrayList<SysMenuModel>();
			List<Integer> idList = new ArrayList<Integer>();
			Iterator<Integer> iterator = menus.iterator();
			while (iterator.hasNext()) {
				idList.add(Integer.valueOf(iterator.next()));
			}
			try {
				sysMenuModels = this.sysMenuDao.queryModelListByidList(idList);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (sysMenuModels != null && sysMenuModels.size() > 0) {
				for (SysMenuModel sysMenuModel : sysMenuModels) {
					MenuTreeModel menuTreeModel = new MenuTreeModel();
					menuTreeModel.setId(sysMenuModel.getId());
					menuTreeModel.setPid(sysMenuModel.getParentId());
					menuTreeModel.setName(sysMenuModel.getMenuName());
					menuList.add(menuTreeModel);
				}
			}
		}
		return menuList;
	}

	@Override
	public SysUser getByPhone(String phone) {
		SysUserExample userExample = new SysUserExample();
		SysUserExample.Criteria cr = userExample.createCriteria();
		cr.andMobileEqualTo(phone);
		List<SysUser> list = this.sysUserDao.selectByExample(userExample);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			logger.info("can not find the user:" + phone);
			return null;
		}
	}

	@Override
	public SysUser updataPassworByPhone(String password, String phone) {
		SysUser sysUser = new SysUser();
		SysUserExample userExample = new SysUserExample();
		SysUserExample.Criteria cr = userExample.createCriteria();
		cr.andMobileEqualTo(phone);
		cr.andDelFlagEqualTo("0");
		List<SysUser> list = this.sysUserDao.selectByExample(userExample);
		if(list.size() > 0){
		    sysUser = list.get(0);
			sysUser.setPassword(SysUserServiceImp.entryptPassword(password));
			try {
				sysUserDao.updateByPrimaryKeySelective(sysUser);
			} catch (Exception e) {
				logger.info("can not find the user:" + phone);
				throw new ServiceException("修改密码失败");
			}
		}
		return sysUser;
	}

	@Override
	public String disableUser(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw printException(new ServiceException("用户id不能为空"));
		}
		String[] userIdArray = ids.split(",");
		//禁用账户时如果用户在线，则踢出
		UserUtil.delSession(userIdArray);
		List<String> idList = Arrays.asList(userIdArray);
		try {
			SysUserExample example = new SysUserExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(idList);
			List<SysUser> list = this.sysUserDao.selectByExample(example);
			for(SysUser user:list) {
				String updateSql = "update login_info set if_forbidden = '1' where login_name = '"+
						user.getLoginName()+"' and if_lock = '0'";
				this.baseDao.updateSql(updateSql);
			}
		} catch (Exception e) {
			throw printException(e);
		}
		return "";
	}

	@Override
	public List<MapEntity> getSelectMap() {
		SysUserExample example = new SysUserExample();
		if(!UserUtil.ifAdmin()) {
			List<OfficeModel> currentOfficeTree = sysOfficeTreeService.getCurrentOfficeTree();
			List<String> officeIdList = new ArrayList<>(currentOfficeTree.size());
			if(currentOfficeTree!=null && currentOfficeTree.size()>0) {
				for(OfficeModel model:currentOfficeTree) {
					officeIdList.add(model.getId());
				}
				Criteria cr = example.createCriteria();
				cr.andOfficeIdIn(officeIdList);
			}
			
		}
		List<SysUser> list = this.sysUserDao.selectByExample(example);
		List<MapEntity> resultList = new ArrayList<>(list.size());
		for(SysUser user:list) {
			MapEntity entity = new MapEntity();
			entity.setK(user.getId());
			entity.setV(user.getUserName());
			resultList.add(entity);
		}
		return resultList;
	}

	@Override
	@Transactional
	public void configRoles(String userIds, String roleIds) {
		if(StringUtils.isBlank(userIds)||StringUtils.isBlank(roleIds)) {
			return;
		}
		String[] userArray = userIds.split(",");
		String[] roleArray = roleIds.split(",");
		//配置权限时将用户踢掉，并清空用户的缓存
		UserUtil.delSession(userArray);
		UserUtil.deleteCache(userArray);
		try {
			List<UserRole> list = new ArrayList<>(userArray.length*roleArray.length);
			for(String uid:userArray) {
				for(String rid:roleArray) {
					UserRole ur = new UserRole(uid, Integer.valueOf(rid));
					list.add(ur);
				}
			}
			this.userRoleDao.deleteByUserId(Arrays.asList(userArray));
			this.userRoleDao.insertList(list);
		} catch (Exception e) {
			throw printException(e);
		}
		
	}

	@Override
	public String unlockUser(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw printException(new ServiceException("用户id不能为空"));
		}
		String[] split = ids.split(",");
		List<String> idList = Arrays.asList(split);
		try {
			SysUserExample example = new SysUserExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(idList);
			List<SysUser> list = this.sysUserDao.selectByExample(example);
			for(SysUser user:list) {
				String updateSql = "update login_info set if_lock = '0',err_count = '0', "+
						" lock_start_time = null, lock_end_time = null"+
						" where login_name = '"+user.getLoginName()+"'";
				this.baseDao.updateSql(updateSql);
			}
		} catch (Exception e) {
			throw printException(e);
		}
		return "";
	}

	@Override
	public String enableUser(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw printException(new ServiceException("用户id不能为空"));
		}
		String[] split = ids.split(",");
		List<String> idList = Arrays.asList(split);
		try {
			SysUserExample example = new SysUserExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(idList);
			List<SysUser> list = this.sysUserDao.selectByExample(example);
			for(SysUser user:list) {
				String updateSql = "update login_info set if_forbidden = '0' where login_name = '"+user.getLoginName()+"'";
				this.baseDao.updateSql(updateSql);
			}
		} catch (Exception e) {
			throw printException(e);
		}
		return "";
	}

	
}

// 延迟一段时间后再重置同步标识位为不变
class DelayResetThread implements Runnable {

	private SysUserDao sysUserDao;

	public DelayResetThread(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	@Override
	public void run() {
		try {
			/**
			 * 12小时=1000*60*60*12毫秒 1分钟=1000*60毫秒 延迟重置同步标识位
			 */
			Thread.sleep(1000 * 30);
			resetNoChangeFlag();
			resetDelFlag();
			System.out.println("延后30s同步标记重置完成");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步成功后延迟将新增（0）和修改（2）都置为不变（3）
	 */
	private void resetNoChangeFlag() {
		SysUser record = new SysUser();
		record.setCreateBy(null);
		record.setUserCode(null);
		record.setSyncFlag("3");
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria cr = example.createCriteria();
		List<String> values = new ArrayList<String>();
		values.add("0");
		values.add("2");
		cr.andSyncFlagIn(values);
		sysUserDao.updateByExampleSelective(record, example);
	}

	/**
	 * 同步成功后延迟将删除（1）的del_flag置为1，即逻辑删除
	 */
	private void resetDelFlag() {
		SysUser record = new SysUser();
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria cr = example.createCriteria();
		record.setSyncFlag("1");
		record.setDelFlag("1");
		record.setCreateBy(null);
		record.setUserCode(null);
		cr.andSyncFlagEqualTo("1");
		sysUserDao.updateByExampleSelective(record, example);
	}

	

}
