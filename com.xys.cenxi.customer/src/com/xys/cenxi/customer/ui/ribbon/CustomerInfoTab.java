package com.xys.cenxi.customer.ui.ribbon;

import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.xys.cenxi.customer.ui.ribbon.action.CustomerMgrAction;

public class CustomerInfoTab {
	
	private final RibbonTabFolder parent;
	
	private final String name = "�ͻ���Ϣ";
	
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
		
		rgFarmerInfo = new RibbonGroup(rtCustom, "ũ����Ϣ");
		rbFarmerInfo = new RibbonButton(rgFarmerInfo, null, "ũ����Ϣ", RibbonButton.STYLE_NO_DEPRESS);
		
//		rgOtherInfo = new RibbonGroup(rtCustom, "��ũ����Ϣ");
////		rbgOther = new RibbonButtonGroup(rgOtherInfo);
//		rbOther = new RibbonButton(rgOtherInfo, null, "��ũ����Ϣ", RibbonButton.STYLE_NO_DEPRESS);
		
		initAction();
	}

	private void initAction(){
		rbFarmerInfo.addSelectionListener(new CustomerMgrAction());
	}
}
