package net.lantrack.framework.jms.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import net.lantrack.framework.jms.kafka.PropertiesConfig;
/**
 * KafkaProducer
 *       
 * @date 2019年5月14日
 */
public class KfkProducer {

	private static volatile Producer<String, String> producer;
	
	private static Producer<String, String> getProducer(){
		if(producer==null) {
			synchronized (KfkProducer.class) {
				if(producer==null) {
					producer = new KafkaProducer<String, String>(PropertiesConfig.getProperties());
				}
			}
		}
		return producer;
	}
	/**
	 * 发送消息
	 * @param topic
	 * @param value
	 * @date 2019年5月14日
	 */
	public static void sendMsg(String topic,String value) {
		getProducer().send(new ProducerRecord<String, String>(topic, value));
	}
}
