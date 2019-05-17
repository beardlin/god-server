package net.lantrack.framework.jms.kafka.test;

import org.springframework.stereotype.Service;

import net.lantrack.framework.jms.kafka.consumer.ConsumerObserver;

@Service
public class DataHandler2 implements ConsumerObserver{

	@Override
	public void execute(String topic,String key, Object value) {
		System.out.println(this.getClass().getName()+"接收到 "+"topic="+topic+",value="+value);
		
	}

}
