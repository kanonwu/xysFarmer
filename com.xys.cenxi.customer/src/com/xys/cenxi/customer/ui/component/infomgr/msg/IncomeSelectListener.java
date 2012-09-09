package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.IncomeSelectMsg;
import com.xys.cenxi.customer.ui.component.income.InOutMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class IncomeSelectListener implements IMessageListener {
	
	private InOutMgrCmp mgr;
	
	public IncomeSelectListener(InOutMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof IncomeSelectMsg){
			IncomeSelectMsg fsm = (IncomeSelectMsg) msg;
			mgr.setIncome(fsm.income);
		}
	}

}
