package net.lantrack.framework.sysbase.service;

import java.util.List;
import java.util.Map;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.Duty;



public interface DutyService extends CrudService<Duty> {
	
	/**
	 * 保存带权限的职务信息
	 * @param entity
	 * @param menus
	 * @return
	 * @throws ServiceException
	 */
	public Duty save(Duty entity, String menus) throws ServiceException;
	
	/**
	 * 修改带权限的职务信息
	 * @param entity
	 * @param menus
	 * @return
	 * @throws ServiceException
	 */
	public Duty update(Duty entity, String menus) throws ServiceException;
    
    /**
     * 查看当前职务下的权限
     * @param id
     * @return
     * 2018年1月17日
     * @author lin
     */
	Map<String, Object> getMenuTreeByDutyId(Integer id);

    /**
     * 给当前部门职务添加权限
     * @param id 当前职务id
     * @param menus  所勾选的菜单id
     * 2018年1月17日
     * @author lin
     */
    void addPermission(Integer id,String menus);
	/**
	 * 根据部门id查询其下所有职务
	 * @param id
	 * @return
	 * 2018年1月16日
	 * @author lin
	 */
	public List<Duty> getDutyByOfficeId(String id);
	/**
	 * 根据部门id查询其下所有职务（Map形式）
	 * @param id
	 * @return
	 * 2018年1月18日
	 * @author hww
	 */
	public Map<Integer, String> getMapByOfficeId(String id);
	
}