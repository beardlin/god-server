
package net.lantrack.framework.sysbase.util;

import com.google.common.collect.Lists;

import net.lantrack.framework.core.cache.service.CacheService;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.service.MapEntityService;
import net.lantrack.framework.core.util.SpringContextHolder;
import net.lantrack.framework.security.session.SessionDAO;
import net.lantrack.framework.shiro.Principal;
import net.lantrack.framework.sysbase.entity.*;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.AuthorizingService;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.service.SysMenuService;
import net.lantrack.framework.sysbase.service.SysRoleService;
import net.lantrack.framework.sysbase.service.SysUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户工具类 2017年12月20日
 * 
 * @author lin
 */
public class UserUtil {

	protected Logger logger = (Logger) LogManager.getLogger("mylog");

	/**
	 * 初始化时，先由单点登录系统给本业务系统指派初始登录的管理员用户，即当前登录用户对象的isAdmin属性值为"1"
	 */
	public static String currentUserIsAdmin = "1";
	
	/**
	 * 当前用户session-KEY
	 */
	public static final String CURRENT_USER_SESSION = "current_user_session";

	/**
	 * 初始化时，角色表里必须有一条记录为管理员，且该角色id为1，Integer类型
	 */
	public static Integer adminRoleId = 1;

	
	/**
	 * Session管理
	 */
	private static SessionDAO sessionDao = ((SessionDAO) SpringContextHolder.getBean(SessionDAO.class));
	
	/**
	 * 用户Service
	 */
	private static SysUserService userService = ((SysUserService) SpringContextHolder.getBean("sysUserServiceImp"));
	


	/**
	 * 用户登录状态Service
	 */
	private static LoginInfoService loginInfoService = ((LoginInfoService) SpringContextHolder
			.getBean("loginInfoServiceImpl"));

	/**
	 * 授权登录
	 */
	private static AuthorizingService authorizingService = ((AuthorizingService) SpringContextHolder
			.getBean("authorizingServiceImpl"));
	
	/**
	 * 角色
	 */
	private static SysRoleService sysRoleService = ((SysRoleService) SpringContextHolder
			.getBean("sysRoleServiceImp"));
	
	/**
	 * 菜单
	 */
	private static SysMenuService sysMenuService = (SysMenuService) SpringContextHolder
			.getBean("sysMenuServiceImp");
	
	/**
	 * 用户下拉框
	 */
	private static MapEntityService userMapService =  (MapEntityService) SpringContextHolder.getBean("sysUserServiceImp");
	
	/**
	 * 获取缓存Service
	 */
	private static CacheService cacheService = (CacheService) SpringContextHolder.getBean("redisServiceImpl");
	
	//缓存
	public static final String SYS_MENU_CACHE = "sys_menu_cache_"; 
	public static final String SYS_MENUIDS_CACHE = "sys_menuids_cache_"; 
	public static final String SYS_PERMISSION_CACHE = "sys_permission_cache_"; 
	public static final String SYS_ROLE_CACHE="sys_role_cache_";
	public static final String SYS_ROLEIDS_CACHE="sys_roleids_cache_";

	
	/**
	 * 获取当前用户能见到的用户下拉列表
	 * @return
	 * @date 2019年3月29日
	 */
	public static List<MapEntity> getUserSelect(){
		return userMapService.getSelectMap();
	}
	
	/**
	 * 查看用户登录信息
	 * @return
	 * @date 2019年1月18日
	 */
	public static LoginInfo getLoginInfo() {
		String currentUser = getCurrentUser();
		if (currentUser != null) {
			return loginInfoService.getInfo(currentUser);
		}
		return null;
	}

	
	
	/**
	 * 获取当前用户的登录名
	 * 
	 * @return 取不到返回null
	 */
	public static String getCurrentUser() {
		Principal principal = UserUtil.getPrincipal();
		return principal == null ? null : principal.getLoginName();
	}

	/**
	 * 获取当前用户的ID
	 * 
	 * @return 取不到返回null
	 */
	public static String getCurrentUserId() {
		Principal principal = UserUtil.getPrincipal();
		return principal == null ? null : principal.getId();
	}

