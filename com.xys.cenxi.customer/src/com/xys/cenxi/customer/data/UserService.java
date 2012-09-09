package com.xys.cenxi.customer.data;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.User;
import com.xys.cenxi.customer.util.OrderGenerator;

public class UserService {
	
	private static UserService service;
	
	private UserService(){
		
	}
	
	public static UserService getInstance(){
		if(service == null){
			service = new UserService();
		}
		
		return service;
	}
	
	public User add(User fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(User fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<User> fas){
		Dao dao = DataSourceManager.getDao();
		for(User f : fas){
			dao.delete(f);
		}
	}
	
	public void update(User fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	public User getUser(String name, String pwd){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(User.class, Cnd.where("name", "=", name).and("passWord", "=", pwd));
//		User user = null;
//		if(name.toLowerCase().equals("admin")){
//			if(pwd.equals("123456")){
//				user = new User();
//				user.setName(name);
//				user.setPassWord(pwd);
//			}
//		}
		
	}

}
