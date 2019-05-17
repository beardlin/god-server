package net.lantrack.project.search.model;
/**
 * 配置添加元数据
 * @Description:      
 * @author lin
 * @date 2018年7月9日
 */

import java.util.List;

public class ConfigModel {
	/**
	 * 选中的树节点id
	 */
	private String id;
	/**
	 * 选中的表信息
	 */
	private List<TableModel> tables;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<TableModel> getTables() {
		return tables;
	}
	public void setTables(List<TableModel> tables) {
		this.tables = tables;
	}
	
}
