package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Housing;
import com.xys.cenxi.customer.util.OrderGenerator;

public class HousingService {
	
	private static HousingService service;
	
	private HousingService(){
		
	}
	
	public static HousingService getInstance(){
		if(service == null){
			service = new HousingService();
		}
		
		return service;
	}
	
	public Housing add(Housing fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(Housing fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<Housing> fas){
		Dao dao = DataSourceManager.getDao();
		for(Housing f : fas){
			dao.delete(f);
		}
	}
	
	public void update(Housing fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询房产情况
	 * @param cusomerID
	 * @return
	 */
	public List<Housing> getHouse(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(Housing.class, Cnd.where("ownerID", "=", cusomerID));
	}

}
