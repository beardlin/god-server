package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.sysbase.entity.RoleMenu;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限表Dao
 * 2018年1月16日
 * @author lin
 */
public interface RoleMenuDao {
    
    /**
     * 查看当前角色下的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<MenuTreeModel> getMenuByRoleId(Integer id);
    
    /**
     * 单个插入
     * @param entity
     * @return
     * 2018年1月17日
     * @author lin
     */
    int insert(RoleMenu entity);
    
    /**
     * 批量插入
     * @param menu
     * @return
     * 2018年1月6日
     * @author lin
     */
    int insertList(List<RoleMenu> list);
    
    
    /**
     * 根据角色id查找对应的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<Integer> queryMenuListByRole(@Param("id") Integer id);
    
    /**
     * 根据角色集合roles查找对应的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<Integer> queryMenuListByRoles(List<Integer> roles);
    
    /**
     * 删除角色时删除对应的权限
     * @param id
     * 2018年1月17日
     * @author lin
     */
    void deleteByRoleId(List<String> ids);
}