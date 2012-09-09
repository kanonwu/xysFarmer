package com.xys.cenxi.customer.ui.ribbon.action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.xys.cenxi.customer.start.AppMain;
import com.xys.cenxi.customer.start.Content;
import com.xys.cenxi.customer.ui.component.infomgr.CustomerMgrCmp;

public class CustomerMgrAction implements SelectionListener {

	@Override
	public void widgetSelected(SelectionEvent e) {
		//打开农户信息管理界面
		AppMain main = Content.getAppMain();
		CTabFolder folder = main.getTabFolder();
		
		CTabItem tiCustomer = main.getTabItem(CustomerMgrCmp.NAME); 
		if(tiCustomer == null){
			tiCustomer = new CTabItem(folder, SWT.CLOSE);
			CustomerMgrCmp customerCmp = new CustomerMgrCmp(folder, SWT.NONE);
			tiCustomer.setControl(customerCmp);
			tiCustomer.setText(CustomerMgrCmp.NAME);
		}
		folder.setSelection(tiCustomer);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
