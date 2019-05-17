package net.lantrack.framework.sysbase.dao;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysDict;
import net.lantrack.framework.sysbase.entity.SysDictExample;
import net.lantrack.framework.sysbase.model.dict.DictModel;
import net.lantrack.framework.sysbase.model.dict.DictTypeModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 
 * 系统字典管理Dao
 * 2018年1月4日
 * @author lin
 */
public interface SysDictDao {
    
    /**
     * 获取字典类型接口，type+descrip去重
     * @return
     * 2018年1月24日
     * @author lin
     */
    List<DictTypeModel> getDictType();
    /**
     * 条件查询个数，一般配合分页使用
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    long countByExample(SysDictExample example);

    /**
     * 自定义条件删除
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByExample(SysDictExample example);

    /**
     * 主键删除
     * @param id
     * @return
     * 2018年1月5日
     * @author lin
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 对象insert
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int insert(SysDict record);

    /**
     * 自定义列插入
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int insertSelective(SysDict record);

    /**
     * 自定义条件查询
     * @param example
     * @return
     * 2018年1月5日
     * @author lin
     */
    List<SysDict> selectByExample(SysDictExample example);

    /**
     * 主键查询
     * @param id
     * @return
     * 2018年1月5日
     * @author lin
     */
    SysDict selectByPrimaryKey(Integer id);

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
    int updateByExampleSelective(@Param("record") SysDict record, @Param("example") SysDictExample example);
    /**
     * 整个对象的更改
     * example 用于添加调节，可用于批量操作
     * @param record
     * @param example
     * @return int
     * 2018年1月5日
     * @author lin
     */
    int updateByExample(@Param("record") SysDict record, @Param("example") SysDictExample example);
    /**
     * 根据主键只更新部分字段
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKeySelective(SysDict record);

    /**
     * 根据主键更新整个对象
     * @param record
     * @return
     * 2018年1月5日
     * @author lin
     */
    int updateByPrimaryKey(SysDict record);
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己在实现
     * @param page
     * @param dict
     * @return List<SysDict>
     * 2018年1月4日
     * @author lin
     */
    public List<SysDict> getDictListPage(@Param("page") PageEntity page, 
            @Param("temp") SysDict dict) throws DaoException;
    /**
     * 根据字典类别和所属科室查询字典
     * @param type
     * @param depart
     * @return
     * 2018年3月27日
     * @author lin
     */
    List<DictModel> getDictByTypeAndPid(@Param("type") String type,
            @Param("depart") Integer depart);
}