package net.lantrack.framework.sysbase.service;


import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysLog;
import net.lantrack.framework.sysbase.model.log.LogModel;

/**
 * 操作日志Service
 * 2018年1月25日
 * @author hww
 */
public interface SysLogService extends CrudService<SysLog> {

	/**
	 * 根据主键id获取日志model
	 * @param id
	 * @return
	 */
	public LogModel queryModelById(Object id);
	/**
	 * 清空日志，只有超级管理员才有此权限
	 * @throws ServiceException
	 * 2018年1月24日
	 * @author lin
	 */
	public void clearLog() throws ServiceException;
}