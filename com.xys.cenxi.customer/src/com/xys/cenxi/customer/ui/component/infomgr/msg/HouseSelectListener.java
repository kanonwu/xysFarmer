package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.HousingSelectMsg;
import com.xys.cenxi.customer.ui.component.house.HouseMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class HouseSelectListener implements IMessageListener {
	
	private HouseMgrCmp mgr;
	
	public HouseSelectListener(HouseMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof HousingSelectMsg){
			HousingSelectMsg fsm = (HousingSelectMsg) msg;
			mgr.setHouse(fsm.house);
		}
	}

}
