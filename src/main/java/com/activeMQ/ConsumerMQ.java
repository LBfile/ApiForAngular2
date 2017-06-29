package com.activeMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by CDC3715 on 2017/6/15.
 */
@Component
public class ConsumerMQ {

    private static final Logger logger= LoggerFactory.getLogger(ConsumerMQ.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    // Connection ：JMS客户端到JMS Provider的连接
    private Connection connection = null;
    // Session：一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination = null;
    // MessageConsumer：消息接收者
    private MessageConsumer consumer = null;

    public MessageConsumer getConsumer(String topicName) {
        try {
            connection = connectionFactory.createConnection();//构造从工厂得到连接对象
            connection.start();//启动
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//获取操作连接
            destination = session.createQueue(topicName);
            consumer = session.createConsumer(destination);
            return consumer;
        } catch (Exception e) {
            return null;
        }
    }

    //@Scheduled(fixedDelay = 60000, initialDelay = 10000)
    public void getMessageBean() throws JMSException {
            String topic="topic1";
        consumer=getConsumer(topic);
        if (consumer!=null) {
            Object object = (Object) consumer.receive(1000);
           if(object!=null){
               System.out.print("消费了一个");
               System.out.print(object.toString());
           }
        }
    }
}
