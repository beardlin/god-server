package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.Duty;
import net.lantrack.framework.sysbase.entity.DutyExample;
import org.apache.ibatis.annotations.Param;

/**
 * 职务表Dao
 * 2018年1月16日
 * @author lin
 */
public interface DutyDao {
    /**
     * 条件查询个数，一般配合分页使用
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    long countByExample(DutyExample example);

    /**
     * 自定义条件删除
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByExample(DutyExample example);
    /**
     * 主键删除
     * @param id
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Duty record);
    /**
     * 自定义列插入
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int insertSelective(Duty record);
    /**
     * 自定义条件查询
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    List<Duty> selectByExample(DutyExample example);
    /**
     * 主键查询
     * @param id
     * @return
     * 2018年1月5日
     * @author lin
     */
    Duty selectByPrimaryKey(Integer id);
    /**
     * 只更新record中不为null字段，
     * 如果需要某几个列的修改，可以set到record 中，其余为null
     * example 用于添加调节，可用于批量操作
     * @param record
     * @param example
     * @return int
     * 2018年1月5日
     * @author lin
     */
    int updateByExampleSelective(@Param("record") Duty record, @Param("example") DutyExample example);
    /**
     * 整个对象的更改
     * example 用于添加调节，可用于批量操作
     * @param record
     * @param example
     * @return int
     * 2018年1月5日
     * @author lin
     */
    int updateByExample(@Param("record") Duty record, @Param("example") DutyExample example);
    /**
     * 根据主键只更新部分字段
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKeySelective(Duty record);
    /**
     * 根据主键更新整个对象
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKey(Duty record);
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己在实现
     * @param page
     * @param dict
     * @return List<SysDict>
     * 2018年1月4日
     * @author lin
     */
    public List<Duty> getDutyListPage(@Param("page") PageEntity page, 
            @Param("temp") Duty dict) throws DaoException;
}