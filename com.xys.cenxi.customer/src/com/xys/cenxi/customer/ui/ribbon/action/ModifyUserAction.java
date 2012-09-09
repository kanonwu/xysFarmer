package com.xys.cenxi.customer.ui.ribbon.action;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;

import com.xys.cenxi.customer.ui.component.user.ModifyUserDialog;

public class ModifyUserAction implements SelectionListener {

	@Override
	public void widgetSelected(SelectionEvent e) {
		//打开农户信息管理界面
		ModifyUserDialog dia = new ModifyUserDialog(Display.getDefault().getActiveShell());
		dia.open();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
