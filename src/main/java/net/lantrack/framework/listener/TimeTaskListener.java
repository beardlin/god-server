package net.lantrack.framework.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.lantrack.framework.sysbase.service.SysTaskService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *   监听到服务启动时与硬件的网络是否畅通
 */
public class TimeTaskListener implements ServletContextListener{


	@Override
	public void contextInitialized(ServletContextEvent sc) {
		//获得Spring容器
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
        //从Spring容器中获得SysTaskService的实例
        SysTaskService sysTaskService = ctx.getBean(SysTaskService.class);
		sysTaskService.startTask("1");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
