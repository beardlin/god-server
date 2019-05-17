package net.lantrack.project.search.service;

import java.util.List;

import net.lantrack.project.search.entity.ColumnInfo;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.model.ColumnModel;
import net.lantrack.project.search.model.ConfigModel;
import net.lantrack.project.search.model.TableModel;

/**
 * 获取系统元数据Service
 * @Description:      
 * @author lin
 * @date 2018年7月9日
 */
public interface MetaDataService {
	
	/**
	 * 获取当前表的字段列表
	 * @Description: 
	 * @author lin
	 * @date 2018年7月10日
	 */
	List<ColumnInfo> getColumnByTableId(String tbId);
	
	/**
	 * 初始化默认选中的表信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月10日
	 */
	List<TableModel> initCheckedTable(String id);
	
	TableInfo getTableInfoById(String id);
	
	/**
	 * 更新字段基本信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	void updateColumn(ColumnInfo entity);
	
	/**
	 * 删除表中的字段信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	void deleteColumns(String ids);
	
	/**
	 * 获取对应表字段信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	List<ColumnModel> getColumnsByTable(String tableName,String dbName);

	/**
	 * 配置元数据表
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	void configMetaData(ConfigModel configModel);
	
	/**
	 * 获取数据库中的所有表信息
	 * @Description: 
	 * @author lin
	 * @date 2018年7月9日
	 */
	List<TableModel> getDbTables();
	
	
}
