package net.lantrack.framework.core.importexport.excel.reader;

import java.util.List;

public class ExcelInfo {

	/**
	 * 转换成哪种实体对象
	 */
	private Class<?> clazz;
	private Integer curretRowNum;
	private Integer totalCount;
	private List<Object> rowDatas;
	
	
	public ExcelInfo() {
		super();
	}
	public ExcelInfo(Class<?> clazz,Integer curretRowNum, Integer totalCount, List<Object> rowDatas) {
		super();
		this.clazz = clazz;
		this.curretRowNum = curretRowNum;
		this.totalCount = totalCount;
		this.rowDatas = rowDatas;
	}
	
	
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public Integer getCurretRowNum() {
		return curretRowNum;
	}
	public void setCurretRowNum(Integer curretRowNum) {
		this.curretRowNum = curretRowNum;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<Object> getRowDatas() {
		return rowDatas;
	}
	public void setRowDatas(List<Object> rowDatas) {
		this.rowDatas = rowDatas;
	}
	
	
	
}
