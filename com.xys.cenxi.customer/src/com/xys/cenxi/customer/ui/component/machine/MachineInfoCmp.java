package com.xys.cenxi.customer.ui.component.machine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.FarmMachine;
import com.xys.cenxi.customer.util.Util;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class MachineInfoCmp extends Composite {
	private Text textName;
	
	private Customer master;
	
	private FarmMachine machine;
	private Text textPrice;
	private Text textYear;
	private Text textDesc;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MachineInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u673A\u68B0\u540D\u79F0\uFF1A");
		
		textName = new Text(composite, SWT.BORDER);
		GridData gd_textName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textName.widthHint = 68;
		textName.setLayoutData(gd_textName);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("*");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u8D2D\u7F6E\u5E74\u4EFD\uFF1A");
		
		textYear = new Text(composite, SWT.BORDER);
		textYear.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验购建年份：位数不能超过4位
				String text = textYear.getText();
				String newStr = Util.verifyInteger(e, text);

				if(newStr.length() > 4){
					e.doit = false;
				}
			}
		});
		GridData gd_textYear = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textYear.widthHint = 128;
		textYear.setLayoutData(gd_textYear);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u8BC4\u4F30\u4EF7\u683C(\u4E07\u5143)\uFF1A");
		
		textPrice = new Text(composite, SWT.BORDER);
		textPrice.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验评估价格
				String text = textPrice.getText();
				String oldText = text;
				if(oldText.length() > 0){
					oldText = text.substring(0, e.start) + text.substring(e.end, text.length());
				}
				String newStr = oldText + e.text;
				if(newStr.length() == 0){
					e.doit = true;
					return;
				}
				if(newStr.length() > 10){
					e.doit = false;
					return;
				}
				e.doit = Util.checkFloat(oldText, e.text);
			}
		});
		textPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u5907\u6CE8\uFF1A");
		
		textDesc = new Text(composite, SWT.BORDER);
		textDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
	/**
	 * 校验输入数据是否正确，如果不正确，返回提示信息，如果全部正确
	 * @return
	 */
	public String validateData(){
		//名称
		String name = textName.getText();
		if(Util.isEmpty(name)){
			textName.setFocus();
			return "请输入机械名称。 ";
		}
		
		textName.setText(name.trim());
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
	}
	
	public FarmMachine getModifing(){
		return this.machine;
	}
	

	public FarmMachine getFarmMachine(){
		if(machine == null){
			machine = new FarmMachine();
		}
		
		if(!Util.isEmpty(textName.getText())){
			machine.setName(textName.getText());
		}else{
			machine.setName("");
		}
		
		if(this.master != null){
			machine.setOwnerID(master.getRowID());
		}else{
			machine.setOwnerID(null);
		}
		
		if(!Util.isEmpty(textPrice.getText())){
			machine.setPrice(Float.valueOf(textPrice.getText()));
		}else{
			machine.setPrice(null);
		}
		
		if(!Util.isEmpty(textYear.getText())){
			machine.setBuyYear(Integer.valueOf(textYear.getText()));
		}else{
			machine.setBuyYear(null);
		}
		
		if(!Util.isEmpty(textDesc.getText())){
			machine.setDesc(textDesc.getText());
		}else{
			machine.setDesc(null);
		}
		return machine;
	}
	
	public void setMachine(FarmMachine vehicle1){
		this.machine = vehicle1;
		if(vehicle1 == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(machine.getName())){
			textName.setText(machine.getName());
		}else{
			textName.setText("");
		}

		if(machine.getPrice() != null){
			textPrice.setText(Util.toPlainString(machine.getPrice()));
		}else{
			textPrice.setText("");
		}
		
		if(machine.getBuyYear() != null){
			textYear.setText(machine.getBuyYear().toString());
		}else{
			textYear.setText("");
		}
		
		if(!Util.isEmpty(machine.getDesc())){
			textDesc.setText(machine.getDesc());
		}else{
			textDesc.setText("");
		}
		
		textName.setFocus();
	}

	private void clearData() {
		textName.setText("");
		textPrice.setText("");
		textYear.setText("");
		textDesc.setText("");
		textName.setFocus();
	}
}
