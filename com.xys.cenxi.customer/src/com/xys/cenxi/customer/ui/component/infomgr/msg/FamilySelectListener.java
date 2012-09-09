package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.FamilySelectMsg;
import com.xys.cenxi.customer.ui.component.infomgr.FamilyMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class FamilySelectListener implements IMessageListener {
	
	private FamilyMgrCmp mgr;
	
	public FamilySelectListener(FamilyMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof FamilySelectMsg){
			FamilySelectMsg fsm = (FamilySelectMsg) msg;
			mgr.setFamily(fsm.family);
		}
	}

}
