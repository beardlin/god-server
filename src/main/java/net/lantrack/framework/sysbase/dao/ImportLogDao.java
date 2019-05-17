package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.ImportLog;
import net.lantrack.framework.sysbase.entity.ImportLogExample;

import org.apache.ibatis.annotations.Param;

public interface ImportLogDao {
    long countByExample(ImportLogExample example);

    int deleteByExample(ImportLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImportLog record);

    int insertSelective(ImportLog record);

    List<ImportLog> selectByExample(ImportLogExample example);

    ImportLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImportLog record, @Param("example") ImportLogExample example);

    int updateByExample(@Param("record") ImportLog record, @Param("example") ImportLogExample example);

    int updateByPrimaryKeySelective(ImportLog record);

    int updateByPrimaryKey(ImportLog record);
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己再实现
     * @param page
     * @param log
     * @return List<SysLog>
     */
    public List<ImportLog> getLogListPage(@Param("page") PageEntity page, 
            @Param("log") ImportLog log) throws DaoException;
}