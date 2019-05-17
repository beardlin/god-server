package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysLog;
import net.lantrack.framework.sysbase.entity.SysLogExample;
import net.lantrack.framework.sysbase.model.log.LogModel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 系统日志管理dao 
 * 2018年1月4日
 * @author lin
 */
public interface SysLogDao {
    
    /**
     * 清空日志
     * @return
     * 2018年1月24日
     * @author lin
     */
    long clearLog();
    
	/**
     * 根据条件获取日志表（sys_log）里筛选出的日志总数
     * @param example SysLogExample类型
     * @return 总数  long类型
     */
    long countByExample(SysLogExample example);

    /**
     * 根据条件物理删除日志表（sys_log）里筛选出的日志记录，返回删除成功的总条数
     * @param example SysLogExample类型
     * @return 删除成功的总条数   int类型
     */
    int deleteByExample(SysLogExample example);
    

    /**
     * 根据主键id物理删除日志表（sys_log）里指定的日志记录，返回删除成功的条数
     * @param id Long类型
     * @return 删除成功的条数    int类型
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 向日志表（sys_log）里插入一条完整的日志记录，返回插入成功的条数
     * @param record SysLog类型
     * @return 插入成功的条数    int类型
     */
    int insert(SysLog record);

    /**
     * 向日志表（sys_log）里插入一条日志记录，只给属性值不为null的字段赋值，返回插入成功的条数
     * @param record SysLog类型
     * @return 插入成功的条数    int类型
     */
    int insertSelective(SysLog record);

    /**
     * 根据条件获取日志表（sys_log）里筛选出的日志集合
     * @param example SysLogExample类型
     * @return 日志集合  List<SysLog>类型
     */
    List<SysLog> selectByExample(SysLogExample example);

    /**
     * 根据主键id获取日志表（sys_log）里指定的日志记录
     * @param id Integer类型
     * @return 日志记录    SysLog类型
     */
    LogModel selectByPrimaryKey(long id);

    
    
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己再实现
     * @param page
     * @param log
     * @return List<SysLog>
     */
    public List<LogModel> getLogListPage(@Param("page") PageEntity page, 
            @Param("log") SysLog log) throws DaoException;
   

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);
    
    
    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);


    int updateByPrimaryKey(SysLog record);
}