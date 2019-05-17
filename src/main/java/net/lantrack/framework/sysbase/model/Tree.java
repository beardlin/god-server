package net.lantrack.framework.sysbase.model;

import java.util.Collections;
import java.util.List;


/**
 * Tree组件结构
 *       
 * @date 2019年3月28日
 */
public class Tree implements Comparable<Tree>{
	
	private String id;
	private String pid;
	private String label;
	private Integer sort=0;
	private List<Tree> children;
	
	public Tree(String id, String pid, String name,Integer sort) {
		super();
		this.id = id;
		this.pid = pid;
		this.label = name;
		this.sort = sort;
	}
	public Tree(String id, String pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.label = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Tree() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		Collections.sort(children);
		this.children = children;
	}
	
	@Override
	public int compareTo(Tree o) {
		if(o.sort!=null && this.sort != null) {
			return this.sort - o.sort;
		}
		return 0;
	}
}
