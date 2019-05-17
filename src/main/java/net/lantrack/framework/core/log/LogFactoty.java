package net.lantrack.framework.core.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * 获取运行日志对象
 * @date 2019年5月14日
 */
public class LogFactoty {
	 
	public static Logger getRunLogger() {
		return (Logger) LogManager.getLogger("syslog");
	}
}
