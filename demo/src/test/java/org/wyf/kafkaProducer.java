package org.wyf;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class kafkaProducer {

    @Test
    public void KafkaProducerTest() {
        Producer producer = createProducer();
        int i=0;
        while(true){
            producer.send(new KeyedMessage<Integer, String>("test", "message: " + i++));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Producer createProducer() {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "127.0.0.1:2181");
        properties.put("serializer.class", StringEncoder.class.getName());
        properties.put("metadata.broker.list", "127.0.0.1:9092");// 声明kafka broker
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

}
