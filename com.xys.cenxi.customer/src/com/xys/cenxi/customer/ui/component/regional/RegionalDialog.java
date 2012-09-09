package com.xys.cenxi.customer.ui.component.regional;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;

public class RegionalDialog extends Dialog {
	private RegionalTreeCmp regionalTreeCmp;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public RegionalDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		regionalTreeCmp = new RegionalTreeCmp(container, SWT.NONE, check);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "\u786E\u5B9A",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				"\u53D6\u6D88", false);
	}
	
	private boolean check;
	
	private Object result;
	
	public Object getResult(){
		return this.result;
	}
	
	@Override
	protected void okPressed() {
		if(check){
			result = regionalTreeCmp.getCheck();
		}else{
			result = regionalTreeCmp.getSelect();
		}
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
