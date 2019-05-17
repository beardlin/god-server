package net.lantrack.framework.jms.kafka.test;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import net.lantrack.framework.jms.kafka.PropertiesConfig;

public class KafKaConsumerTest {

	public static void main(String[] args) {
		//生成消费者
		@SuppressWarnings("resource")
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(PropertiesConfig.getProperties());
		//订阅topics
		consumer.subscribe(Arrays.asList("topic1","topic2","topic3"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("topic="+record.topic()+",value="+record.value());
			}
		}
	}
}
