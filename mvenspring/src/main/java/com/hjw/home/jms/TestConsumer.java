package com.hjw.home.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jmsconfigtopicconsumer.xml");    
	    while(true) {    
	    } 
	}
	 
}
