package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.MachineSelectMsg;
import com.xys.cenxi.customer.ui.component.machine.MachineMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class MachineSelectListener implements IMessageListener {
	
	private MachineMgrCmp mgr;
	
	public MachineSelectListener(MachineMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof MachineSelectMsg){
			MachineSelectMsg fsm = (MachineSelectMsg) msg;
			mgr.setMachine(fsm.machine);
		}
	}

}
