package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.FarmMachine;
import com.xys.cenxi.customer.util.OrderGenerator;

public class FarmMachineService {
	
	private static FarmMachineService service;
	
	private FarmMachineService(){
	}
	
	public static FarmMachineService getInstance(){
		if(service == null){
			service = new FarmMachineService();
		}
		
		return service;
	}
	
	public FarmMachine add(FarmMachine fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(FarmMachine fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<FarmMachine> fas){
		Dao dao = DataSourceManager.getDao();
		for(FarmMachine f : fas){
			dao.delete(f);
		}
	}
	
	public void update(FarmMachine fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询农业机械
	 * @param cusomerID
	 * @return
	 */
	public List<FarmMachine> getForestry(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(FarmMachine.class, Cnd.where("ownerID", "=", cusomerID));
	}

}
