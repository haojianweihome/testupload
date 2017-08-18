package com.hjw.home.test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{

	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
        System.out.println(method.getName());  
        Object o1 = methodProxy.invokeSuper(object, args);  
        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
        return o1; 
	}

	

}
