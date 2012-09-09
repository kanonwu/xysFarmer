package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Vehicle;
import com.xys.cenxi.customer.util.OrderGenerator;

public class VehicleService {
	
	private static VehicleService service;
	
	private VehicleService(){
	}
	
	public static VehicleService getInstance(){
		if(service == null){
			service = new VehicleService();
		}
		
		return service;
	}
	
	public Vehicle add(Vehicle fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(Vehicle fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<Vehicle> fas){
		Dao dao = DataSourceManager.getDao();
		for(Vehicle f : fas){
			dao.delete(f);
		}
	}
	
	public void update(Vehicle fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询车辆情况
	 * @param cusomerID
	 * @return
	 */
	public List<Vehicle> getForestry(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(Vehicle.class, Cnd.where("ownerID", "=", cusomerID));
	}

}
