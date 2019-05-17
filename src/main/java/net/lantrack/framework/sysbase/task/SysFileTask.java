package net.lantrack.framework.sysbase.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.lantrack.framework.core.service.UpfileService;

@Component
public class SysFileTask {

	@Autowired
	UpfileService upfileService;
	
	//每天凌晨1点定时清理系统垃圾文件
	@Scheduled(cron="0 0 1 * * ?")
	public void clearTempFile() {
		upfileService.clearTempFile();
	}
}
