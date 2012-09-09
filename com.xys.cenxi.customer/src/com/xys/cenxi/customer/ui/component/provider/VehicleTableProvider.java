package com.xys.cenxi.customer.ui.component.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xys.cenxi.customer.data.VehicleService;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Vehicle;

/**
 * ���������������ṩ��
 * @author wjl
 *
 */
public class VehicleTableProvider implements IStructuredContentProvider {
	

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
		List<Vehicle> vehicles = VehicleService.getInstance().getForestry(cus.getRowID());
		return vehicles.toArray();
	}

}
