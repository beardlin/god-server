package net.lantrack.framework.sysbase.dao;

import java.util.List;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.entity.LoginInfoExample;
import org.apache.ibatis.annotations.Param;

public interface LoginInfoMapper {
    long countByExample(LoginInfoExample example);

    int deleteByExample(LoginInfoExample example);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    List<LoginInfo> selectByExample(LoginInfoExample example);

    int updateByExampleSelective(@Param("record") LoginInfo record, @Param("example") LoginInfoExample example);

    int updateByExample(@Param("record") LoginInfo record, @Param("example") LoginInfoExample example);
}