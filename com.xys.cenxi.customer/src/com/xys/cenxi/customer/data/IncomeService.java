package com.xys.cenxi.customer.data;

import java.math.BigDecimal;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.pojo.FamilyOutput;
import com.xys.cenxi.customer.pojo.FarmIncome;
import com.xys.cenxi.customer.pojo.OtherIncome;
import com.xys.cenxi.customer.util.OrderGenerator;

public class IncomeService {
	
	private static IncomeService service;
	
	private IncomeService(){
	}
	
	public static IncomeService getInstance(){
		if(service == null){
			service = new IncomeService();
		}
		
		return service;
	}
	
	public FarmIncome add(FarmIncome fa){
		Dao dao = DataSourceManager.getDao();
		if(fa.getRowID() == null){
			fa.setRowID(OrderGenerator.newOrder());
		}
		
		return dao.insert(fa);
	}
	
	public void delete(FarmIncome fa){
		Dao dao = DataSourceManager.getDao();
		dao.delete(fa);
	}
	
	public void delete(List<FarmIncome> fas){
		Dao dao = DataSourceManager.getDao();
		for(FarmIncome f : fas){
			dao.delete(f);
		}
	}
	
	public void update(FarmIncome fa){
		Dao dao = DataSourceManager.getDao();
		dao.update(fa);
	}
	
	/**
	 * 根据农户rowID查询种养收入情况
	 * @param cusomerID
	 * @return
	 */
	public List<FarmIncome> getFarmIncome(String customerID){
		Dao dao = DataSourceManager.getDao();
		return dao.query(FarmIncome.class, Cnd.where("ownerID", "=", customerID));
	}
	
	public OtherIncome getOtherIncome(String customerID){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(OtherIncome.class, Cnd.where("ownerID", "=", customerID));
	}
	
	public BigDecimal getFamilyTotalIncome(String customerID){
		//得到全部收入：种养收入+其他收入
		
		//种养收入
		List<FarmIncome> farmIncomes = getFarmIncome(customerID);
		BigDecimal result = BigDecimal.ZERO;
		for(FarmIncome income : farmIncomes){
			if(income.getIncome() != null){
				result = result.add(BigDecimal.valueOf(income.getIncome())); 
			}
		}
		
		//其他收入
		OtherIncome oi = getOtherIncome(customerID);
		if(oi != null){
			if(oi.getWorkIncome() != null){
				result = result.add(BigDecimal.valueOf(oi.getWorkIncome()));
			}
			if(oi.getOtherIncome() != null){
				result = result.add(BigDecimal.valueOf(oi.getOtherIncome()));
			}
		}
		return result;
	}
	
	public FamilyOutput getFamilyOutput(String customerID){
		Dao dao = DataSourceManager.getDao();
		return dao.fetch(FamilyOutput.class, Cnd.where("ownerID", "=", customerID));
	}

	public BigDecimal getFamilyNetIncome(String customerID){
		BigDecimal income = getFamilyTotalIncome(customerID);
		FamilyOutput output = getFamilyOutput(customerID);
		
		BigDecimal netIncome = BigDecimal.ZERO;;
		if(income != null){
			netIncome = income;
		}
		BigDecimal totalOut = BigDecimal.ZERO;
		if(output != null){
			if(output.getProductionOutput() != null){
				totalOut = totalOut.add(BigDecimal.valueOf(output.getProductionOutput()));
			}
			if(output.getLiftOutput() != null){
				totalOut = totalOut.add(BigDecimal.valueOf(output.getLiftOutput()));
			}
			if(output.getOtherOutput() != null){
				totalOut = totalOut.add(BigDecimal.valueOf((output.getOtherOutput())));
			}
		}
		
		netIncome = netIncome.subtract(totalOut);
		return netIncome;
	}
	
	public void update(OtherIncome income){
		Dao dao = DataSourceManager.getDao();
		dao.update(income);
	}
	
	public OtherIncome add(OtherIncome income){
		Dao dao = DataSourceManager.getDao();
		if(income.getRowID() == null){
			income.setRowID(OrderGenerator.newOrder());
		}
		return dao.insert(income);
	}
	
	public FamilyOutput add(FamilyOutput output){
		Dao dao = DataSourceManager.getDao();
		if(output.getRowID() == null){
			output.setRowID(OrderGenerator.newOrder());
		}
		return dao.insert(output);
	}
	
	public void update(FamilyOutput output){
		Dao dao = DataSourceManager.getDao();
		dao.update(output);
	}
}
