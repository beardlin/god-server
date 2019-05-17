package net.lantrack.framework.sysbase.service.imp;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.lantrack.BaseJunitTest;
import net.lantrack.framework.sysbase.entity.SysTask;
import net.lantrack.framework.sysbase.service.SysTaskService;

public class SysTaskServiceImpTest extends BaseJunitTest{
	
	@Autowired
	SysTaskService sysTaskService;	
	
	@Test
    public void getTaskById(){
		System.out.println("test......");
	}
	
	
	@Test
    public void addTask(){
		SysTask entity=new SysTask();
		entity.setTaskName("定时任务3");
		entity.setTaskStatus("3");
		entity.setJobName("job3");
		entity.setTriggerName("trigger3");
		entity.setClassName("net.lantrack.framework.sysbase.QuartzJobExample");
		entity.setFieldJson(null);
		entity.setCronExps("0/1 * * * * ?");
		this.sysTaskService.save(entity);		
	}
	
	@Test
    public void startTask(){
		List<SysTask> list=this.sysTaskService.startTask("1");
		System.out.println(list.size());
	}

}
