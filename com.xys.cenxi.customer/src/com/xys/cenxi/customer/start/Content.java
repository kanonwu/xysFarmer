package com.xys.cenxi.customer.start;

import com.xys.cenxi.customer.pojo.User;

public class Content {

	private static AppMain appMain;
	
	private static User user;
	
	public static User getUser(){
		return user;
	}
	
	public static void setUser(User argUser){
		user = argUser;
	}
	
	protected static void setAppMain(AppMain app){
		appMain = app;
	}
	
	public static AppMain getAppMain(){
		return appMain;
	}
}
