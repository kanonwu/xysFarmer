package com.xys.cenxi.customer.ui.component.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xys.cenxi.customer.data.FamilyService;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Family;

/**
 * 家庭成员表格内容提供器
 * @author wjl
 *
 */
public class FamilyTableProvider implements IStructuredContentProvider {
	

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
		List<Family> familys = FamilyService.getInstance().getFamily(cus.getRowID());
		return familys.toArray();
	}

}
