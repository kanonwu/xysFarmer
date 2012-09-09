package com.xys.cenxi.customer.ui.component.credidt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.xys.cenxi.customer.data.CredidtService;
import com.xys.cenxi.customer.pojo.Credidt;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.DebitCnd;
import com.xys.cenxi.customer.util.UIUtil;

public class CredidiMgrCmp extends Composite {
	private CredidtInfoCmp credidtInfoCmp;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CredidiMgrCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		credidtInfoCmp = new CredidtInfoCmp(this, SWT.NONE);
		credidtInfoCmp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSave();
			}
		});
		button.setImage(SWTResourceManager.getImage(CredidiMgrCmp.class, "/icons/save.gif"));
		button.setBounds(10, 0, 80, 27);
		button.setText("\u4FDD\u5B58");

	}
	
	private Customer customer;
	
	public void setCustomer(Customer cus){
		this.customer = cus;
		credidtInfoCmp.setCustomer(cus);
	}

	protected void doSave() {
		if(this.customer == null)
			return;
		
		Credidt cre = credidtInfoCmp.getCredidt();
		DebitCnd debit = credidtInfoCmp.getDebitCnd();
		if(cre.getRowID() == null){
			//新增
			CredidtService.getInstance().add(cre);
		}else{
			//更新
			CredidtService.getInstance().update(cre);
		}
		if(debit.getRowID() == null){
			CredidtService.getInstance().add(debit);
		}else{
			CredidtService.getInstance().update(debit);
		}
		
		UIUtil.showMessage("保存完成。");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
