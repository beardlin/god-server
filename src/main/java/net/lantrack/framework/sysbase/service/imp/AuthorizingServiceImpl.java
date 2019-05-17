package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.sysbase.dao.SysMenuDao;
import net.lantrack.framework.sysbase.dao.SysRoleDao;
import net.lantrack.framework.sysbase.dao.UserRoleDao;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysRole;
import net.lantrack.framework.sysbase.entity.SysRoleExample;
import net.lantrack.framework.sysbase.entity.SysRoleExample.Criteria;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.AuthorizingService;
import net.lantrack.framework.sysbase.service.SysRoleService;
@Service
public class AuthorizingServiceImpl extends BaseService implements AuthorizingService {

	@Autowired
	protected SysRoleDao sysRoleDao; 
	
	@Autowired
	protected UserRoleDao userRoleDao; 
	
	@Autowired
	protected SysMenuDao sysMenuDao;
	
	@Autowired
	protected SysRoleService sysRoleService;
	
	
	
	@Override
	public List<String> getPermissions(String userId) {
		List<String> permissions = new ArrayList<>();
		if(StringUtils.isBlank(userId)) {
			return permissions;
		}
		try {
			//获取当前用户的权限id
			Set<Integer> menuIds = getMenuIdsByUserId(userId);
			if(menuIds==null||menuIds.size()==0) {
				return permissions;
			}
			List<Integer> listid = new ArrayList<>(menuIds);
			//查询权限标识符
			permissions = this.sysMenuDao.queryPermission(listid);
		} catch (Exception e) {
			printException(e);
		}
		return permissions;
	}

	@Override
	public List<MenuTreeModel> getMenuTree(String userId) {
		List<MenuTreeModel> menusTree = new ArrayList<>();
		try {
			//获取当前用户的权限id
			Set<Integer> menuIds = getMenuIdsByUserId(userId);
			if(menuIds==null||menuIds.size()==0) {
				return menusTree;
			}
			//查询菜单树
			List<SysMenuModel> list = this.sysMenuDao.queryMenuListByidList(menuIds);
			for(SysMenuModel sm:list) {
				MenuTreeModel tree = new MenuTreeModel(sm.getId(), sm.getParentId(), sm.getMenuName(),sm.getSort());
				menusTree.add(tree);
			}
			
		} catch (Exception e) {
			printException(e);
		}
		return menusTree;
	}

	@Override
	public Set<Integer> getMenuIdsByUserId(String userId) {
		Set<Integer> menuIds = new HashSet<Integer>();
		if(StringUtils.isBlank(userId)) {
			return menuIds;
		}
		try {
			//获取当前用户拥有角色
			List<Integer> roleIds = getRoleIdsByUserId(userId);
			if(roleIds==null||roleIds.size()==0) {
				return menuIds;
			}
			SysRoleExample example = new SysRoleExample();
			Criteria cr = example.createCriteria();
			cr.andIdIntegerIn(roleIds);
			//查询当前角色下的权限
			List<SysRole> roles = this.sysRoleDao.selectByExampleWithBLOBs(example);
			for(SysRole role:roles) {
				String permission = role.getPermission();
				String[] split = permission.split(",");
				for(String p:split) {
					menuIds.add(Integer.valueOf(p));
				}
			}
		}catch(Exception e){
			printException(e);
		}
		return menuIds;
	}

	@Override
	public List<Integer> getRoleIdsByUserId(String userId) {
		List<Integer> roleIds = new ArrayList<>();
		if(StringUtils.isBlank(userId)) {
			return roleIds;
		}
		try {
			//获取当前用户拥有角色
			roleIds = userRoleDao.queryRoleListByUser(userId);
		} catch (Exception e) {
			printException(e);
		}
		return roleIds;
	}

	@Override
	public Set<Integer> getMenuIdsByRoleId(String roleid) {
		Set<Integer> menuIds = new HashSet<Integer>();
		if(StringUtils.isBlank(roleid)) {
			return menuIds;
		}
		try {
			//查询当前角色下的权限
			SysRole role = this.sysRoleDao.selectByPrimaryKey(Integer.valueOf(roleid));
			if(role==null) {
				return menuIds;
			}
			String permission = role.getPermission();
			String[] split = permission.split(",");
			for(String p:split) {
				menuIds.add(Integer.valueOf(p));
			}
		}catch(Exception e){
			printException(e);
		}
		return menuIds;
	}
	
	

}
