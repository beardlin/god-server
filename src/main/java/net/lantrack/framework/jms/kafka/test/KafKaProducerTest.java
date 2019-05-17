package net.lantrack.framework.jms.kafka.test;

import net.lantrack.framework.jms.kafka.producer.KfkProducer;

public class KafKaProducerTest {

	/**
	 * 生产者
	 * @param args
	 * @throws InterruptedException 
	 * @date 2019年5月14日
	 */
    public static void main(String[] args) throws InterruptedException{
    	for(int i=1;i<50;i++) {
    		int a = i%3;
    		if(a==0) {
    			KfkProducer.sendMsg("topic3", "value"+i);
    		}else if (a==1) {
    			KfkProducer.sendMsg("topic1", "value"+i);
			}else if (a==2) {
				KfkProducer.sendMsg("topic2", "value"+i);
			}
    		System.out.println("send"+i);
    		Thread.sleep(1000);
    	}
    	
    }
}
