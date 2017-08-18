package com.hjw.home.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("resources/jmsconfigtopicconsumer2.xml");    
	    while(true) {    
	    } 
	}
}
