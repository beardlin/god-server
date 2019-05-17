package net.lantrack.framework.sysbase.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.user.UserAuthModel;
import net.lantrack.framework.sysbase.service.AuthorizingService;
import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.util.UserUtil;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	AuthorizingService authorizingService;
	
	/**
	 * 获取当前在线人数
	 * auth/sessions
	 */
	@RequestMapping("/sessions")
	public ReturnEntity sessions(ReturnEntity info) {
		info.setResult(UserUtil.onlineCount());
		return info;
	}
	
	/**
	 * 获取当前已有角色下的权限id
	 * auth/menus
	 */
	@RequestMapping("menus")
	public ReturnEntity getMenus(@RequestBody Map<String, String> parms, ReturnEntity info) {
		String roleid = parms.get("roleid");
		try {
			Set<Integer> menuIds = this.authorizingService.getMenuIdsByRoleId(roleid);
			info.setResult(menuIds);
		} catch (Exception e) {
			info.failed("获取失败");
		}
		return info;
	}
	
	/**
	 * 获取当前已有角色权限
	 * auth/get
	 */
	@RequestMapping("get")
	public ReturnEntity getAuthorize(@RequestBody Map<String, String> parms, ReturnEntity info) {
		String userid = parms.get("userid");
		try {
			List<Integer> roleIds = this.authorizingService.getRoleIdsByUserId(userid);
			Set<Integer> menuIds = this.authorizingService.getMenuIdsByUserId(userid);
			Map<String, Object> result = new HashMap<>();
			result.put("rold", roleIds);
			result.put("menu", menuIds);
			info.setResult(result);
		} catch (Exception e) {
			info.failed("获取失败");
		}
		return info;
	}
	
	/**
	 * 给多个用户批量授权
	 * auth/authorize
	 */
	@RequestMapping("authorize")
	@LogDesc(value="配置权限",type=LogType.UPDATE)
	public ReturnEntity authorizeBatch(@RequestBody UserAuthModel model, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:auth:authorize")) {
//            return info;    
//        }
		try {
			if("0".equals(UserUtil.getUser().getAccreditFlag())) {
				info.failed("您没有授权权限");
				return info;
			}
			this.sysUserService.configRoles(model.getUserids(), model.getRoles());
		} catch (Exception e) {
			info.failed("配置失败");
		}
		return info;
	}
}
