package net.lantrack.framework.jms.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;

import net.lantrack.framework.core.log.LogFactoty;
import net.lantrack.framework.jms.kafka.consumer.Consumer;
import net.lantrack.framework.jms.kafka.consumer.ConsumerObserver;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopic;
import net.lantrack.framework.jms.kafka.topic.service.KafkaTopicService;

/**
 * 监听项目启动时，启动KafKaConsumer      
 * @date 2019年5月14日
 */
//@WebListener
public class KafKaListener implements ServletContextListener{
	 /**
     * 日志对象
     */
    protected  Logger logger = LogFactoty.getRunLogger();
    
    
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//1 获取ApplicationContext俩种方式
//		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		//2 获取ApplicationContext俩种方式
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		
		KafkaTopicService topicService = ctx.getBean(KafkaTopicService.class);
		//注册Topic和Observer
		List<KafkaTopic> allTopic = topicService.getAll();
		if(allTopic==null || allTopic.isEmpty()) {
			this.logger.error(" no topic has been init !");
			return;
		}
		Map<String,List<ConsumerObserver>> topics = new HashMap<>(allTopic.size());
		for(KafkaTopic topic:allTopic) {
			String[] observerClazz = topic.getObserver().split(",");
			List<ConsumerObserver> observerList = getObserverList(observerClazz,ctx);
			if(observerList!=null && !observerList.isEmpty()) {
				topics.put(topic.getTopic(), observerList);
			}
		}
		//启动KafKa
		Consumer consumer = new Consumer(topics,10, 1000);
		new Thread(consumer).start();
	}
	/**
	 * 获取对应的Observers
	 * @param classNames
	 * @return
	 * @date 2019年5月14日
	 */
	private List<ConsumerObserver> getObserverList(String[] classNames,WebApplicationContext ctx) {
		List<ConsumerObserver> list = new ArrayList<>(classNames.length);
		//根据全类名或者短名获取对应的对象
		for(String className:classNames) {
			Object bean = null;
			//先从Spring容器中获取
			try {
				bean = ctx.getBean(className);
				System.out.println("从Spring获取："+className);
			} catch (NoSuchBeanDefinitionException e) {
				try {
					//Spring中不存在则通过反射获取
					Class<?> clazz = Class.forName(className);
					System.out.println("通过反射获取："+className);
					bean = clazz.newInstance();
				} catch (ClassNotFoundException e1) {
					this.logger.error("ClassNotFound:{}--{}",className,e1.getMessage());
				} catch (InstantiationException e1) {
					this.logger.error("InstantiationException={}",e1.getMessage());
				} catch (IllegalAccessException e1) {
					this.logger.error("IllegalAccessException={}",e1.getMessage());
				}
			}
			if(bean!=null && bean instanceof ConsumerObserver) {
				list.add((ConsumerObserver)bean);
			}
			
		}
		return list;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("关闭KafKa");
	}

}
