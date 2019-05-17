package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.sysbase.entity.DutyMenu;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职务表权限Dao
 * 2018年1月16日
 * @author lin
 */
public interface DutyMenuDao {
    
    /**
     * 查看当前职务下的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<MenuTreeModel> getMenuByDutyId(Integer id);
    
    /**
     * 单个插入
     * @param entity
     * @return
     * 2018年1月17日
     * @author lin
     */
    int insert(DutyMenu entity);
    
    /**
     * 批量插入
     * @param menu
     * @return
     * 2018年1月6日
     * @author lin
     */
    int insertList(List<DutyMenu> list);
    
    
    /**
     * 根据职务id查找对应的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
    List<Integer> queryMenuListByDuty(@Param("id") Integer id);
    /**
     * 删除职务时删除对应的权限
     * @param id
     * 2018年1月17日
     * @author lin
     */
    void deleteByDutyId(List<String> ids);
}