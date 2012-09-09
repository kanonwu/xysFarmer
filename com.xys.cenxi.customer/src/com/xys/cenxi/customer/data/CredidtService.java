package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Credidt;
import com.xys.cenxi.customer.pojo.DebitCnd;
import com.xys.cenxi.customer.util.OrderGenerator;

public class CredidtService {
	
	private static CredidtService service;
	
	private CredidtService(){
		
	}
	
	public static CredidtService getInstance(){
		if(service == null){
			service = new CredidtService();
		}
		
		return service;
	}
	
	public Credidt add(Credidt fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(Credidt fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<Credidt> fas){
		Dao dao = DataSourceManager.getDao();
		for(Credidt f : fas){
			dao.delete(f);
		}
	}
	
	public void update(Credidt fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询其他重要信息
	 * @param cusomerID
	 * @return
	 */
	public Credidt getCredidt(String cusomerID){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(Credidt.class, Cnd.where("ownerID", "=", cusomerID));
	}
	
	public DebitCnd getDebitCnd(String customerID){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(DebitCnd.class, Cnd.where("ownerID", "=", customerID));
	}
	
	public DebitCnd add(DebitCnd debit){
		if(debit.getRowID() == null){
			debit.setRowID(OrderGenerator.newOrder());
		}
		Dao dao = DataSourceManager.getDao();
		return dao.insert(debit);
	}
	
	public void update(DebitCnd debit){
		Dao dao = DataSourceManager.getDao();
		dao.update(debit);
	}

	public void delete(DebitCnd debit){
		Dao dao = DataSourceManager.getDao();
		dao.delete(debit);
	}
}
