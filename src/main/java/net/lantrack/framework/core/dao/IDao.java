package net.lantrack.framework.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 公用基础增删查改Dao
 * @author lin
 * @date 2018/07/15
 */
public interface IDao {
	/**
	 * 自定义Sql查询
	 * @param sql
	 * @return
	 */
	@Select("${querySql}")
	Map<String, Object> querySingleBySql(@Param("querySql") String querySql);

	/**
	 * 自定义Sql查询
	 * @param sql
	 * @return
	 */
	@Select("${querySql}")
	List<Map<String, Object>> queryListBySql(@Param("querySql") String querySql);
	/**
	 * 自定义修改
	 * @param updateSql
	 */
	@Update("${updateSql}")
	void updateBySql(@Param("updateSql") String updateSql);
	/**
	 * 自定义删除
	 * @param deleteSql
	 */
	@Delete("${deleteSql}")
	void deleteBySql(@Param("deleteSql") String deleteSql);
	/**
	 * 自定义插入
	 * @param insertSql
	 */
	@Insert("${insertSql}")
	void insertSql(@Param("insertSql") String insertSql);
	
	
}
