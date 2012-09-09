package com.xys.cenxi.customer.ui.component.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xys.cenxi.customer.data.IncomeService;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.FarmIncome;

/**
 * 种养收入情况表格内容提供器
 * @author wjl
 *
 */
public class IncomeTableProvider implements IStructuredContentProvider {
	

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(!(inputElement instanceof Customer)){
			return null;
		}
		
		Customer cus = (Customer) inputElement;
		List<FarmIncome> incomes = IncomeService.getInstance().getFarmIncome(cus.getRowID());
		return incomes.toArray();
	}

}
