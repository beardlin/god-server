package net.lantrack.framework.sysbase.dao;

import java.util.List;
import net.lantrack.framework.sysbase.entity.IdUser;
import net.lantrack.framework.sysbase.entity.IdUserExample;
import org.apache.ibatis.annotations.Param;

public interface IdUserDao {
    
    /**
     * 批量插入
     * @param userlist
     * @return
     * 2018年1月6日
     * @author lin
     */
    int insertList(List<IdUser> userlist);
    
    long countByExample(IdUserExample example);

    int deleteByExample(IdUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IdUser record);

    int insertSelective(IdUser record);

    List<IdUser> selectByExample(IdUserExample example);

    IdUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IdUser record, @Param("example") IdUserExample example);

    int updateByExample(@Param("record") IdUser record, @Param("example") IdUserExample example);

    int updateByPrimaryKeySelective(IdUser record);

    int updateByPrimaryKey(IdUser record);
}