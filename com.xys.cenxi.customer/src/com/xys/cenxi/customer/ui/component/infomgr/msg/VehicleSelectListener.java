package com.xys.cenxi.customer.ui.component.infomgr.msg;

import com.xys.cenxi.customer.msg.VehicleSelectMsg;
import com.xys.cenxi.customer.ui.component.vehicle.VehicleMgrCmp;
import com.xys.cenxi.message.IMessageListener;

public class VehicleSelectListener implements IMessageListener {
	
	private VehicleMgrCmp mgr;
	
	public VehicleSelectListener(VehicleMgrCmp arg){
		this.mgr = arg;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof VehicleSelectMsg){
			VehicleSelectMsg fsm = (VehicleSelectMsg) msg;
			mgr.setVehicle(fsm.vehicle);
		}
	}

}
