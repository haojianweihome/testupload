package com.hjw.home.test;

public class Cat implements Animal{

	@Override
	public void run() {
		System.out.println("小猫跑");
		
	}

	@Override
	public void eat() {
		System.out.println("小猫吃");
		
	}

}
