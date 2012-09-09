package com.xys.cenxi.customer.ui.ribbon;

import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.xys.cenxi.customer.ui.ribbon.action.ModifyUserAction;

public class SettingTab {
	
	private final RibbonTabFolder parent;
	
	private final String name = "设置";
	
	private RibbonTab rtSetting;
	
	private RibbonGroup rgSetting;
	
	
	private RibbonButton rbUserModify;
	
	
	public SettingTab(RibbonTabFolder parent) {
		this.parent = parent;
	}
	
	public  void createContent(){
		rtSetting = new RibbonTab(parent, name);
		
		rgSetting = new RibbonGroup(rtSetting, "用户");
		rbUserModify = new RibbonButton(rgSetting, null, "修改用户名密码", RibbonButton.STYLE_NO_DEPRESS);
		
//		rgOtherInfo = new RibbonGroup(rtCustom, "非农户信息");
////		rbgOther = new RibbonButtonGroup(rgOtherInfo);
//		rbOther = new RibbonButton(rgOtherInfo, null, "非农户信息", RibbonButton.STYLE_NO_DEPRESS);
		
		initAction();
	}

	private void initAction(){
		rbUserModify.addSelectionListener(new ModifyUserAction());
	}
}
