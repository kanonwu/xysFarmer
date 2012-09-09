package com.xys.cenxi.customer.data;

public class RelationService {

	private static RelationService service;
	
	private final String[] edus;
	
	private RelationService(){
		edus = new String[]{"", "妻", "夫", "父", "母", "子", "女", "弟", "兄", "姐", "妹", "户主", "儿媳", "其他"};
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
