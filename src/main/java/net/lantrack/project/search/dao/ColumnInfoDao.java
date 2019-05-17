package net.lantrack.project.search.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.project.search.entity.ColumnInfo;
import net.lantrack.project.search.entity.ColumnInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ColumnInfoDao {
	
	/**
	 * 批量插入字段信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	int insertList(List<ColumnInfo> list);
	
	/**
	 * 分页信息   名称以  ***ListPage命名
	 * @Description: 
	 * @author lin
	 * @date 2018年6月13日
	 */
	List<ColumnInfo> getListPage(@Param("tableId") Integer tableId,@Param("page") PageEntity page);
	
    long countByExample(ColumnInfoExample example);

    int deleteByExample(ColumnInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ColumnInfo record);

    int insertSelective(ColumnInfo record);

    List<ColumnInfo> selectByExample(ColumnInfoExample example);

    ColumnInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ColumnInfo record, @Param("example") ColumnInfoExample example);

    int updateByExample(@Param("record") ColumnInfo record, @Param("example") ColumnInfoExample example);

    int updateByPrimaryKeySelective(ColumnInfo record);

    int updateByPrimaryKey(ColumnInfo record);
}