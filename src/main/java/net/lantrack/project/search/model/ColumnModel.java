package net.lantrack.project.search.model;

import java.io.Serializable;

/**
 * 数据表字段信息
 * 
 * @Description:
 * @author lin
 * @date 2018年7月9日
 */
public class ColumnModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String columnName;
	private String zhName;
	private String dataType;
	private String dataLength;

	public ColumnModel(String columnName, String zhName, String dataType, String dataLength) {
		super();
		this.columnName = columnName;
		this.zhName = zhName;
		this.dataType = dataType;
		this.dataLength = dataLength;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	@Override
	public String toString() {
		return "ColumnModel [columnName=" + columnName + ", zhName=" + zhName + ", dataType=" + dataType
				+ ", dataLength=" + dataLength + "]";
	}

	
}
