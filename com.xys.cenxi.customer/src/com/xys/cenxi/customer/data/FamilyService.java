package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Family;
import com.xys.cenxi.customer.util.OrderGenerator;

public class FamilyService {
	
	private static FamilyService service;
	
	private FamilyService(){
		
	}
	
	public static FamilyService getInstance(){
		if(service == null){
			service = new FamilyService();
		}
		
		return service;
	}
	
	public Family add(Family fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(Family fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<Family> fas){
		Dao dao = DataSourceManager.getDao();
		for(Family f : fas){
			dao.delete(f);
		}
	}
	
	public void update(Family fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询家庭成员
	 * @param cusomerID
	 * @return
	 */
	public List<Family> getFamily(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(Family.class, Cnd.where("ownerID", "=", cusomerID));
	}

}
