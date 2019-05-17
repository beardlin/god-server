package net.lantrack.framework.jms.kafka.consumer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lantrack.framework.jms.kafka.PropertiesConfig;

/**
 * KafKaConsumer
 *       
 * @date 2019年5月13日
 */
public class Consumer implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	/**
	 * Topics And ConsumerObserver
	 */
	private  Map<String,List<ConsumerObserver>> topics;
	
	/**
	 * 开启线程数
	 */
	private int corePoolSize = 5;
	/**
	 * 队列容量大小
	 */
	private int queueCapacity = 1000;
	
	private  ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L,
			TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(queueCapacity),
			new ThreadPoolExecutor.CallerRunsPolicy());
	
	
	public Consumer(Map<String,List<ConsumerObserver>> topics) {
		this.topics = topics;
	}
	public Consumer(Map<String,List<ConsumerObserver>> topics,int corePoolSize) {
		this.topics = topics;
		this.corePoolSize = corePoolSize;
	}

	public Consumer(Map<String,List<ConsumerObserver>> topics,int corePoolSize, int queueCapacity) {
		super();
		this.corePoolSize = corePoolSize;
		this.queueCapacity = queueCapacity;
		this.topics = topics;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
		if(topics==null || topics.isEmpty()) {
			this.logger.error("no topics has been regist!");
			return;
		}
		//生成消费者
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(PropertiesConfig.getProperties());
		//订阅topics
		consumer.subscribe(topics.keySet());
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				threadPool.execute(new ConsumerHandler(record,topics));
			}
		}
	}
	

	public int getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public int getQueueCapacity() {
		return queueCapacity;
	}
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
}
