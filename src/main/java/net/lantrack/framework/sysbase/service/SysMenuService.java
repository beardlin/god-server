/**
 *
 */
package net.lantrack.framework.sysbase.service;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysMenu;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

import java.util.List;
import java.util.Set;


/**
 * 菜单管理Service
 * 2018年1月20日
 * @author lin
 */
public interface SysMenuService extends CrudService<SysMenu> {
	
	/**
	 * 修改菜单
	 * @param menu
	 * @date 2019年3月14日
	 */
	public void updateMenu(SysMenu menu);
    
    /**
     * 获取用户的所有权限菜单
     * @param userid
     * @return
     * 2018年1月18日
     * @author lin
     */
    public Set<Integer> getUserMenus(SysUser user);
    
    /**
     * 根据当前用户查取主页菜单
     * @return
     * 2018年1月18日
     * @author lin
     */
    public List<SysMenuModel> getMeunIndex(SysUser user);
    
    /**
     * 根据当前用户查取拥有权限
     * @return
     * 2018年1月18日
     * @author lin
     */
    public List<String> getUserPermission(SysUser user);
    

    /**
     * 获取菜单树，采用点击父节点获取子节点加载方式
     * @param pid
     * @return
     * @throws ServiceException
     * 2018年1月11日
     * @author lin
     */
    public List<MenuTreeModel> getTreeByPid(Integer pid) throws ServiceException;
    
    /**
     * 获取Admin系统菜单，父节点为'1'的,
     * @param parentId
     * @return
     * 2018年1月10日
     * @author lin
     */
    public List<SysMenuModel> getMenuAdmin(Integer parentId);
    /**
     * 查询所有权限Admin专属
     * @return
     * 2018年1月18日
     * @author lin
     */
    public List<String> getAdminPermission(Integer id);
    
        
    /**
     * 一次获取所有的权限菜单树
     * @return
     */
    public List<MenuTreeModel> getAllMenuTree();
    /**
     * 根据用户所拥有的menuIds获取对应的权限树
     * @param menuIds
     * @return
     */
    public List<MenuTreeModel> getUserMenuTreeModels(Set<Integer> menuIds);

}