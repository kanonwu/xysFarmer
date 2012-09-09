package com.xys.cenxi.customer.ui.component.vehicle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Vehicle;
import com.xys.cenxi.customer.util.Util;

public class VehicleInfoCmp extends Composite {
	private Text textLicense;
	
	private Customer master;
	
	private Vehicle vehicle;
	private Text textPrice;
	private Text textType;
	private Text textDisplacement;
	private Text textAddYear;
	private Text textDesc;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VehicleInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u8F66\u8F86\u7C7B\u578B\uFF1A");
		
		textType = new Text(composite, SWT.BORDER);
		textType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("*");
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u8F66\u724C\u53F7\u7801\uFF1A");
		
		textLicense = new Text(composite, SWT.BORDER);
		GridData gd_textLicense = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textLicense.widthHint = 68;
		textLicense.setLayoutData(gd_textLicense);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u8D2D\u7F6E\u5E74\u4EFD\uFF1A");
		
		textAddYear = new Text(composite, SWT.BORDER);
		textAddYear.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Util.verifyInteger(e, textAddYear.getText());
			}
		});
		textAddYear.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u6392\u91CF\u5428\u4F4D\uFF1A");
		
		textDisplacement = new Text(composite, SWT.BORDER);
		GridData gd_textDisplacement = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textDisplacement.widthHint = 128;
		textDisplacement.setLayoutData(gd_textDisplacement);
		
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
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u5907\u6CE8\uFF1A");
		
		textDesc = new Text(composite, SWT.BORDER);
		textDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
		//车辆类型
		String name = textType.getText();
		if(Util.isEmpty(name)){
			textType.setFocus();
			return "请输入车辆类型。 ";
		}
		
		textType.setText(name.trim());
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
	}
	
	public Vehicle getModifing(){
		return this.vehicle;
	}
	

	public Vehicle getVehicle(){
		if(vehicle == null){
			vehicle = new Vehicle();
		}
		
		if(!Util.isEmpty(textLicense.getText())){
			vehicle.setLicense(textLicense.getText());
		}else{
			vehicle.setLicense(null);
		}
		if(!Util.isEmpty(textType.getText())){
			vehicle.setType(textType.getText());
		}else{
			vehicle.setType(null);
		}
		
		if(this.master != null){
			vehicle.setOwnerID(master.getRowID());
		}else{
			vehicle.setOwnerID(null);
		}
		
		if(!Util.isEmpty(textPrice.getText())){
			vehicle.setPrice(Float.valueOf(textPrice.getText()));
		}else{
			vehicle.setPrice(null);
		}
		
		if(!Util.isEmpty(textDisplacement.getText())){
			vehicle.setDisplacement(textDisplacement.getText());
		}else{
			vehicle.setDisplacement(null);
		}
		
		if(!Util.isEmpty(textAddYear.getText())){
			vehicle.setBuyYear(Integer.valueOf(textAddYear.getText()));
		}
		if(!Util.isEmpty(textDesc.getText())){
			vehicle.setDesc(textDesc.getText());
		}
		
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle1){
		this.vehicle = vehicle1;
		if(vehicle1 == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(vehicle.getLicense())){
			textLicense.setText(vehicle.getLicense());
		}else{
			textLicense.setText("");
		}

		if(vehicle.getPrice() != null){
			textPrice.setText(Util.toPlainString(vehicle.getPrice()));
		}else{
			textPrice.setText("");
		}
		
		if(!Util.isEmpty(vehicle.getDisplacement())){
			textDisplacement.setText(vehicle.getDisplacement());
		}else{
			textDisplacement.setText("");
		}
		
		if(!Util.isEmpty(vehicle.getType())){
			textType.setText(vehicle.getType());
		}else{
			textType.setText("");
		}
		
		if(vehicle.getBuyYear() != null){
			textAddYear.setText(vehicle.getBuyYear().toString());
		}else{
			textAddYear.setText("");
		}
		
		if(!Util.isEmpty(vehicle.getDesc())){
			textDesc.setText(vehicle.getDesc());
		}
		
		textType.setFocus();
	}

	private void clearData() {
		textLicense.setText("");
		textPrice.setText("");
		textType.setText("");
		textLicense.setFocus();
		textDisplacement.setText("");
		textDesc.setText("");
		textAddYear.setText("");
		textType.setFocus();
	}
}
