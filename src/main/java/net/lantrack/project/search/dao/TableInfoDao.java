package net.lantrack.project.search.dao;

import java.util.List;
import java.util.Map;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.entity.TableInfoExample;
import org.apache.ibatis.annotations.Param;

public interface TableInfoDao {
	
	/**
	 * 查询自定义数据字段,带分页的话  以***ListPage命名
	 * @Description: 
	 * @author lin
	 * @date 2018年6月14日
	 */
	List<Map<String, Object>> selectDataBySqlListPage(@Param("customSql") String cusSql,@Param("page") PageEntity page,@Param("cond") String cond);
	
    long countByExample(TableInfoExample example);

    int deleteByExample(TableInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    List<TableInfo> selectByExample(TableInfoExample example);

    TableInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TableInfo record, @Param("example") TableInfoExample example);

    int updateByExample(@Param("record") TableInfo record, @Param("example") TableInfoExample example);

    int updateByPrimaryKeySelective(TableInfo record);

    int updateByPrimaryKey(TableInfo record);
}