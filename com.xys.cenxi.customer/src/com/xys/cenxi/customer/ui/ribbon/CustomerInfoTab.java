package com.xys.cenxi.customer.ui.ribbon;

import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.xys.cenxi.customer.ui.ribbon.action.CustomerMgrAction;

public class CustomerInfoTab {
	
	private final RibbonTabFolder parent;
	
	private final String name = "客户信息";
	
	private RibbonTab rtCustom;
	
	private RibbonGroup rgFarmerInfo;
	
//	private RibbonButtonGroup rbgFarmerInfo;
	
	private RibbonButton rbFarmerInfo;
	
//	private RibbonGroup rgOtherInfo;
	
//	private RibbonButtonGroup rbgOther;
	
//	private RibbonButton rbOther;
	
	public CustomerInfoTab(RibbonTabFolder parent) {
		this.parent = parent;
	}
	
	public  void createContent(){
		rtCustom = new RibbonTab(parent, name);
		
		rgFarmerInfo = new RibbonGroup(rtCustom, "农户信息");
		rbFarmerInfo = new RibbonButton(rgFarmerInfo, null, "农户信息", RibbonButton.STYLE_NO_DEPRESS);
		
//		rgOtherInfo = new RibbonGroup(rtCustom, "非农户信息");
////		rbgOther = new RibbonButtonGroup(rgOtherInfo);
//		rbOther = new RibbonButton(rgOtherInfo, null, "非农户信息", RibbonButton.STYLE_NO_DEPRESS);
		
		initAction();
	}

	private void initAction(){
		rbFarmerInfo.addSelectionListener(new CustomerMgrAction());
	}
}
