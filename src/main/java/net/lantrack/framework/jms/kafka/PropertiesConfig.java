package net.lantrack.framework.jms.kafka;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * KafKa Client Properties Config
 * @date 2019年5月13日
 */
public class PropertiesConfig {
	private static Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);
	private static volatile Properties properties;
	public static Properties getProperties() {
		if(properties==null) {
			synchronized(PropertiesConfig.class) {
				if(properties==null) {
					try {
						properties = PropertiesLoaderUtils.loadAllProperties("properties/kafka.properties");
					} catch (IOException e) {
						logger.error("KafKa Properties load error:{}",e.getMessage());
					}
				}
			}
		}
		return properties;
	}
}
