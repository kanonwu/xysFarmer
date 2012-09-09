package com.xys.cenxi.customer.data;

public class HealthyService {

	private static HealthyService service;
	
	private final String[] edus;
	
	private HealthyService(){
		edus = new String[]{"", "����",  "һ��", "��"};
	}
	
	public static HealthyService getInstant(){
		if(service == null){
			service = new HealthyService();
		}
		
		return service;
	}
	
	public String[] getHealthy(){
		return edus;
	}
}
