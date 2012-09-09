package com.xys.cenxi.customer.db;

import java.io.File;

import javax.sql.DataSource;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import com.xys.cenxi.customer.util.Util;


public class DataSourceManager {
	
	private static DataSource ds;
	
	private static String driver = "org.hsqldb.jdbcDriver";
	
	private static Dao dao;
	
	public static DataSource getDataSource(){
		if(ds != null){
			return ds;
		}
        //创建存放数据库的文件夹
        Util.createDatabaseDir();
        
		ProxoolDataSource pds = new ProxoolDataSource();
		pds.setDriver(driver);
	       //得到数据库文件夹路径
        String databasePath = Util.getDatabaseDir();
        String serverURL = "jdbc:hsqldb:" + databasePath + File.separator + Util.DB_NAME;
        pds.setDriver(driver);
        pds.setDriverUrl(serverURL);
        pds.setMaximumConnectionCount(10);
        pds.setMinimumConnectionCount(1);
        pds.setMaximumConnectionLifetime(43200000);
        pds.setUser("sa");
        pds.setPassword("");
        ds = pds;
        dao = new NutDao(ds);
		return ds;
	}

	public static Dao getDao(){
		if(ds == null){
			getDataSource();
		}
		return dao;
	}
}
