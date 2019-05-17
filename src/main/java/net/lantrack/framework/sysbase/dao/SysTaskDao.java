package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.sysbase.entity.SysTask;
import net.lantrack.framework.sysbase.entity.SysTaskExample;

import org.apache.ibatis.annotations.Param;

public interface SysTaskDao {
	long countByExample(SysTaskExample example);

    int deleteByExample(SysTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysTask record);

    int insertSelective(SysTask record);

    List<SysTask> selectByExample(SysTaskExample example);

    SysTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysTask record, @Param("example") SysTaskExample example);

    int updateByExample(@Param("record") SysTask record, @Param("example") SysTaskExample example);

    int updateByPrimaryKeySelective(SysTask record);

    int updateByPrimaryKey(SysTask record);

	public SysTask selectByJobName(String jobName);
}