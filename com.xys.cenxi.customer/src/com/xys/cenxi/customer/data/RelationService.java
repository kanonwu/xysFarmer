package com.xys.cenxi.customer.data;

public class RelationService {

	private static RelationService service;
	
	private final String[] edus;
	
	private RelationService(){
		edus = new String[]{"", "��", "��", "��", "ĸ", "��", "Ů", "��", "��", "��", "��", "����", "��ϱ", "����"};
	}
	
	public static RelationService getInstant(){
		if(service == null){
			service = new RelationService();
		}
		
		return service;
	}
	
	public String[] getRelation(){
		return edus;
	}
}
