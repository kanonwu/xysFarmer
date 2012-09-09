package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.CustomerSelectMsg;
import com.xys.cenxi.customer.ui.component.infomgr.CustomerMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class CustomerSelectListener implements IMessageListener {
	
	private CustomerMgrCmp handler;
	
	public CustomerSelectListener(CustomerMgrCmp handler){
		this.handler = handler;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof CustomerSelectMsg){
			CustomerSelectMsg selMsg = (CustomerSelectMsg) msg;
			handler.setCustomer(selMsg.selectCustomer);
		}
	}

}
