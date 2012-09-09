package com.xys.cenxi.customer.ui.component.forestry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.ForestRights;
import com.xys.cenxi.customer.util.Util;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ForestryInfoCmp extends Composite {
	private Text textRightID;
	private Text textVariety;
	
	private Customer master;
	
	private ForestRights forest;
	private Text textPrice;
	private Text textArea;
	private Text textDesc;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ForestryInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(6, false));
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6797\u6743\u8BC1\u53F7\uFF1A");
		
		textRightID = new Text(composite, SWT.BORDER);
		GridData gd_textRightID = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textRightID.widthHint = 68;
		textRightID.setLayoutData(gd_textRightID);
		new Label(composite, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u4E3B\u8981\u54C1\u79CD\uFF1A");
		
		textVariety = new Text(composite, SWT.BORDER);
		GridData gd_textVariety = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textVariety.widthHint = 92;
		textVariety.setLayoutData(gd_textVariety);
		
		Label lblsdfdsd = new Label(composite, SWT.NONE);
		lblsdfdsd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblsdfdsd.setText("*");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u9762\u79EF\uFF08\u4EA9\uFF09\uFF1A");
		
		textArea = new Text(composite, SWT.BORDER);
		textArea.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验种植面积
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
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u5907\u6CE8\uFF1A");
		
		textDesc = new Text(composite, SWT.BORDER);
		GridData gd_textDesc = new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1);
		gd_textDesc.widthHint = 128;
		textDesc.setLayoutData(gd_textDesc);
		new Label(composite, SWT.NONE);

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
		//主要品种
		String name = textVariety.getText();
		if(Util.isEmpty(name)){
			textRightID.setFocus();
			return "请输入主要品种。 ";
		}
		
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
	}
	
	public ForestRights getModifing(){
		return this.forest;
	}
	

	public ForestRights getForestry(){
		if(forest == null){
			forest = new ForestRights();
		}
		if(!Util.isEmpty(textRightID.getText())){
			forest.setRightsID(textRightID.getText());
		}
		if(!Util.isEmpty(textVariety.getText())){
			forest.setVariety(textVariety.getText());
		}
		
		String area = textArea.getText();
		if(!Util.isEmpty(area)){
			forest.setArea(Float.valueOf(area));
		}
		if(this.master != null){
			forest.setOwnerID(master.getRowID());
		}
		if(!Util.isEmpty(textPrice.getText())){
			forest.setPrice(Float.valueOf(textPrice.getText()));
		}
		if(!Util.isEmpty(textDesc.getText())){
			forest.setDesc(textDesc.getText());
		}
		return forest;
	}
	
	public void setForestRights(ForestRights house){
		this.forest = house;
		if(house == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(house.getRightsID())){
			textRightID.setText(house.getRightsID());
		}else{
			textRightID.setText("");
		}
		if(house.getVariety() != null){
			textVariety.setText(house.getVariety());
		}else{
			textVariety.setText("");
		}
		if(house.getPrice() != null){
			textPrice.setText(Util.toPlainString(house.getPrice()));
		}else{
			textPrice.setText("");
		}
		if(house.getArea() != null){
			textArea.setText(Util.toPlainString(house.getArea()));
		}else{
			textArea.setText("");
		}
		if(house.getDesc() != null){
			textDesc.setText(house.getDesc());
		}else{
			textDesc.setText("");
		}
		
		textRightID.setFocus();
	}

	private void clearData() {
		textRightID.setText("");
		textVariety.setText("");
		textPrice.setText("");
		textArea.setText("");
		textRightID.setFocus();
		textDesc.setText("");
	}
}
