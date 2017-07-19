package org.wyf.activemq;

import org.apache.activemq.*;

import javax.jms.*;

/**
 * Created by wyf on 2017/7/19.
 */
public class ActiveMQProducer {
    private final String queueName = "queue-1";
    private final String topicName = "topic-1";
    private final String MqUrl = "tcp://127.0.0.1:61616";

    public void produceQueue() {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageProducer messageProducer = session.createProducer(destination);
            int i = 10;
            while (i-- > 0) {
                TextMessage message = session.createTextMessage("queue-msg-" + i);
                messageProducer.send(message);
                System.out.println("producer-queue-msg=" + message.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void produceTopic() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topicName);
            MessageProducer messageProducer = session.createProducer(destination);
            int i = 10;
            while (i-- > 0) {
                TextMessage message = session.createTextMessage("topic-msg-" + i);
                messageProducer.send(message);
                System.out.println("producer-topic-msg=" + message.getText());
            }
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ActiveMQProducer producer = new ActiveMQProducer();
        producer.produceQueue();
        producer.produceTopic();
    }
}
