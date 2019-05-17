package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.entity.SysUserExample;
import net.lantrack.framework.sysbase.model.user.UserModel;

import org.apache.ibatis.annotations.Param;
/**
 * SysUserDao 
 * 2018年1月3日
 * @author lin
 */
public interface SysUserDao{
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己在实现
     * @param page
     * @param user
     * @return List<SysUser>
     * 2018年1月4日
     * @author lin
     */
    public List<UserModel> getUserListPage(@Param("page") PageEntity page, 
            @Param("user") SysUser user) throws DaoException;
    /**
     * 多表分页，自己实现
     * @param list
     * @return
     */
    public List<UserModel> getPageByOfficeId(@Param("list") List<String> list, @Param("userName") String userName, @Param("ifAdmin") String ifAdmin, @Param("id") String id, @Param("startIndex") Integer startIndex, @Param("perPageCount") Integer perPageSize);
    
    
    public Integer getCountByOfficeId(@Param("list") List<String> list, @Param("userName") String userName);
    
    /**
     * 批量插入
     * @param list
     * @return
     * 2018年1月17日
     * @author hww
     */
    int insertList(List<SysUser> list);
    
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);
    
    SysUser selectByPrimaryKey(String id);
    
    public UserModel queryBy(String id);
    
    UserModel selectModelByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    
}