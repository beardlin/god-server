package net.lantrack.project.search.model;

import java.io.Serializable;

/**
 * 自定义条件查询条件
 * @Description:      
 * @author lin
 */
public class CustomCondtion implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询字段
	 */
	private String field;
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 查询条件  =,>,<,<=,>=,like,
	 */
	private String condtion;
	/**
	 * 查询值
	 */
	private String value;
	/**
	 * 连接条件 AND OR
	 */ 
	private String concatCond;
	
	
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCondtion() {
		return condtion;
	}
	public void setCondtion(String condtion) {
		this.condtion = condtion;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getConcatCond() {
		return concatCond;
	}
	public void setConcatCond(String concatCond) {
		this.concatCond = concatCond;
	}
	
	
	
	
}
