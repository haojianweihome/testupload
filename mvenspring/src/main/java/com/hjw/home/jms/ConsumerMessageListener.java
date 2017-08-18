package com.hjw.home.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerMessageListener implements MessageListener{
	@Autowired
	private UserMessageConverter userMessageConverter;
	@Override
	public void onMessage(Message message) {
		//这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换  
        TextMessage textMsg = (TextMessage) message;  
        System.out.println("接收到一个纯文本消息。");  
        try {  
            System.out.println("消息内容是：" + textMsg.getText());  
        } catch (JMSException e) {  
            e.printStackTrace();  
        } 
		 /*if (message instanceof ObjectMessage) {  
	            ObjectMessage objMessage = (ObjectMessage) message;  
	            try {  
	                Object obj = objMessage.getObject(); 
	                Email email = (Email) obj;  
	                User user = (User) userMessageConverter.fromMessage(objMessage);  
	                System.out.println("接收到一个ObjectMessage，包含user对象。");  
	                System.out.println(user);  
	            } catch (JMSException e) {  
	                e.printStackTrace();  
	            }  
	              
	        } */ 
		
	}

}
