package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.sysbase.entity.UserRole;


/**
 * 用户角色Dao
 * 2018年1月18日
 * @author hww
 */
public interface UserRoleDao {

	/**
     * 单个插入
     * @param entity
     * @return
     * 2018年1月18日
     * @author hww
     */
    int insert(UserRole entity);
    
    /**
     * 批量插入
     * @param menu
     * @return
     * 2018年1月18日
     * @author hww
     */
    int insertList(List<UserRole> list);
    
    /**
     * 根据用户id获取角色id的集合
     * @param id
     * @return
     */
    List<Integer> queryRoleListByUser(String id);
    
    /**
     * 删除指定用户对应的所有角色
     * @param id
     * 2018年1月20日
     * @author hww
     */
    void deleteByUserId(List<String> ids);
	
}
