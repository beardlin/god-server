package net.lantrack.framework.monitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.monitor.MonitorUtil;
import net.lantrack.framework.monitor.system.model.SysMonitorInfo;
import net.lantrack.framework.springplugin.controller.BaseController;
/**
 * 系统监控
 *       
 * @date 2019年5月17日
 */
@RestController 
@RequestMapping("/monitor")
public class MonitorController extends BaseController{

	/**
	 * 系统资源信息及JVM信息
	 * @param info
	 * @return
	 * @date 2019年5月17日
	 */
	@RequestMapping("/sys")
	public ReturnEntity sysMonitor(ReturnEntity info) {
		try {
			SysMonitorInfo sysMonitor = MonitorUtil.getSysMonitor();
			info.setResult(sysMonitor);
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}
}
