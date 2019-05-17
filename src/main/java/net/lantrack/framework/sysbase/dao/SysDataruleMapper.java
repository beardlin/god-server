package net.lantrack.framework.sysbase.dao;

import java.util.List;
import net.lantrack.framework.sysbase.entity.SysDatarule;
import net.lantrack.framework.sysbase.entity.SysDataruleExample;
import org.apache.ibatis.annotations.Param;

public interface SysDataruleMapper {
    long countByExample(SysDataruleExample example);

    int deleteByExample(SysDataruleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDatarule record);

    int insertSelective(SysDatarule record);

    List<SysDatarule> selectByExample(SysDataruleExample example);

    SysDatarule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysDatarule record, @Param("example") SysDataruleExample example);

    int updateByExample(@Param("record") SysDatarule record, @Param("example") SysDataruleExample example);

    int updateByPrimaryKeySelective(SysDatarule record);

    int updateByPrimaryKey(SysDatarule record);
}