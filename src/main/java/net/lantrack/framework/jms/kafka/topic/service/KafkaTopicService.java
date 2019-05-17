package net.lantrack.framework.jms.kafka.topic.service;

import java.util.List;

import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopic;

public interface KafkaTopicService {

	/**
	 * 获取所有Topic
	 * @return
	 * @date 2019年5月14日
	 */
	List<KafkaTopic> getAll();
}
