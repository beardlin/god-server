package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysOfficeTree;
import net.lantrack.framework.sysbase.model.office.OfficeModel;



/**
 * 组织机构树形表      
 * @date 2019年3月12日
 */
 
public interface SysOfficeTreeService extends CrudService<SysOfficeTree> {
	
	
	
	/**
	 * 从单点登录系统抽取全部的组织机构信息
	 * @throws Exception
	 */
	public String extractOfficeFromSso(String sn) throws Exception;

	/**
	 * 根据父id获取其子节点
	 * @param pid
	 * @return
	 * @throws ServiceException
	 */
	public List<OfficeModel> getTreeByPid(String pid) throws ServiceException; 
	
	/**
	 * 根据传入的officeId获取其子孙后代所有的officeIds
	 * @param officeId 当前传入的机构id
	 * @return officeIds 以逗号拼接的所有下属机构ids
	 */
	public String getAllSubOfficeids(String officeId, boolean hasSingleQuote);
	/**
	 * 获取当前登录用户所能见到的直系组织机构
	 */
	public List<OfficeModel> getCurrentOfficeTree(); 

}