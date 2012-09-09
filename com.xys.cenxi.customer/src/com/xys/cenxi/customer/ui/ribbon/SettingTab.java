package com.xys.cenxi.customer.ui.ribbon;

import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.xys.cenxi.customer.ui.ribbon.action.ModifyUserAction;

public class SettingTab {
	
	private final RibbonTabFolder parent;
	
	private final String name = "����";
	
	private RibbonTab rtSetting;
	
	private RibbonGroup rgSetting;
	
	
	private RibbonButton rbUserModify;
	
	
	public SettingTab(RibbonTabFolder parent) {
		this.parent = parent;
	}
	
	public  void createContent(){
		rtSetting = new RibbonTab(parent, name);
		
		rgSetting = new RibbonGroup(rtSetting, "�û�");
		rbUserModify = new RibbonButton(rgSetting, null, "�޸��û�������", RibbonButton.STYLE_NO_DEPRESS);
		
//		rgOtherInfo = new RibbonGroup(rtCustom, "��ũ����Ϣ");
////		rbgOther = new RibbonButtonGroup(rgOtherInfo);
//		rbOther = new RibbonButton(rgOtherInfo, null, "��ũ����Ϣ", RibbonButton.STYLE_NO_DEPRESS);
		
		initAction();
	}

	private void initAction(){
		rbUserModify.addSelectionListener(new ModifyUserAction());
	}
}
