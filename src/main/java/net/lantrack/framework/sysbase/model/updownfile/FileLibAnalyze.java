package net.lantrack.framework.sysbase.model.updownfile;

import java.util.List;

import net.lantrack.framework.core.entity.MapEntity;

/**
 * 资料库统计
 *       
 * @date 2019年4月22日
 */
public class FileLibAnalyze {
	
	/**
	 * 文件总个数
	 */
	private String totalCount;
	/**
	 * 文件总大小
	 */
	private String totalSize;
	
	
	/**
	 * 各附件类型数量统计
	 */
	private List<MapEntity> typeAnalyzCount;
	
	/**
	 * 各附件类型大小统计
	 */
	private List<MapEntity> typeAnalyzSize;

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
