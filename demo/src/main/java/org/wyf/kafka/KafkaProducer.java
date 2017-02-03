package org.wyf.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by wyf on 17/2/3.
 */
public class KafkaProducer {
    public static void main(String[] args){
        KafkaProducer kafkaProducer = new KafkaProducer();
        kafkaProducer.sendTaskMsg("test", "Just for test");
    }
    public boolean sendTaskMsg(String topic, String taskMsg) {
        boolean ret = false;
        for (int ii = 0; ii < 3; ++ii) {
            try {
                Properties properties = new Properties();
                properties.put("zk.connect", "127.0.0.1:2181");
                properties.put("metadata.broker.list", "127.0.0.1:9092");
                properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
                properties.put("serializer.class", "kafka.serializer.StringEncoder");
                properties.put("partitioner.class", "kafka.producer.DefaultPartitioner");
                properties.put("request.required.acks", "1");
                ProducerConfig config = new ProducerConfig(properties);
                Producer<String, String> producer = new Producer<String, String>(config);
                // Creates a KeyedMessage instance
                KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, taskMsg);

                // Publish the message
                producer.send(data);

            } catch (Exception e) {
                ret = false;
                continue;
            }
            ret = true;
            break;
        }
        return ret;
    }
}
