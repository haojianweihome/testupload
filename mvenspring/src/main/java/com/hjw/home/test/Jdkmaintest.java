package com.hjw.home.test;

import java.lang.reflect.Proxy;

public class Jdkmaintest {
	public static void main(String[] args) {
		Animal animal = new Cat();  
		JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler(animal);  
		Animal animalProxy = (Animal)Proxy.newProxyInstance(animal.getClass().getClassLoader(),  
				animal.getClass().getInterfaces(), jdkInvocationHandler);  
        animalProxy.run();  
        animalProxy.eat(); 
	}
}
