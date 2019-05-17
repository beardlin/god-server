package net.lantrack.framework.jms.kafka.topic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.jms.kafka.topic.dao.KafkaTopicMapper;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopic;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopicExample;
import net.lantrack.framework.jms.kafka.topic.service.KafkaTopicService;

@Service
public class KafkaTopicServiceImpl implements KafkaTopicService {

	@Autowired
	KafkaTopicMapper kafkaTopicMapper;
	
	@Override
	public List<KafkaTopic> getAll() {
		return kafkaTopicMapper.selectByExample(new KafkaTopicExample());
	}

}
