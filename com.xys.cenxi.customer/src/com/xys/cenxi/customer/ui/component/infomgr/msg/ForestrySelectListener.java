package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.ForestSelectMsg;
import com.xys.cenxi.customer.ui.component.forestry.ForestryMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class ForestrySelectListener implements IMessageListener {
	
	private ForestryMgrCmp mgr;
	
	public ForestrySelectListener(ForestryMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof ForestSelectMsg){
			ForestSelectMsg fsm = (ForestSelectMsg) msg;
			mgr.setForestry(fsm.forest);
		}
	}

}
