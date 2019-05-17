package net.lantrack.framework.sysbase.dao;

import java.util.List;
import net.lantrack.framework.sysbase.entity.SysFile;
import net.lantrack.framework.sysbase.entity.SysFileExample;
import org.apache.ibatis.annotations.Param;

public interface SysFileMapper {
	
	/**
	 * 自定义sql查询
	 * @param sql
	 * @return
	 * @date 2018年9月12日
	 */
	List<SysFile> searchBySql(@Param("querySql") String sql);
	
	/**
	 * 查询当前目录下的文件或文件夹
	 * @param pid
	 * @return
	 * @date 2018年9月11日
	 */
	List<SysFile> queryByPid(@Param("pId") Integer pid);
	
    long countByExample(SysFileExample example);

    int deleteByExample(SysFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    int insertSelective(SysFile record);

    List<SysFile> selectByExample(SysFileExample example);

    SysFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByExample(@Param("record") SysFile record, @Param("example") SysFileExample example);

    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);
}