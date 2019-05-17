package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.sysbase.model.TreeModel;

/**
 * 维护所有树形结构接口
 * 树形结构表设计必须字段  id,p_id,p_ids,t_name,p_name,full_name,o_sort
 * @author lin
 *
 */
public interface TreeService {
	
	/**
	 * 获取树接口
	 * @param pid
	 * @return
	 */
	public List<TreeModel> getTree(String pid);
	
	
	/**
	 * 删除树节点
	 * @param id
	 * @return
	 */
	public String deleteTreeNode(String id);
	/**
	 * 修改树节点
	 * @param id
	 * @param nodeName
	 * @return
	 */
	public String updateTreeNode(String id,String nodeName);
	
	/**
	 * 添加树节点
	 * @param treeId
	 * @param nodeName
	 * @return
	 */
	public String addTreeNode(String pId,String nodeName);
	
}
