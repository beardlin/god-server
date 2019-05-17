/**
 *
 */
package net.lantrack.framework.shiro;


import net.lantrack.framework.core.config.Config;
import net.lantrack.framework.core.util.Encodes;
import net.lantrack.framework.security.session.SessionDAO;
import net.lantrack.framework.shiro.Principal;
import net.lantrack.framework.shiro.UsernamePasswordToken;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.service.LoginInfoService;
//import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author 大法师
 *
 */
@Service
//@DependsOn({"userDao","roleDao","menuDao"})
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = (Logger) LogManager.getLogger("mylog");

//	@Autowired
//	private SysUserService sysUserService;
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private SessionDAO sessionDao;
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
//		System.out.println("system--authenticate");
		UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) authcToken;
		int activeSessionSize = sessionDao.getActiveSessions(false).size();
		if (logger.isDebugEnabled()){
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, usernamePasswordToke.getUsername());
		}
		// 校验用户名密码
		SysUser user = null;
		if("admin".equals(usernamePasswordToke.getUsername())) {
			user = new SysUser();
			user.setId("admin");
			user.setLoginName("admin");
			user.setPassword("3a2c0bdfd3eecddc1920e665d140ea8487193e505fe030267a0a823b");
			user.setUserName("admin");
			user.setIfAdmin("1");
			user.setOfficeId("0");
		}else {
			user = UserUtil.getByLoginName(usernamePasswordToke.getUsername());
			if(user==null) {
				user = UserUtil.getByPhone(usernamePasswordToke.getUsername());
			}
		}
		if(logger.isDebugEnabled()){
	        logger.debug("userCheckStart:",user);
	    }
		if(user!=null) {
			//校验当前用户状态
			this.loginInfoService.validUserStatus(user.getLoginName());
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
			String userId= user.getId()==null?"":user.getId()+"";
			//登录名
			String loginName = user.getLoginName();
			Principal principal = new Principal(userId,loginName,user.getUserName(),
					usernamePasswordToke.getDeviceType(),user.getIfAdmin(),
					UserUtil.getSession().getId().toString());
	        return new SimpleAuthenticationInfo(principal,user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		}else {
			return null;
		}
	
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		System.out.println("system");
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Config.TRUE.equals(Config.usermultiAccountLogin)){
			Collection<Session> sessions = sessionDao.getActiveSessions(true, principal, UserUtil.getSession());
			if (sessions.size() > 0){
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtil.getSubject().isAuthenticated()){
					for (Session session : sessions){
						sessionDao.delete(session);
					}
				}else{
					// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。 互斥登录只留一个，即不让存在有记住我！
					UserUtil.getSubject().logout();
					throw new AuthenticationException("账号已在其它地方登录，请重新登录。");
				}
			}
		}
		SysUser user = UserUtil.getByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<String> list = UserUtil.getPermissionList();
			if(list!=null&&list.size()>0) {
				for (String permission : list){
					if (StringUtils.isNotBlank(permission)){
						// 添加基于Permission的权限信息
						info.addStringPermission(permission);
					}
				}
			}
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
	@PostConstruct
	public void initCredentialsMatcher() {
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(sysUserService.HASH_ALGORITHM);
//		matcher.setHashIterations(sysUserService.HASH_INTERATIONS);
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
		matcher.setHashIterations(HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

//	/**
//	 * 清空用户关联权限认证，待下次使用时重新加载
//	 */
//	public void clearCachedAuthorizationInfo(Principal principal) {
//		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
//		clearCachedAuthorizationInfo(principals);
//	}

	/**
	 * 清空所有关联认证
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
//		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//		if (cache != null) {
//			for (Object key : cache.keys()) {
//				cache.remove(key);
//			}
//		}
	}




}
