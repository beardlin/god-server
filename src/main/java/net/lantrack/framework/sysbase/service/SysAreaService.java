package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysArea;

/**
 * 行政区域Service
 * 2018年1月17日
 * @author hww
 */
public interface SysAreaService extends CrudService<SysArea> {

	/**
	 * 根据当前节点的id查询其儿子节点集合
	 * @param id 当前节点id String类型
	 * @return list 儿子节点集合
	 */
	public List<SysArea> getChildrenById(String id);
	
}