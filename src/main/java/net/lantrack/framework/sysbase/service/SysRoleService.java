package net.lantrack.framework.sysbase.service;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysRole;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

import java.util.List;
import java.util.Map;


/**
 * 角色管理
 * 2018年1月18日
 * @author lin
 */
public interface SysRoleService extends CrudService<SysRole> {
	
	
	/**
	 * 根据用户获取角色列表
	 * @param userId
	 * @return
	 * @date 2019年3月21日
	 */
	List<SysRole> getRoleListByUserId(String userId);
	
	/**
	 * 保存带权限参数的角色
	 * @param entity
	 * @param menus
	 * @return
	 * @throws ServiceException
	 */
	public SysRole save(SysRole entity, String menus) throws ServiceException; 
	
	/**
	 * 修改带权限参数的角色
	 * @param entity
	 * @param menus
	 * @return
	 * @throws ServiceException
	 */
	public SysRole update(SysRole entity, String menus) throws ServiceException; 

    /**
     * 查看当前角色下的权限树
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<MenuTreeModel> getMenuTreeByRoleId(Integer id);
    
    /**
     * 获取当前角色所能见到的菜单集合
     * @param id
     * @return
     * 2018年2月1日
     * @author hww
     */
    List<SysMenuModel> getSysMenuListByRoleId(Integer id);
    
    /**
     * 给当前角色添加权限
     * @param id 当前职务id
     * @param menus  所勾选的菜单id
     * 2018年1月17日
     * @author lin
     */
    void addPermission(Integer id,String menus);
    
    /**
     * 根据用户id以Map形式返回其能见所有的角色
     * @return map 
     * 2018年1月18日
     * @author hww
     */
	public Map<Integer, String> getAll(String userId);
	
	/**
	 * 根据角色id获取全部权限列表和所操作角色目前已有的权限列表的map
	 * @return map
	 */
	public Map<String, Object> getMenuMapByRoleId(Integer id);
	
	/**
	 * 根据勾选的角色ids获取对应权限id的并集
	 * @param ids
	 * @return
	 */
	public List<Integer> getMenuListByRoleIds(String ids);
}