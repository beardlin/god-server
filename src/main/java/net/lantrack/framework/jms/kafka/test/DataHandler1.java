package net.lantrack.framework.jms.kafka.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.jms.kafka.consumer.ConsumerObserver;
import net.lantrack.framework.jms.kafka.topic.dao.KafkaTopicMapper;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopic;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopicExample;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopicExample.Criteria;

@Service
public class DataHandler1 implements ConsumerObserver,DataHandlerService{

	@Autowired
	KafkaTopicMapper kafkaTopicMapper;
	
	@Override
	public void execute(String topic, String key,Object value) {
		System.out.println(this.getClass().getName()+" 接收到 "+"topic="+topic+ ",value="+value);
		handlerData(topic);
	}

	@Override
	public void handlerData(String topic) {
		KafkaTopicExample example = new KafkaTopicExample(); 
		Criteria criteria = example.createCriteria();
		criteria.andTopicEqualTo(topic);
		List<KafkaTopic> list = kafkaTopicMapper.selectByExample(example);
		for(KafkaTopic kt:list) {
			System.err.println("DataHandler1:"+kt.getObserver());
		}
		
	}

}
