package com.xys.cenxi.customer.data;

public class GenderService {
	
	private static GenderService service;
	
	private String[] genders;
	
	private GenderService(){
		genders = new String[]{"ÄÐ", "Å®"};
	}

	public static GenderService getInstant(){
		if(service == null){
			service = new GenderService();
		}
		
		return service;
	}
	
	public String[] getGenders(){
		return genders;
	}
}
