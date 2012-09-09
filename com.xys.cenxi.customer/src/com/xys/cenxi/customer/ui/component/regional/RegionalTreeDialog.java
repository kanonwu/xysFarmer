package com.xys.cenxi.customer.ui.component.regional;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegionalTreeDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RegionalTreeDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private boolean check;
	private Button btnOK;
	private Button btnCancle;
	private RegionalTreeCmp regionalTreeCmp;
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shell.setSize(541, 300);
		shell.setText("\u884C\u653F\u533A\u5212");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		regionalTreeCmp = new RegionalTreeCmp(composite, SWT.NONE, check);
		regionalTreeCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 36;
		gd_composite_1.widthHint = 279;
		composite_1.setLayoutData(gd_composite_1);
		new Label(composite_1, SWT.NONE);
		
		btnOK = new Button(composite_1, SWT.NONE);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doOK();
			}
		});
		btnOK.setImage(SWTResourceManager.getImage(RegionalTreeDialog.class, "/icons/complete_tsk.gif"));
		btnOK.setText("\u786E\u5B9A");
		new Label(composite_1, SWT.NONE);
		
		btnCancle = new Button(composite_1, SWT.NONE);
		btnCancle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCancle();
			}
		});
		btnCancle.setImage(SWTResourceManager.getImage(RegionalTreeDialog.class, "/icons/close_1.gif"));
		btnCancle.setText("\u53D6\u6D88");

	}

	protected void doCancle() {
		// TODO Auto-generated method stub
		
	}

	protected void doOK() {
		if(check){
			result = regionalTreeCmp.getCheck();
		}else{
			result = regionalTreeCmp.getSelect();
		}
		
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}
