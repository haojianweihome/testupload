package com.hjw.home.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkInvocationHandler implements InvocationHandler{
	Object target;
	JdkInvocationHandler(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		System.out.println("++++++before " + method.getName() + "++++++");  
        Object result = method.invoke(target, args);  
        System.out.println("++++++after " + method.getName() + "++++++");  
        return result;  
	}

}
