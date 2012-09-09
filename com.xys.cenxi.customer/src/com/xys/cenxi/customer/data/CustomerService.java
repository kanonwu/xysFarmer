package com.xys.cenxi.customer.data;

import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;

import com.xys.cenxi.customer.data.query.CustomerQueryKey;
import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.util.OrderGenerator;
import com.xys.cenxi.customer.util.Util;

public class CustomerService {

	private static CustomerService service;
	
	private CustomerService(){
	}
	
	public static CustomerService getService(){
		if(service == null){
			service = new CustomerService();
		}
		
		return service;
	}
	
	public Customer addCustomer(Customer customer){
		Dao dao = DataSourceManager.getDao();
		if(customer.getRowID() == null){
			customer.setRowID(OrderGenerator.newOrder());
		}
		customer.setModifyTime(new Date());
		
		return dao.insert(customer);
	}
	
	public void updateCustomer(Customer customer){
		customer.setModifyTime(new Date());
		Dao dao = DataSourceManager.getDao();
		dao.update(customer);
	}
	
	public void deleteCustomer(Customer customer){
		Dao dao = DataSourceManager.getDao();
		dao.delete(customer);
	}
	
	public void deleteCustomer(List<Customer> items){
		Dao dao = DataSourceManager.getDao();
		for(Customer cus : items){
			dao.delete(cus);
		}
	}
	
	public List<Customer> getCustomer(CustomerQueryKey key){
		List<Customer> result = null;
		Dao dao = DataSourceManager.getDao();
		Criteria cri = Cnd.cri();
		if(!Util.isEmpty(key.name)){
			cri.where().and("name", "=", key.name);
		}
		if(!Util.isEmpty(key.identify)){
			cri.where().and("identify", "=", key.identify);
		}
		if(!Util.isEmpty(key.gender)){
			cri.where().and("gender", "=", key.gender);
		}
		if(!Util.isEmpty(key.marry)){
			cri.where().and("marry", "=", key.marry);
		}
		if(!Util.isEmpty(key.education)){
			cri.where().and("education", "=", key.education);
		}
		if(!Util.isEmpty(key.regional)){
			cri.where().and("regional", "=", key.regional);
		}
		if(!Util.isEmpty(key.address)){
			cri.where().andLike("address", key.address);
		}

		if(key.pagerInfo != null){
			result = dao.query(Customer.class, cri, key.pagerInfo);
			key.pagerInfo.setRecordCount(dao.count(Customer.class, cri));
		}else{
			result = dao.query(Customer.class, cri);
		}
		
		return result;
	}
}
