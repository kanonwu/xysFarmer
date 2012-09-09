package com.xys.cenxi.customer.ui.component.infomgr;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.data.EducationService;
import com.xys.cenxi.customer.data.GenderService;
import com.xys.cenxi.customer.data.HealthyService;
import com.xys.cenxi.customer.data.MarryService;
import com.xys.cenxi.customer.data.RelationService;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Family;
import com.xys.cenxi.customer.util.UIUtil;
import com.xys.cenxi.customer.util.Util;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class FamilyInfoCmp extends Composite {
	private Text textName;
	private Text textIdentify;
	private Text textPhone;
	private Combo cbRelation;
	private Combo cbGender;
	private DateTime dtBirthday;
	private Combo cbHealthy;
	private Combo cbEducation;
	private Combo cbMarry;
	
	private Customer master;
	
	private Family family;
	private Text textAge;
	private Label lblIdLength;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FamilyInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(6, false));
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u59D3\u540D\uFF1A");
		
		textName = new Text(composite, SWT.BORDER);
		GridData gd_textName = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textName.widthHint = 98;
		textName.setLayoutData(gd_textName);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("*");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u8EAB\u4EFD\u8BC1\uFF1A");
		
		textIdentify = new Text(composite, SWT.BORDER);
		textIdentify.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				int length = textIdentify.getText().length();
				String str = "*";
				if(length > 0){
					str += length;
				}
				lblIdLength.setText(str);
				lblIdLength.getParent().layout();
				
				if(length > 15){
					idCardProcess(textIdentify.getText());
				}
			}
		});
		textIdentify.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Util.verifyID(e, textIdentify.getText());
			}
		});
		GridData gd_textIdentify = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textIdentify.widthHint = 266;
		textIdentify.setLayoutData(gd_textIdentify);
		
		lblIdLength = new Label(composite, SWT.NONE);
		lblIdLength.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblIdLength.setText("*");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u4E0E\u6237\u4E3B\u5173\u7CFB\uFF1A");
		
		cbRelation = new Combo(composite, SWT.NONE);
		cbRelation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setText("*");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u6027\u522B\uFF1A");
		
		cbGender = new Combo(composite, SWT.READ_ONLY);
		cbGender.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u51FA\u751F\u65E5\u671F\uFF1A");
		
		dtBirthday = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		GridData gd_dtBirthday = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_dtBirthday.widthHint = 114;
		dtBirthday.setLayoutData(gd_dtBirthday);
		new Label(composite, SWT.NONE);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_9.setText("\u5E74\u9F84\uFF1A");
		
		textAge = new Text(composite, SWT.BORDER);
		textAge.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验年龄：位数不能超过2位
				String text = textAge.getText();
				String newStr = Util.verifyInteger(e, text);

				if(newStr.length() > 2){
					e.doit = false;
				}
			}
		});
		textAge.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u6587\u5316\u7A0B\u5EA6\uFF1A");
		
		cbEducation = new Combo(composite, SWT.READ_ONLY);
		cbEducation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u5065\u5EB7\u72B6\u51B5\uFF1A");
		
		cbHealthy = new Combo(composite, SWT.READ_ONLY);
		cbHealthy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		textPhone = new Text(composite, SWT.BORDER);
		textPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("\u5A5A\u59FB\u72B6\u51B5\uFF1A");
		
		cbMarry = new Combo(composite, SWT.READ_ONLY);
		cbMarry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite, SWT.NONE);

		initInput();
	}

	protected void idCardProcess(String idCard) {
		if(idCard == null || idCard.length() < 15){
			return;
		}
		Calendar birthday = Util.idCardVlidator.getBirthday(idCard);
		if(birthday != null){
			dtBirthday.setYear(birthday.get(Calendar.YEAR));
			dtBirthday.setDay(birthday.get(Calendar.DAY_OF_MONTH));
			dtBirthday.setMonth(birthday.get(Calendar.MONTH));
			
			Calendar cal =  Calendar.getInstance();
			int age = cal.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
			textAge.setText(String.valueOf(age));
		}else{
			textAge.setText("");
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void initInput(){
		String[] genders = GenderService.getInstant().getGenders();
		cbGender.setItems(genders);
		if(genders.length > 0){
			cbGender.select(0);
		}
		
		String[] marry = MarryService.getInstant().getMarrys();
		cbMarry.setItems(marry);
		if(marry.length > 0){
			cbMarry.select(0);
		}
		
		String[] edu = EducationService.getInstant().getEducations();
		cbEducation.setItems(edu);
		if(edu.length > 0){
			cbEducation.select(0);
		}
		
		String[] healthy = HealthyService.getInstant().getHealthy();
		cbHealthy.setItems(healthy);
		if(healthy.length > 0){
			cbHealthy.select(0);
		}
		
		String[] relation = RelationService.getInstant().getRelation();
		cbRelation.setItems(relation);
		if(relation.length > 0){
			cbRelation.select(0);
		}
	}
	
	/**
	 * 校验输入数据是否正确，如果不正确，返回提示信息，如果全部正确
	 * @return
	 */
	public String validateData(){
		//姓名
		String name = textName.getText();
		if(Util.isEmpty(name)){
			textName.setFocus();
			return "请输入姓名 ";
		}
		
		textName.setText(name.trim());
		
		String relation = cbRelation.getText();
		if(Util.isEmpty(relation)){
			cbRelation.setFocus();
			return " 请输入与户主关系。";
		}
		
		return null;
	}
	
	public void setCustomer(Customer cus){
		this.master = cus;
	}
	
	public Family getModifingFamily(){
		return this.family;
	}
	

	public Family getFamily(){
		if(family == null){
			family = new Family();
		}
		family.setName(textName.getText());
		family.setBirthday(Util.getDate(dtBirthday));
		family.setEducation(cbEducation.getText());
		family.setGender(cbGender.getText());
		family.setHealthy(cbHealthy.getText());
		family.setIdentify(textIdentify.getText());
		family.setMarry(cbMarry.getText());
		family.setPhone(textPhone.getText());
		family.setRelation(cbRelation.getText());
		if(!Util.isEmpty(textAge.getText())){
			family.setAge(Integer.valueOf(textAge.getText()));
		}
		if(this.master != null){
			family.setOwnerID(master.getRowID());
		}
		return family;
	}
	
	public void setFamily(Family fa){
		this.family = fa;
		if(fa == null){
			clearData();
			return;
		}
		
		if(!Util.isEmpty(fa.getName())){
			textName.setText(fa.getName());
		}else{
			textName.setText("");
		}
		if(!Util.isEmpty(fa.getIdentify())){
			textIdentify.setText(fa.getIdentify());
		}else{
			textIdentify.setText("");
		}
		if(!Util.isEmpty(fa.getPhone())){
			textPhone.setText(fa.getPhone());
		}else{
			textPhone.setText("");
		}
		if(!Util.isEmpty(fa.getGender())){
			cbGender.setText(fa.getGender());
		}else{
			cbGender.setText("");
		}
		if(!Util.isEmpty(fa.getRelation())){
			cbRelation.setText(fa.getRelation());
		}else{
			cbRelation.setText("");
		}
		if(fa.getBirthday() != null){
			UIUtil.setDate(dtBirthday, fa.getBirthday());
		}else{
			UIUtil.setDate(dtBirthday, new Date());
		}
		if(!Util.isEmpty(fa.getHealthy())){
			cbHealthy.setText(fa.getHealthy());
		}else{
			cbHealthy.setText("");
		}
		if(!Util.isEmpty(fa.getEducation())){
			cbEducation.setText(fa.getEducation());
		}else{
			cbEducation.setText("");
		}
		if(!Util.isEmpty(fa.getMarry())){
			cbMarry.setText(fa.getMarry());
		}else{
			cbMarry.setText("");
		}
		
		textName.setFocus();
	}

	private void clearData() {
		textName.setText("");
		textIdentify.setText("");
		textPhone.setText("");
		cbRelation.setText("");
		cbGender.setText("");
		cbEducation.setText("");
		Point selPoint = new Point(0, 0);
		Calendar cal = Calendar.getInstance();
		dtBirthday.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		cbHealthy.setSelection(selPoint);
		cbMarry.setSelection(selPoint);
		
		textName.setFocus();
	}
}
