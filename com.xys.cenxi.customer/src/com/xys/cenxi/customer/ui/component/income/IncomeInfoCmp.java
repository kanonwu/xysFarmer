package com.xys.cenxi.customer.ui.component.income;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.FarmIncome;
import com.xys.cenxi.customer.util.Util;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class IncomeInfoCmp extends Composite {
	private Text textVariety;
	
	private Customer master;
	
	private FarmIncome income;
	private Text textArea;
	private Text textOutput;
	private Text textIncome;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public IncomeInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u79CD\u517B\u54C1\u79CD\uFF1A");
		
		textVariety = new Text(composite, SWT.BORDER);
		GridData gd_textVariety = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textVariety.widthHint = 68;
		textVariety.setLayoutData(gd_textVariety);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("*");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u6570\u91CF/\u4EA7\u91CF\uFF1A");
		
		textOutput = new Text(composite, SWT.BORDER);
		GridData gd_textOutput = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textOutput.widthHint = 90;
		textOutput.setLayoutData(gd_textOutput);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u5360\u5730\u9762\u79EF(\u4EA9)\uFF1A");
		
		textArea = new Text(composite, SWT.BORDER);
		textArea.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验占地面积
				String text = textArea.getText();
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
		textArea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u5E74\u5EA6\u6536\u5165(\u4E07\u5143)\uFF1A");
		
		textIncome = new Text(composite, SWT.BORDER);
		textIncome.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验年度收入
				String text = textIncome.getText();
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
		textIncome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		initInput();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void initInput(){
	}
	
	/**
	 * 校验输入数据是否正确，如果不正确，返回提示信息，如果全部正确
	 * @return
	 */
	public String validateData(){
		//名称
		String name = textVariety.getText();
		if(Util.isEmpty(name)){
			textVariety.setFocus();
			return "请输入机械名称。 ";
		}
		
		textVariety.setText(name.trim());
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
	}
	
	public FarmIncome getModifing(){
		return this.income;
	}
	

	public FarmIncome getFarmIncome(){
		if(income == null){
			income = new FarmIncome();
		}
		
		if(!Util.isEmpty(textVariety.getText())){
			income.setVariety(textVariety.getText());
		}else{
			income.setVariety(null);
		}
		
		if(this.master != null){
			income.setOwnerID(master.getRowID());
		}else{
			income.setOwnerID(null);
		}
		
		if(!Util.isEmpty(textArea.getText())){
			income.setArea(Float.valueOf(textArea.getText()));
		}else{
			income.setArea(null);
		}
		
		if(!Util.isEmpty(textOutput.getText())){
			income.setOutput(textOutput.getText());
		}else{
			income.setOutput(null);
		}
		
		if(!Util.isEmpty(textIncome.getText())){
			income.setIncome(Float.valueOf(textIncome.getText()));
		}else{
			income.setIncome(null);
		}
		
		return income;
	}
	
	public void setIncome(FarmIncome income1){
		this.income = income1;
		if(income1 == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(income.getVariety())){
			textVariety.setText(income.getVariety());
		}else{
			textVariety.setText("");
		}

		if(income.getArea() != null){
			textArea.setText(Util.toPlainString(income.getArea()));
		}else{
			textArea.setText("");
		}
		
		if(income.getIncome() != null){
			textIncome.setText(Util.toPlainString(income.getIncome()));
		}else{
			textIncome.setText("");
		}
		
		if(!Util.isEmpty(income.getOutput())){
			textOutput.setText(income.getOutput());
		}else{
			textOutput.setText("");
		}
		
		textVariety.setFocus();
	}

	private void clearData() {
		textVariety.setText("");
		textArea.setText("");
		textOutput.setText("");
		textVariety.setFocus();
		textIncome.setText("");
	}
}
