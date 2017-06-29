package com.activeMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.Serializable;

/**
 * Created by CDC3715 on 2017/6/15.
 */
@Component
@EnableScheduling
public class ProducerMQ {

    private static final Logger logger = LoggerFactory.getLogger(ProducerMQ.class);

    @Autowired
    private ConnectionFactory connectionFactory;
    // Connection ：JMS客户端到JMS Provider的连接
    private Connection connection = null;
    // Session：一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination = null;
    // MessageProducer：消息发送者
    private MessageProducer producer;

    public boolean createFactory(String topicName) {
        try {
            connection = connectionFactory.createConnection();//构造从工厂得到连接对象
            connection.start();//启动
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//获取操作连接
            destination = session.createQueue(topicName);// 获取session，FirstQueue是一个服务器的queue
            producer = session.createProducer(destination);//得到消息生成者【发送者】
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);// 设置不持久化
            // session.commit();
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.print("构造从工厂失败:" + e.getMessage());
            return false;
        }
        return true;
    }

    //@Scheduled(fixedDelay = 600000, initialDelay = 1000)
    public void setMessageBean() {
        for(int i=0;i<10;i++){
            String topicName = "topic"+i;
            String message = "topic"+i+"主题";
            if (createFactory(topicName)) {
                try {
                    ObjectMessage msg = session.createObjectMessage((Serializable) message);
                    producer.send(msg);
                    System.out.println("发送了一个");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
