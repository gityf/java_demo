package org.wyf.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.net.SyslogAppender;

import javax.jms.*;

/**
 * Created by wyf on 2017/7/19.
 */
public class ActiveMQComsumer {
    private final String queueName = "queue-1";
    private final String topicName = "topic-1";
    private final String MqUrl = "tcp://127.0.0.1:61616";

    public void comsumeQueue() {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                 public void onMessage(Message message) {
                     TextMessage textMessage = (TextMessage) message;
                     try {
                         System.out.println("comsume-queue-msg=" + textMessage.getText());
                     } catch (JMSException e) {
                         e.printStackTrace();
                     }
                 }
             });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void comsumeTopic() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topicName);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("comsume-topic-msg=" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ActiveMQComsumer comsumer = new ActiveMQComsumer();
        //comsumer.comsumeQueue();
        comsumer.comsumeTopic();
    }
}
