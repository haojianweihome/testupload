package com.hjw.home.test;

import org.springframework.cglib.proxy.Enhancer;

public class Cglibmain {
	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();  
		  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(Cat.class);  
        enhancer.setCallback(cglibProxy);   
        Animal o = (Animal)enhancer.create();  
        o.run(); 
        o.eat(); 
	}
}
