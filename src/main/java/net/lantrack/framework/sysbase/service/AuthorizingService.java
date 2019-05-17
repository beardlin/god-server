package net.lantrack.framework.sysbase.service;

import java.util.List;
import java.util.Set;

import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

/**
 * 获取授权
 * @date 2019年3月8日
 */
public interface AuthorizingService {
	

	/**
	 * 获取当前用户权限List
	 * @param userId
	 * @return
	 * @date 2019年3月8日
	 */
	Set<Integer> getMenuIdsByRoleId(String roleid);
	
	/**
	 * 获取当前用户权限List
	 * @param userId
	 * @return
	 * @date 2019年3月8日
	 */
	Set<Integer> getMenuIdsByUserId(String userId);

	/**
	 * 获取当前用户角色List
	 * @param userId
	 * @return
	 * @date 2019年3月8日
	 */
	List<Integer> getRoleIdsByUserId(String userId);
	
	/**
	 * 获取当前用户权限
	 * @param userId
	 * @return
	 * @date 2019年3月8日
	 */
	List<MenuTreeModel> getMenuTree(String userId);
	
	/**
	 * 获取当前用户权限标识符
	 * @param userId
	 * @return
	 * @date 2019年3月8日
	 */
	List<String> getPermissions(String userId);
	
	
}
