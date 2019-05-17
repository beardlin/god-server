package net.lantrack.framework.sysbase.model.updownfile;

import java.util.List;

import net.lantrack.framework.core.entity.MapEntity;

public class FileAnalyze {

	/**
	 * 磁盘总空间大小
	 */
	private String diskTotalSize;
	
	/**
	 * 磁盘剩余空间
	 */
	private String remainSpase;
	
	/**
	 * 文件总个数
	 */
	private String totalCount;
	/**
	 * 文件总大小
	 */
	private String totalSize;
	/**
	 * 临时文件总个数
	 */
	private String tempCount;
	/**
	 * 临时文件总大小
	 */
	private String tempSize;
	
	/**
	 * 各表单所拥有的附件数量
	 */
	private List<MapEntity> tableAnalyzCount;
	
	/**
	 * 各表单所拥有的附件大小
	 */
	private List<MapEntity> tableAnalyzSize;
	
	/**
	 * 各附件类型数量统计
	 */
	private List<MapEntity> typeAnalyzCount;
	
	/**
	 * 各附件类型大小统计
	 */
	private List<MapEntity> typeAnalyzSize;

	public String getDiskTotalSize() {
		return diskTotalSize;
	}

	public void setDiskTotalSize(String diskTotalSize) {
		this.diskTotalSize = diskTotalSize;
	}

	public String getRemainSpase() {
		return remainSpase;
	}

	public void setRemainSpase(String remainSpase) {
		this.remainSpase = remainSpase;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}

	public String getTempCount() {
		return tempCount;
	}

	public void setTempCount(String tempCount) {
		this.tempCount = tempCount;
	}

	public String getTempSize() {
		return tempSize;
	}

	public void setTempSize(String tempSize) {
		this.tempSize = tempSize;
	}

	public List<MapEntity> getTableAnalyzCount() {
		return tableAnalyzCount;
	}

	public void setTableAnalyzCount(List<MapEntity> tableAnalyzCount) {
		this.tableAnalyzCount = tableAnalyzCount;
	}

	public List<MapEntity> getTableAnalyzSize() {
		return tableAnalyzSize;
	}

	public void setTableAnalyzSize(List<MapEntity> tableAnalyzSize) {
		this.tableAnalyzSize = tableAnalyzSize;
	}

	public List<MapEntity> getTypeAnalyzCount() {
		return typeAnalyzCount;
	}

	public void setTypeAnalyzCount(List<MapEntity> typeAnalyzCount) {
		this.typeAnalyzCount = typeAnalyzCount;
	}

	public List<MapEntity> getTypeAnalyzSize() {
		return typeAnalyzSize;
	}

	public void setTypeAnalyzSize(List<MapEntity> typeAnalyzSize) {
		this.typeAnalyzSize = typeAnalyzSize;
	}
	
	
	
}
