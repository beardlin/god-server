package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.sysbase.entity.UserMenu;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权限表Dao
 * 2018年1月16日
 * @author lin
 */
public interface UserMenuDao {
    
    /**
     * 查看当前用户下的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<MenuTreeModel> getMenuByUserId(String id);
    
    /**
     * 单个插入
     * @param entity
     * @return
     * 2018年1月17日
     * @author lin
     */
    int insert(UserMenu entity);
    
    /**
     * 批量插入
     * @param menu
     * @return
     * 2018年1月6日
     * @author lin
     */
    int insertList(List<UserMenu> list);
    
    
    /**
     * 根据用户id查找对应的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<Integer> queryMenuListByUser(@Param("id") String id);
    
    /**
     * 删除指定用户对应的所有权限关系
     * @param id
     * 2018年1月20日
     * @author hww
     */
    void deleteByUserId(List<String> ids);
    
}