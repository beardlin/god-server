package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.sysbase.entity.SysMenu;
import net.lantrack.framework.sysbase.entity.SysMenuExample;
import net.lantrack.framework.sysbase.entity.SysMenuModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;
/**
 * 菜单栏管理dao
 * 2018年1月4日
 * @author lin
 */
public interface SysMenuDao {
    
    /**
     * 修改儿子们的父级别菜单名称
     * @param menu
     * 2018年1月24日
     * @author lin
     */
    void updateChildParentName(@Param("menu") SysMenu menu);
    
    /**
     * 查询当前菜单下的所有permission
     * @param menuids
     * @return
     * 2018年1月18日
     * @author lin
     */
    List<String> queryPermission(List<Integer> menuids);
    /**
     * 查询所有权限，超级管理员所属
     * @return
     * 2018年1月23日
     * @author lin
     */
    List<String> queryAllPermission();
    
    /**
     * 根据父节点查找子节点
     * @param pid
     * @return
     * 2018年1月11日
     * @author lin
     */
    List<SysMenu> queryByPid(Integer pid);
    
    /**
     * 获取页面菜单 pid为1的
     * @param pid
     * @return
     * 2018年1月10日
     * @author lin
     */
    List<SysMenuModel> getIndexMenuByParentId(Integer pid);
    
    /**
     * 根据菜单id集合获取所有显示菜单集合
     * @param idList
     * @return
     * 2018年2月1日
     * @author hww
     */
    List<SysMenuModel> queryMenuListByidList(@Param("set") Set<Integer> idList);
    /**
     * 不论显示不显示都获取
     * @param idList
     * @return
     */
    List<SysMenuModel> queryModelListByidList(List<Integer> idList);
    
    /**
     * 根据菜单id集合获取对应权限集合
     * @param idList
     * @return
     * 2018年2月1日
     * @author hww
     */
    List<String> queryPermissionByidList(List<Integer> idList);
    
    /**
     * 条件查询个数，一般配合分页使用
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    long countByExample(SysMenuExample example);

    /**
     * 自定义条件删除
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByExample(SysMenuExample example);

    /**
     * 主键删除
     * @param id
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 对象insert
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int insert(SysMenu record);

    /**
     * 自定义列插入
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int insertSelective(SysMenu record);

    /**
     * 自定义条件查询
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    List<SysMenu> selectByExample(SysMenuExample example);

    /**
     * 通过主键查询
     */
    SysMenu selectByPrimaryKey(Integer id);

    /**
     * 只更新record中不为null字段，
     * 如果需要某几个列的修改，可以set到record 中，其余为null
     * example 用于添加调节，可用于批量操作
     * @param record
     * @param example
     * @return int
     * 2018年1月5日
     * @author lin
     */
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * 整个对象的更改
     * example 用于添加调节，可用于批量操作
     * @param record
     * @param example
     * @return int
     * 2018年1月5日
     * @author lin
     */
    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * 根据主键只更新部分字段
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * 根据主键更新整个对象
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKey(SysMenu record);
    /**
     * 获取当前节点里最大顺序
     * @param pid
     * @return
     * 2018年1月11日
     * @author lin
     */
    Integer getMaxSortByPid(Integer pid);
}