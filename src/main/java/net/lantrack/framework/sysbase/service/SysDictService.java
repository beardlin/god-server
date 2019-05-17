/**
 *
 */
package net.lantrack.framework.sysbase.service;

import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 字典管理Service
 * 2018年1月24日
 * @author lin
 */
public interface SysDictService extends CrudService<SysDict> {

    /**
     * 获取字典中的所有字典类型
     * @return
     * 2018年1月24日
     * @author lin
     */
    public Map<String, String> getDictTypeAll();
    
	/**
	 * 根据性能要求，重写此方法， 并自己传参数
	 * @param entity SysDict类型
	 * @param pleaseSelect int类型  是否默认请选择 0是 ，1否
	 * @return map <value, label>
	 */
	public Map<String, String> getDictMap(SysDict entity, int pleaseSelect);
	
	/**
	 * 根据条件列表查询所有的字典信息
	 */
	public List<SysDict> getAll(SysDict entity);
}