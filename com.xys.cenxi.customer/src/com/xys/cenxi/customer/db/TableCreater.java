package com.xys.cenxi.customer.db;

import java.io.File;

import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.data.RegionalService;
import com.xys.cenxi.customer.data.UserService;
import com.xys.cenxi.customer.pojo.Credidt;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.DebitCnd;
import com.xys.cenxi.customer.pojo.Family;
import com.xys.cenxi.customer.pojo.FamilyOutput;
import com.xys.cenxi.customer.pojo.FarmIncome;
import com.xys.cenxi.customer.pojo.FarmMachine;
import com.xys.cenxi.customer.pojo.ForestRights;
import com.xys.cenxi.customer.pojo.Housing;
import com.xys.cenxi.customer.pojo.OtherIncome;
import com.xys.cenxi.customer.pojo.Regional;
import com.xys.cenxi.customer.pojo.User;
import com.xys.cenxi.customer.pojo.Vehicle;


/**
 * ����ϵͳʹ�õı�ϵͳ��ʼ��ʱִ��
 * 2010.9.6 drop Housing ForestryRights vehicle machine
 * @author wjl
 *
 */
public class TableCreater {
	private static Logger logger = LoggerFactory.getLogger(TableCreater.class);
	/**
	 * �������ݿ��
	 */
	public void createTable(){
		boolean dropTable = false;
		logger.info("�������ݿ��" + dropTable);
		//ʹ��nutz������
		Dao dao = DataSourceManager.getDao();
		dao.create(Customer.class, dropTable);
		dao.create(Family.class, false);
		dao.create(FamilyOutput.class, dropTable);
		dao.create(FarmIncome.class, dropTable);
		dao.create(FarmMachine.class, false);
		dao.create(ForestRights.class, dropTable);
		dao.create(Housing.class, dropTable);
		dao.create(OtherIncome.class, dropTable);
		dao.create(Vehicle.class, dropTable);
		dao.create(Credidt.class, dropTable);
		dao.create(DebitCnd.class, dropTable);
		if(!dao.exists(Regional.class)){
			dao.create(Regional.class, true);
			try {
				RegionalService.getInstance().loadRegionalFromFile(new File("regional.xls"));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("���������������ݳ���", e);
			}
		}
		
		if(!dao.exists(User.class)){
			dao.create(User.class, true);
			//����Ĭ���û��� admin 1234
			User admin = new User();
			admin.setName("admin");
			admin.setPassWord("1234");
			admin.setDesc("Ĭ���û���");
			
			UserService.getInstance().add(admin);
		}
	}
	
}
