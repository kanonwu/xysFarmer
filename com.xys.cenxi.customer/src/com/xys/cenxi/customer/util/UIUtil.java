package com.xys.cenxi.customer.util;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UIUtil {

	public static void showMessage(String mess){
		Shell shell = Display.getDefault().getActiveShell();
		MessageBox mb = new MessageBox(shell);
		mb.setMessage(mess);
		mb.setText("ÌבÊ¾");
		mb.open();
	}
	
	public static void setDate(DateTime dt, Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dt.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
	}
	
}