	/**
	 * 获取当前用户是否是初始管理员的标识
	 * 
	 * @return 若不是返回false
	 */
	public static boolean ifAdmin() {
		boolean flag = false;
		Principal principal = UserUtil.getPrincipal();
		if (principal != null && UserUtil.currentUserIsAdmin.equals(principal.getIfAdmin())) {
			flag = true;
		}
		return flag;
	}


	/**
	 * 根据ID获取用户实体
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUser get(String id) {
		SysUser user = new SysUser();
		try {
			user = (SysUser) userService.queryById(id);
		} catch (Exception e) {

		}
		return user;
	}

	/**
	 * 根据手机号获取用户
	 * 
	 * @param phone
	 * @return 取不到返回null
	 */
	public static SysUser getByPhone(String phone) {
		if (StringUtils.isBlank(phone)) {
			return null;
		}
		return userService.getByPhone(phone);
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static SysUser getByLoginName(String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return null;
		}

		return userService.getByLoginName(loginName);
	}

	/**
	 * 获取当前用户对象
	 * 
	 * @return 取不到返回 new User()
	 */
	public static SysUser getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			SysUser user = getByLoginName(principal.getLoginName());
			if (user == null) {
				user = getByPhone(principal.getLoginName());
			}
			if (user != null) {
				return user;
			}
			return new SysUser();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUser();
	}
	

	/**
	 * 删除所有缓存
	 * @date 2019年3月25日
	 */
	public static void clearCache() {
		cacheService.clear(SYS_MENU_CACHE);
		cacheService.clear(SYS_MENUIDS_CACHE);
		cacheService.clear(SYS_PERMISSION_CACHE);
		cacheService.clear(SYS_ROLE_CACHE);
		cacheService.clear(SYS_ROLEIDS_CACHE);
	}


	/**
	 * 删除用户缓存
	 * @date 2019年3月25日
	 */
	public static void deleteCache(String ...userIds) {
		if(userIds==null || userIds.length==0) {
			return;
		}
		for(String userId:userIds) {
			cacheService.delete(SYS_MENU_CACHE, userId);
			cacheService.delete(SYS_MENUIDS_CACHE, userId);
			cacheService.delete(SYS_PERMISSION_CACHE, userId);
			cacheService.delete(SYS_ROLE_CACHE, userId);
			cacheService.delete(SYS_ROLEIDS_CACHE, userId);
		}
		
	}
	/**
	 * 获取当前用户菜单ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getMenuIds() {
		Object object = cacheService.get(SYS_MENUIDS_CACHE, getCurrentUserId());
		if(object!=null) {
			return (List<String>) object;
		}
		List<String> list = new ArrayList<>();
		if(ifAdmin()) {
			List<SysMenu> all = sysMenuService.getAll(new SysMenu());
			for(SysMenu menu:all) {
				list.add(String.valueOf(menu.getId()));
			}
		}else {
			Set<Integer> menuIds = authorizingService.getMenuIdsByUserId(getCurrentUserId());
			for(Integer menuId:menuIds) {
				list.add(String.valueOf(menuId));
			}
			//只给非管理员用户放入缓存
			if(list!=null&&list.size()>0) {
				cacheService.save(SYS_MENUIDS_CACHE, getCurrentUserId(), list);
			}
		}
		return list;
		
	}
	
	

	/**
	 * 获取当前用户角色
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SysRole> getRoleList() {
		Object object = cacheService.get(SYS_ROLE_CACHE, getCurrentUserId());
		if(object!=null) {
			return (List<SysRole>) object;
		}
		List<SysRole> list = null;
		if(ifAdmin()) {
			list = sysRoleService.getAll(new SysRole());
		}else {
			list = sysRoleService.getRoleListByUserId(getCurrentUserId());
			//只给非管理员用户放入缓存
			if(list!=null&&list.size()>0) {
				cacheService.save(SYS_ROLE_CACHE, getCurrentUserId(), list);
			}
		}
		return list;
		
	}

	/**
	 * 获取当前用户角色id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> getRoleIds() {
		Object object = cacheService.get(SYS_ROLEIDS_CACHE, getCurrentUserId());
		if(object!=null) {
			return (List<Integer>) object;
		}
		List<Integer> ids = new ArrayList<>();
		if(ifAdmin()) {
			List<SysRole> all = sysRoleService.getAll(new SysRole());
			for(SysRole role:all) {
				ids.add(role.getId());
			} 
		}else {
			 ids = authorizingService.getRoleIdsByUserId(getCurrentUserId());
			 //只给非管理员用户放入缓存
			 if(ids!=null && ids.size()>0) {
				cacheService.save(SYS_ROLEIDS_CACHE, getCurrentUserId(), ids);
			 }
		}
		
		return ids;
	}

	/**
	 * 获取当前用户所有授权
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getPermissionList() {
		Object object = cacheService.get(SYS_PERMISSION_CACHE, getCurrentUserId());
		if(object!=null) {
			return (List<String>) object;
		}
		try {
			List<String> permissions = new ArrayList<>();
			if(ifAdmin()) {
				List<SysMenu> all = sysMenuService.getAll(new SysMenu());
				for(SysMenu menu:all) {
					if(StringUtils.isNotBlank(menu.getPermission())) {
						permissions.add(menu.getPermission());
					}
				}
			}else {
				permissions = authorizingService.getPermissions(getCurrentUserId());
				//只给非管理员用户放入缓存
				if(permissions!=null&&permissions.size()>0) {
					cacheService.save(SYS_PERMISSION_CACHE, getCurrentUserId(), permissions);
				}
			}
			return permissions;
		} catch (Exception e) {
			return Lists.newArrayList();
		}
	}
	/**
	 * 获取当前用户所能见到的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MenuTreeModel> getMenuTree() {
		Object object = cacheService.get(SYS_MENU_CACHE, getCurrentUserId());
		if(object!=null) {
			return (List<MenuTreeModel>) object;
		}
		try {
			List<MenuTreeModel> menuTree = new ArrayList<>();
			if(ifAdmin()) {
				List<SysMenu> all = sysMenuService.getAll(new SysMenu());
				for(SysMenu menu:all) {
					MenuTreeModel tree = new MenuTreeModel(menu.getId(), menu.getParentId(), menu.getMenuName(),menu.getSort());
					menuTree.add(tree);
				}
			}else {
				menuTree = authorizingService.getMenuTree(getCurrentUserId());
				//只给非管理员用户放入缓存
				if(menuTree!=null&&menuTree.size()>0) {
					cacheService.save(SYS_MENU_CACHE, getCurrentUserId(), menuTree);
				}
			}
			
			return menuTree;
		} catch (Exception e) {
			return Lists.newArrayList();
		}
		
	}
	

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者当事人对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			net.lantrack.framework.shiro.Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (Exception e) {

		}
		return null;
	}
	/**
	 * 获取用户Session
	 * @return
	 * @date 2019年3月25日
	 */
	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}
	
	/**
	 * 获取在线人数
	 * @return
	 * @date 2019年3月25日
	 */
	public static int onlineCount() {
		Set<String> set = new HashSet<>();
		try {
			Collection<Session> sessions = sessionDao.getActiveSessions(false);
			for(Session session:sessions) {
				Object principal = session.getAttribute("principalId");				
				if(principal!=null) {
					String userId = principal.toString();
					if (userId.contains(Principal.LOGIN_TYPE_APP)) {
						userId = userId.replace(Principal.LOGIN_TYPE_APP, "");
					}
					if (userId.contains(Principal.LOGIN_TYPE_PC)) {
						userId = userId.replace(Principal.LOGIN_TYPE_PC, "");
					}
					set.add(userId);
				}
			}
			return set.size();
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	/**
	 * 删除用户Session
	 * @param userid
	 * @return
	 * @date 2019年3月25日
	 */
	public static void delSession(String ...userids) {
		if(userids==null||userids.length==0) {
			return;
		}
		Map<String, String> userMap = new HashMap<>(userids.length*2);
		for(String userid:userids) {
			String principalPc = userid + Principal.LOGIN_TYPE_PC;
			String principalApp = userid + Principal.LOGIN_TYPE_APP;
			userMap.put(principalPc, userid);
			userMap.put(principalApp, userid);
		}
		try {
			Collection<Session> sessions = sessionDao.getActiveSessions(true);
			for(Session session:sessions) {
				Object principal = session.getAttribute("principalId");				
				if(principal!=null&&userMap.containsKey(principal.toString())) {
					sessionDao.delete(session);
				}
			}
		} catch (Exception e) {
			
		}
	}

}
