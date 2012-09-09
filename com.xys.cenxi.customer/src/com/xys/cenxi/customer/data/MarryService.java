package com.xys.cenxi.customer.data;

public class MarryService {
	
	private static MarryService service;
	
	private final String[] marrys;
	
	private MarryService(){
		marrys = new String[]{"", "�ѻ�", "δ��", "����", "ɥż", "δ֪"};
	}
	
	public static MarryService getInstant(){
		if(service == null){
			service  = new MarryService();
		}
		
		return service;
	}
	
	public String[] getMarrys(){
		return marrys;
	}

}
