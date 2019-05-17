package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysRole;
import net.lantrack.framework.sysbase.entity.SysRoleExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 角色管理dao
 * 2018年1月4日
 * @author lin
 */
public interface SysRoleDao {
	 long countByExample(SysRoleExample example);

	    int deleteByExample(SysRoleExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(SysRole record);

	    int insertSelective(SysRole record);

	    List<SysRole> selectByExampleWithBLOBs(SysRoleExample example);

	    List<SysRole> selectByExample(SysRoleExample example);

	    SysRole selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	    int updateByExampleWithBLOBs(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	    int updateByPrimaryKeySelective(SysRole record);

	    int updateByPrimaryKeyWithBLOBs(SysRole record);

	    int updateByPrimaryKey(SysRole record);
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己在实现
     * @param page
     * @param role
     * @return List<SysDict>
     * 2018年1月4日
     * @author lin
     */
    public List<SysRole> getRoleListPage(@Param("page") PageEntity page, 
            @Param("temp") SysRole role) throws DaoException;
}