package net.lantrack.framework.core.datasource;
/**
 * 此处将配置文件中的数据源都添加到枚举中，便于维护      
 * @author lin
 * @date 2018年7月23日
 */
public enum SourceName {

	DATASOURCE("dataSource"),
	DATASOURCE1("dataSource1"),
	DATASOURCE2("dataSource2");
	
	private String name;

	private SourceName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
