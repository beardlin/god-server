package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysTask;
/**
 * 任务管理Service
 * 2018年1月23日
 * @author lmy
 */
public interface SysTaskService extends CrudService<SysTask>{
	/**
	 * 查找已开启的任务，并加入定时任务中
	 * @return
	 */
	public List<SysTask> startTask(String status);
	
	public SysTask selectByJobName(String jobName);
	
}
