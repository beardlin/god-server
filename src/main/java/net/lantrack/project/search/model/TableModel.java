package net.lantrack.project.search.model;

import java.io.Serializable;

/**
 * 数据表基本信息（页面可编辑）
 * @Description:      
 * @author lin
 * @date 2018年7月9日
 */
public class TableModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 中文名称
	 */
	private String zhName;
	/**
	 * 数据库
	 */
	private String dbName;
	/**
	 * 引擎
	 */
	private String tableEngine;
	//是否默认选中
	private boolean checked = false;
	

	public TableModel(String tableName, String zhName, String dbName, String tableEngine) {
		super();
		this.tableName = tableName;
		this.zhName = zhName;
		this.dbName = dbName;
		this.tableEngine = tableEngine;
	}
	
	
	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getZhName() {
		return zhName;
	}
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTableEngine() {
		return tableEngine;
	}
	public void setTableEngine(String tableEngine) {
		this.tableEngine = tableEngine;
	}
	
	
	
}
