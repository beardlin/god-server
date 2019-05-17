package net.lantrack.framework.jms.kafka.consumer;

public interface ConsumerObserver {

	void execute(String topic,String key,Object value);
}
