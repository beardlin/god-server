package net.lantrack.framework.sysbase.model;

import java.io.Serializable;

import net.lantrack.framework.core.anno.ColumnMapping;
/**
 * 树形表model
 * @author lin
 */
@SuppressWarnings("serial")
public class TreeModel implements Serializable{

	@ColumnMapping("id")
	private String treeId;
	@ColumnMapping("p_id")
	private String treePid;
	@ColumnMapping("t_name")
	private String treeName;
	
	private String treeSort;
	
	
	public TreeModel(String treeId, String treePid, String treeName, String treeSort) {
		super();
		this.treeId = treeId;
		this.treePid = treePid;
		this.treeName = treeName;
		this.treeSort = treeSort;
	}
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public String getTreePid() {
		return treePid;
	}
	public void setTreePid(String treePid) {
		this.treePid = treePid;
	}
	public String getTreeName() {
		return treeName;
	}
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	public String getTreeSort() {
		return treeSort;
	}
	public void setTreeSort(String treeSort) {
		this.treeSort = treeSort;
	}
	
	
	
	
}
