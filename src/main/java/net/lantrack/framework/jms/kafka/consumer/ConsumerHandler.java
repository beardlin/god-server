package net.lantrack.framework.jms.kafka.consumer;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;


/**
 * 消息处理
 *       
 * @date 2019年5月13日
 */
public class ConsumerHandler implements Runnable{
	
	/**
	 * record
	 */
	private ConsumerRecord<String, String> record;
	
	
	/**
	 * Topics And ConsumerObserver
	 */
	private  Map<String,List<ConsumerObserver>> topicObservers;
	
	public ConsumerHandler(ConsumerRecord<String, String> record,
			Map<String,List<ConsumerObserver>> topicsObserver) {
		super();
		this.record = record;
		this.topicObservers = topicsObserver;
	}

	@Override
	public void run() {
		String topic = record.topic();
		if(topicObservers==null || topicObservers.isEmpty()) {
			return;
		}
		//当前Topic对应的处理类
		List<ConsumerObserver> observers = topicObservers.get(topic);
		for(ConsumerObserver os:observers) {
			os.execute(topic, record.key(),record.value());
		}
	}
}
