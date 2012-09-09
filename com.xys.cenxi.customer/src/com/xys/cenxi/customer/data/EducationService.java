package com.xys.cenxi.customer.data;

public class EducationService {

	private static EducationService service;
	
	private final String[] edus;
	
	private EducationService(){
		edus = new String[]{"", "初中以下",  "初中以上", "大专以上"};
	}
	
	public static EducationService getInstant(){
		if(service == null){
			service = new EducationService();
		}
		
		return service;
	}
	
	public String[] getEducations(){
		return edus;
	}
}
