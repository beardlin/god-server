package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.sysbase.entity.SysDatarule;
import net.lantrack.framework.sysbase.model.menu.DataRuleTree;

/**
 * 数据规则      
 * @date 2019年3月21日
 */
public interface SysDataruleService {


	/**
	 * 获取权限树
	 * @return
	 * @date 2019年3月21日
	 */
	List<DataRuleTree> dataruleTree();
	
	/**
	 * 获取菜单下的数据规则
	 * @param menuId
	 * @return
	 * @date 2019年3月21日
	 */
	List<SysDatarule> list(Integer menuId);
	
	/**
	 * 详情
	 * @param rule
	 * @return
	 * @date 2019年3月21日
	 */
	SysDatarule detial(Integer id);
	
	/**
	 * 删除
	 * @param rule
	 * @return
	 * @date 2019年3月21日
	 */
	void delete(String ids);

	/**
	 * 修改
	 * @param rule
	 * @return
	 * @date 2019年3月21日
	 */
	void update(SysDatarule rule);
	/**
	 * 添加
	 * @param rule
	 * @return
	 * @date 2019年3月21日
	 */
	Integer save(SysDatarule rule);
}
