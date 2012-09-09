package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.ForestRights;
import com.xys.cenxi.customer.util.OrderGenerator;

public class ForestryService {
	
	private static ForestryService service;
	
	private ForestryService(){
		
	}
	
	public static ForestryService getInstance(){
		if(service == null){
			service = new ForestryService();
		}
		
		return service;
	}
	
	public ForestRights add(ForestRights fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(ForestRights fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<ForestRights> fas){
		Dao dao = DataSourceManager.getDao();
		for(ForestRights f : fas){
			dao.delete(f);
		}
	}
	
	public void update(ForestRights fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询林权情况
	 * @param cusomerID
	 * @return
	 */
	public List<ForestRights> getForestry(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(ForestRights.class, Cnd.where("ownerID", "=", cusomerID));
	}

}
