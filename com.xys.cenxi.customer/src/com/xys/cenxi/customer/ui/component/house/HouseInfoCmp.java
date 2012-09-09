package com.xys.cenxi.customer.ui.component.house;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Housing;
import com.xys.cenxi.customer.util.Util;

public class HouseInfoCmp extends Composite {
	private Text textAddress;
	private Text textYear;
	private Combo cbProperties;
	private Combo cbStructure;
	
	private Customer master;
	
	private Housing house;
	private Text textPrice;
	private Text textArea;
	private Text textDesc;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HouseInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u5EFA\u7B51\u5730\u5740\uFF1A");
		
		textAddress = new Text(composite, SWT.BORDER);
		textAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("*");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u8D2D\u5EFA\u5E74\u4EFD\uFF1A");
		
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
		gd_textYear.widthHint = 137;
		textYear.setLayoutData(gd_textYear);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u623F\u4EA7\u7528\u9014\uFF1A");
		
		cbProperties = new Combo(composite, SWT.NONE);
		cbProperties.setItems(new String[] {"\u4F4F\u5B85", "\u6210\u5957\u4F4F\u5B85", "\u5546\u4E1A\u7528\u623F", "\u7ECF\u8425\u7528\u623F", "\u5176\u4ED6"});
		cbProperties.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u7ED3\u6784\u7C7B\u522B\uFF1A");
		
		cbStructure = new Combo(composite, SWT.NONE);
		cbStructure.setItems(new String[] {"\u6DF7\u7816\u7ED3\u6784", "\u94A2\u7B4B\u6DF7\u51DD\u571F\u7ED3\u6784", "\u7816\u6728\u7ED3\u6784", "\u7B80\u6613\u7ED3\u6784"});
		cbStructure.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u5EFA\u7B51\u9762\u79EF\uFF1A");
		
		textArea = new Text(composite, SWT.BORDER);
		textArea.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验建筑面积
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
		textArea.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
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
		textPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u5907\u6CE8\uFF1A");
		
		textDesc = new Text(composite, SWT.BORDER);
		textDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

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
		//地址
		String name = textAddress.getText();
		if(Util.isEmpty(name)){
			textAddress.setFocus();
			return "请输入建筑地址。 ";
		}
		
		textAddress.setText(name.trim());
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
		setHouse(null);
	}
	
	public Housing getModifing(){
		return this.house;
	}
	

	public Housing getHouse(){
		if(house == null){
			house = new Housing();
		}
		house.setAddress(textAddress.getText());
		String area = textArea.getText();
		if(!Util.isEmpty(area)){
			house.setArea(Float.valueOf(area));
		}
		house.setDesc(textDesc.getText());
		if(this.master != null){
			house.setOwnerID(master.getRowID());
		}
		if(!Util.isEmpty(textPrice.getText())){
			house.setPrice(Float.valueOf(textPrice.getText()));
		}
		if(!Util.isEmpty(cbProperties.getText())){
			house.setProperties(cbProperties.getText());
		}
		if(!Util.isEmpty(cbStructure.getText())){
			house.setStructure(cbStructure.getText());
		}
		if(!Util.isEmpty(textYear.getText())){
			house.setYear(Integer.valueOf(textYear.getText()));
		}
		return house;
	}
	
	public void setHouse(Housing house){
		this.house = house;
		if(house == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(house.getAddress())){
			textAddress.setText(house.getAddress());
		}else{
			textAddress.setText("");
		}
		if(house.getYear() != null){
			textYear.setText(house.getYear().toString());
		}else{
			textYear.setText("");
		}
		if(!Util.isEmpty(house.getProperties())){
			cbProperties.setText(house.getProperties());
		}else{
			cbProperties.setText("");
		}
		if(!Util.isEmpty(house.getStructure())){
			cbStructure.setText(house.getStructure());
		}else{
			cbStructure.setText("");
		}
		if(house.getPrice() != null){
			textPrice.setText(Util.toPlainString(house.getPrice()));
		}else{
			textPrice.setText("");
		}
		if(house.getArea() != null){
			textArea.setText(house.getArea().toString());
		}else{
			textArea.setText("");
		}
		if(!Util.isEmpty(house.getDesc())){
			textDesc.setText(house.getDesc());
		}else{
			textDesc.setText("");
		}
		
		textAddress.setFocus();
	}

	private void clearData() {
		textAddress.setText("");
		textYear.setText("");
		cbProperties.setText("");
		cbStructure.setText("");
		textDesc.setText("");
		textPrice.setText("");
		textArea.setText("");
		textAddress.setFocus();
	}
}
