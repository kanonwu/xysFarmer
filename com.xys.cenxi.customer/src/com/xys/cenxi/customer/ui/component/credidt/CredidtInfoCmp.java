package com.xys.cenxi.customer.ui.component.credidt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xys.cenxi.customer.data.CredidtService;
import com.xys.cenxi.customer.pojo.Credidt;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.DebitCnd;
import com.xys.cenxi.customer.util.Util;

public class CredidtInfoCmp extends Composite {
	private Text textBank;
	private Text textLoadBalance;
	private Text textLoadFor;
	private Text textLoadAmount;
	private Text textBIAmount;
	private Text textEICount;
	private Text textCMCount;
	private Combo cbOldPeople;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CredidtInfoCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout(4, false));
		GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_group.heightHint = 30;
		group.setLayoutData(gd_group);
		group.setText("\u501F\u8D37\u60C5\u51B5");
		
		Label label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u91D1\u878D\u7ED3\u6784\uFF1A");
		
		textBank = new Text(group, SWT.BORDER);
		GridData gd_textBank = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textBank.widthHint = 150;
		textBank.setLayoutData(gd_textBank);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u8D37\u6B3E\u4F59\u989D(\u4E07\u5143)\uFF1A");
		
		textLoadBalance = new Text(group, SWT.BORDER);
		textLoadBalance.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验输入金额
				Util.verifyMoney(e, textLoadBalance.getText());
			}
		});
		GridData gd_textLoadBalance = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textLoadBalance.widthHint = 88;
		textLoadBalance.setLayoutData(gd_textLoadBalance);
		
		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setLayout(new GridLayout(4, false));
		GridData gd_group_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_group_1.heightHint = 37;
		group_1.setLayoutData(gd_group_1);
		group_1.setText("\u4E24\u5E74\u5185\u8D37\u6B3E\u9700\u6C42\u8BA1\u5212");
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u8D37\u6B3E\u7528\u9014\uFF1A");
		
		textLoadFor = new Text(group_1, SWT.BORDER);
		GridData gd_textLoadFor = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textLoadFor.widthHint = 150;
		textLoadFor.setLayoutData(gd_textLoadFor);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u8BA1\u5212\u8D37\u6B3E\u91D1\u989D(\u4E07\u5143)\uFF1A");
		
		textLoadAmount = new Text(group_1, SWT.BORDER);
		textLoadAmount.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验
				Util.verifyMoney(e, textLoadAmount.getText());
			}
		});
		GridData gd_textLoadAmount = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textLoadAmount.widthHint = 92;
		textLoadAmount.setLayoutData(gd_textLoadAmount);
		
		Group group_2 = new Group(composite, SWT.NONE);
		group_2.setLayout(new GridLayout(10, false));
		GridData gd_group_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_group_2.heightHint = 100;
		group_2.setLayoutData(gd_group_2);
		group_2.setText("\u5176\u4ED6\u91CD\u8981\u4FE1\u606F");
		
		Label label_4 = new Label(group_2, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u656C\u8001\u60C5\u51B5\uFF1A");
		
		cbOldPeople = new Combo(group_2, SWT.READ_ONLY);
		cbOldPeople.setItems(new String[] {"", "\u597D", "\u4E00\u822C", "\u5DEE"});
		cbOldPeople.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbOldPeople.select(0);
		new Label(group_2, SWT.NONE);
		
		Label label_5 = new Label(group_2, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u90BB\u91CC\u5173\u7CFB\uFF1A");
		
		cbNeighbourhood = new Combo(group_2, SWT.READ_ONLY);
		cbNeighbourhood.setItems(new String[] {"", "\u597D", "\u4E00\u822C", "\u5DEE"});
		cbNeighbourhood.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbNeighbourhood.select(0);
		new Label(group_2, SWT.NONE);
		
		Label label_6 = new Label(group_2, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("\u5BF9\u516C\u76CA\u4E8B\u4E1A\uFF1A");
		
		cbPublic = new Combo(group_2, SWT.READ_ONLY);
		cbPublic.setItems(new String[] {"", "\u9AD8", "\u4E00\u822C", "\u4ECE\u4E0D\u5173\u5FC3"});
		cbPublic.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbPublic.select(0);
		new Label(group_2, SWT.NONE);
		new Label(group_2, SWT.NONE);
		
		Label label_7 = new Label(group_2, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("\u5546\u4E1A\u4FDD\u9669\uFF1A");
		
		cbBusinessInsurance = new Combo(group_2, SWT.READ_ONLY);
		cbBusinessInsurance.setItems(new String[] {"", "\u662F", "\u5426"});
		cbBusinessInsurance.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbBusinessInsurance.select(0);
		new Label(group_2, SWT.NONE);
		
		Label label_8 = new Label(group_2, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("\u4FDD\u9669\u91D1\u989D\uFF1A");
		
		textBIAmount = new Text(group_2, SWT.BORDER);
		textBIAmount.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验金额
				Util.verifyMoney(e, textBIAmount.getText());
			}
		});
		textBIAmount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(group_2, SWT.NONE);
		
		Label label_9 = new Label(group_2, SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_9.setText("\u517B\u8001\u4FDD\u9669\uFF1A");
		
		cbEndowmentInsurance = new Combo(group_2, SWT.READ_ONLY);
		cbEndowmentInsurance.setItems(new String[] {"", "\u662F", "\u5426"});
		cbEndowmentInsurance.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbEndowmentInsurance.select(0);
		
		Label label_10 = new Label(group_2, SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_10.setText("\u53C2\u52A0\u4EBA\u6570\uFF1A");
		
		textEICount = new Text(group_2, SWT.BORDER);
		textEICount.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验参加人数：位数不能超过4位
				String text = textEICount.getText();
				String newStr = Util.verifyInteger(e, text);
				if(newStr.length() > 4){
					e.doit = false;
				}
			}
		});
		textEICount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_11 = new Label(group_2, SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_11.setText("\u5408\u4F5C\u533B\u7597\uFF1A");
		
		cbCooperativeMedical = new Combo(group_2, SWT.READ_ONLY);
		cbCooperativeMedical.setItems(new String[] {"", "\u662F", "\u5426"});
		cbCooperativeMedical.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cbCooperativeMedical.select(0);
		new Label(group_2, SWT.NONE);
		
		Label label_12 = new Label(group_2, SWT.NONE);
		label_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_12.setText("\u53C2\u52A0\u4EBA\u6570\uFF1A");
		
		textCMCount = new Text(group_2, SWT.BORDER);
		textCMCount.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验参加人数：位数不能超过4位
				String text = textEICount.getText();
				String newStr = Util.verifyInteger(e, text);
				if(newStr.length() > 4){
					e.doit = false;
				}
			}
		});
		textCMCount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(group_2, SWT.NONE);
		new Label(group_2, SWT.NONE);
		new Label(group_2, SWT.NONE);
		new Label(group_2, SWT.NONE);
		new Label(group_2, SWT.NONE);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private Credidt credidt;
	
	private Customer customer;
	private Combo cbNeighbourhood;
	private Combo cbPublic;
	private Combo cbBusinessInsurance;
	private Combo cbEndowmentInsurance;
	private Combo cbCooperativeMedical;
	
	private DebitCnd debit;
	
	public Credidt getCredidt(){
		if(credidt == null){
			credidt = new Credidt();
		}
		if(customer != null){
			credidt.setOwnerID(customer.getRowID());
		}
		
		if(!Util.isEmpty(textLoadFor.getText())){
			credidt.setLoadFor(textLoadFor.getText());
		}else{
			credidt.setLoadFor(null);
		}
		if(!Util.isEmpty(textLoadAmount.getText())){
			credidt.setLoadAmount(Float.valueOf(textLoadAmount.getText()));
		}else{
			credidt.setLoadAmount(null);
		}
		
		//其他重要信息
		credidt.setOldPeople(cbOldPeople.getText());
		credidt.setNeighbourhood(cbNeighbourhood.getText());
		credidt.setPublicGood(cbPublic.getText());
		credidt.setBusinessInsurance(cbBusinessInsurance.getText());
		if(!Util.isEmpty(textBIAmount.getText())){
			credidt.setBiAmount(Float.valueOf(textBIAmount.getText()));
		}else{
			credidt.setBiAmount(null);
		}
		credidt.setEndowmentInsurance(cbEndowmentInsurance.getText());
		if(!Util.isEmpty(textEICount.getText())){
			credidt.setEiCount(Integer.valueOf(textEICount.getText()));
		}else{
			credidt.setEiCount(null);
		}
		credidt.setCooperativeMedical(cbCooperativeMedical.getText());
		if(!Util.isEmpty(textCMCount.getText())){
			credidt.setCmCount(Integer.valueOf(textCMCount.getText()));
		}else{
			credidt.setCmCount(null);
		}
		
		return credidt;
	}
	
	public DebitCnd getDebitCnd(){
		if(this.customer == null)
			return null;
		if(this.debit == null){
			this.debit = new DebitCnd();
			this.debit.setOwnerID(this.customer.getRowID());
		}
		
		if(!Util.isEmpty(textBank.getText())){
			this.debit.setBank(textBank.getText());
		}else{
			debit.setBank(null);
		}
		
		if(!Util.isEmpty(textLoadBalance.getText())){
			debit.setBalance(Float.valueOf(textLoadBalance.getText()));
		}else{
			debit.setBalance(null);
		}
		
		return debit;
	}
	
	private void setDebitCnd(DebitCnd theDebit){
		this.debit = theDebit;
		if(debit == null){
			clearDebit();
			return;
		}
		
		textBank.setText(debit.getBank() == null ? "" : debit.getBank());
		textLoadBalance.setText(debit.getBalance() == null ? "" : Util.toPlainString(debit.getBalance()));
	}
	
	private void setCredidt(Credidt cre){
		this.credidt = cre;
		if(credidt == null){
			clearData();
			return;
		}
		
		textLoadFor.setText(cre.getLoadFor() == null ? "" : cre.getLoadFor());
		textLoadAmount.setText(cre.getLoadAmount() == null ? "" : Util.toPlainString(cre.getLoadAmount()));
		textBIAmount.setText(cre.getBiAmount() == null ? "" : Util.toPlainString(cre.getBiAmount()));
		textEICount.setText(cre.getEiCount() == null ? "" : cre.getEiCount().toString());
		textCMCount.setText(cre.getCmCount() == null ? "" : cre.getCmCount().toString());
		cbOldPeople.setText(cre.getOldPeople());
		cbNeighbourhood.setText(cre.getNeighbourhood());
		cbPublic.setText(cre.getPublicGood());
		cbBusinessInsurance.setText(cre.getBusinessInsurance());
		cbEndowmentInsurance.setText(cre.getEndowmentInsurance());
		cbCooperativeMedical.setText(cre.getCooperativeMedical());
	}
	
	private void clearDebit(){
		textBank.setText("");
		textLoadBalance.setText("");
	}
	
	private void clearData(){
		textLoadFor.setText("");
		textLoadAmount.setText("");
		textBIAmount.setText("");
		textEICount.setText("");
		textCMCount.setText("");
		cbBusinessInsurance.setText("");
		cbCooperativeMedical.setText("");
		cbEndowmentInsurance.setText("");
		cbNeighbourhood.setText("");
		cbOldPeople.setText("");
		cbPublic.setText("");
	}
	
	public void setCustomer(Customer cus){
		this.customer = cus;
		if(this.customer == null){
			clearData();
			clearDebit();
			return;
		}
		
		this.credidt = CredidtService.getInstance().getCredidt(customer.getRowID());
		this.debit = CredidtService.getInstance().getDebitCnd(customer.getRowID());
		setDebitCnd(this.debit);
		setCredidt(this.credidt);
	}
}
