package com.xys.cenxi.customer.ui.component.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xys.cenxi.customer.data.CustomerService;
import com.xys.cenxi.customer.data.query.CustomerQueryKey;
import com.xys.cenxi.customer.pojo.Customer;

/**
 * �ͻ���Ϣչʾ��������ṩ����CustomerQueryKey ��Ϊ��������
 * @author wjl
 *
 */
public class CustomerTableProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement == null){
			return null;
		}
		
		if(inputElement instanceof CustomerQueryKey){
			CustomerQueryKey key = (CustomerQueryKey) inputElement;
			//��ѯ����
			CustomerService service = CustomerService.getService();
			List<Customer> result = service.getCustomer(key);
			return result.toArray();
		}
		
		return null;
	}

}
