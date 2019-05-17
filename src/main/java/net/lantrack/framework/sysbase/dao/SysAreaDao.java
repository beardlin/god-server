package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysArea;
import net.lantrack.framework.sysbase.entity.SysAreaExample;
import net.lantrack.framework.sysbase.model.area.AreaModel;

import org.apache.ibatis.annotations.Param;

public interface SysAreaDao {
    /**
     * 根据条件获取区域表（sys_area）里筛选出的区域总数
     * @param example SysAreaExample类型
     * @return 总数  long类型
     */
    long countByExample(SysAreaExample example);

    /**
     * 根据条件物理删除区域表（sys_area）里筛选出的区域记录，返回删除成功的总条数
     * @param example SysAreaExample类型
     * @return 删除成功的总条数   int类型
     */
    int deleteByExample(SysAreaExample example);

    /**
     * 根据主键id物理删除区域表（sys_area）里指定的区域记录，返回删除成功的条数
     * @param id Integer类型
     * @return 删除成功的条数    int类型
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 向区域表（sys_area）里插入一条完整的区域记录，返回插入成功的条数
     * @param record SysArea类型
     * @return 插入成功的条数    int类型
     */
    int insert(SysArea record);

    /**
     * 向区域表（sys_area）里插入一条区域记录，只给属性值不为null的字段赋值，返回插入成功的条数
     * @param record SysArea类型
     * @return 插入成功的条数    int类型
     */
    int insertSelective(SysArea record);

    /**
     * 根据条件获取区域表（sys_area）里筛选出的区域集合
     * @param example SysAreaExample类型
     * @return 区域集合  List<SysArea>类型
     */
    List<SysArea> selectByExample(SysAreaExample example);

    /**
     * 根据主键id获取区域表（sys_area）里指定的区域记录
     * @param id Integer类型
     * @return 区域记录    SysArea类型
     */
    SysArea selectByPrimaryKey(Integer id);

    /**
     * 修改区域表（sys_area）里根据条件example过滤出的区域记录，需要修改的字段来源于参数record里不为null的属性
     * @param record  包含所要修改的属性值的SysArea对象
     * @param example 包含所要过滤的条件的SysAreaExample对象
     * @return 修改成功的总条数  int类型
     */
    int updateByExampleSelective(@Param("record") SysArea record, @Param("example") SysAreaExample example);

    /**
     * 修改区域表（sys_area）里根据条件example过滤出的区域记录，将要修改的字段是参数record的全部属性
     * @param record  包含所要修改的属性值的SysArea对象
     * @param example 包含所要过滤的条件的SysAreaExample对象
     * @return 修改成功的总条数  int类型
     */
    int updateByExample(@Param("record") SysArea record, @Param("example") SysAreaExample example);

    /**
     * 根据主键id修改区域表（sys_area）里指定的区域记录，需要修改的字段来源于参数record里不为null的属性
     * @param record SysArea
     * @return 修改成功的条数  int类型
     */
    int updateByPrimaryKeySelective(SysArea record);

    /**
     * 根据主键id修改区域表（sys_area）里指定的区域记录，将要修改的字段是参数record的全部属性
     * @param record SysArea
     * @return 修改成功的条数  int类型
     */
    int updateByPrimaryKey(SysArea record);
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己再实现
     * @param page
     * @param area
     * @return List<SysArea>
     * 2018年1月9日
     * @author hww
     */
    public List<AreaModel> getAreaListPage(@Param("page") PageEntity page, 
            @Param("area") SysArea area) throws DaoException;
    
    
    
}