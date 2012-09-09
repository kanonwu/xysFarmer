package com.xys.cenxi.customer.ui.component.income;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.data.IncomeService;
import com.xys.cenxi.customer.msg.IncomeSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.FamilyOutput;
import com.xys.cenxi.customer.pojo.FarmIncome;
import com.xys.cenxi.customer.pojo.OtherIncome;
import com.xys.cenxi.customer.ui.component.infomgr.msg.IncomeSelectListener;
import com.xys.cenxi.customer.util.UIUtil;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class InOutMgrCmp extends Composite {
	private IncomeTableCmp incomeTableCmp;
	private IncomeInfoCmp incomeInfoCmp;
	private Button btnAdd;
	private Button btnModify;
	private Button btnSave;
	
	private IncomeSelectListener fsl;
	
	private Customer customer;
	private Composite composite_4;
	private Button btnCheck;
	private Button btnDelete;
	private Group group;
	private Group group_1;
	private Label label;
	private Text textWorkIncome;
	private Label label_1;
	private Text textOtherIncome;
	private Composite composite_5;
	private Text textAllIncome;
	private Text textProductionOutput;
	private Text textLifeOutput;
	private Text textOtherOutput;
	private Text textAllOutput;
	private Text textNetIncome;
	private Button btnSaveAll;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InOutMgrCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Group group_2 = new Group(this, SWT.NONE);
		group_2.setText("\u5BB6\u5EAD\u6536\u5165");
		group_2.setLayout(new GridLayout(1, false));
		GridData gd_group_2 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_group_2.heightHint = 303;
		group_2.setLayoutData(gd_group_2);
		
		group_1 = new Group(group_2, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		group_1.setText("\u79CD\u517B\u6536\u5165");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(group_1, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_1.heightHint = 118;
		gd_composite_1.widthHint = 257;
		composite_1.setLayoutData(gd_composite_1);
		
		incomeTableCmp = new IncomeTableCmp(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_2.widthHint = 168;
		composite_2.setLayoutData(gd_composite_2);
		
		incomeInfoCmp = new IncomeInfoCmp(composite_2, SWT.NONE);
		incomeInfoCmp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayout(new GridLayout(5, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_3.heightHint = 37;
		gd_composite_3.widthHint = 67;
		composite_3.setLayoutData(gd_composite_3);
		
		btnAdd = new Button(composite_3, SWT.NONE);
		btnAdd.setEnabled(false);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doAdd();
			}
		});
		btnAdd.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/add_obj.gif"));
		btnAdd.setText("\u65B0\u589E");
		new Label(composite_3, SWT.NONE);
		
		btnModify = new Button(composite_3, SWT.NONE);
		btnModify.setEnabled(false);
		btnModify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doModify();
			}
		});
		btnModify.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/ico_edit.gif"));
		btnModify.setText("\u4FEE\u6539");
		new Label(composite_3, SWT.NONE);
		
		btnSave = new Button(composite_3, SWT.NONE);
		btnSave.setEnabled(false);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSave();
			}
		});
		btnSave.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/save.gif"));
		btnSave.setText("\u4FDD\u5B58");
		
		composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayout(new GridLayout(3, false));
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_4.heightHint = 38;
		composite_4.setLayoutData(gd_composite_4);
		
		btnCheck = new Button(composite_4, SWT.NONE);
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCheck();
			}
		});
		btnCheck.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/complete_tsk.gif"));
		btnCheck.setText("\u5168\u9009");
		new Label(composite_4, SWT.NONE);
		
		btnDelete = new Button(composite_4, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doDelete();
			}
		});
		btnDelete.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/delete_edit.gif"));
		btnDelete.setText("\u5220\u9664");
		new Label(composite, SWT.NONE);
		
		group = new Group(group_2, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setLayout(new GridLayout(5, false));
		group.setText("\u5176\u4ED6\u6536\u5165");
		
		label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u5916\u51FA\u52A1\u5DE5\u5E74\u5EA6\u6536\u5165(\u4E07\u5143)\uFF1A");
		
		textWorkIncome = new Text(group, SWT.BORDER);
		textWorkIncome.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验务工收入
				String text = textWorkIncome.getText();
				Util.verifyMoney(e, text);
			}
		});
		GridData gd_textWorkIncome = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textWorkIncome.widthHint = 96;
		textWorkIncome.setLayoutData(gd_textWorkIncome);
		new Label(group, SWT.NONE);
		
		label_1 = new Label(group, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u5176\u4ED6\u5E74\u5EA6\u6536\u5165(\u4E07\u5143)\uFF1A");
		
		textOtherIncome = new Text(group, SWT.BORDER);
		textOtherIncome.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验其他收入
				String oldText = textOtherIncome.getText();
				Util.verifyMoney(e, oldText);
			}
		});
		GridData gd_textOtherIncome = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textOtherIncome.widthHint = 90;
		textOtherIncome.setLayoutData(gd_textOtherIncome);
		
		composite_5 = new Composite(group_2, SWT.NONE);
		composite_5.setLayout(new GridLayout(4, false));
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label label_2 = new Label(composite_5, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setText("\u5BB6\u5EAD\u5E74\u5EA6\u603B\u6536\u5165(\u4E07\u5143)\uFF1A");
		
		textAllIncome = new Text(composite_5, SWT.BORDER);
		textAllIncome.setEditable(false);
		GridData gd_textAllIncome = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textAllIncome.widthHint = 88;
		textAllIncome.setLayoutData(gd_textAllIncome);
		
		Group group_3 = new Group(this, SWT.NONE);
		group_3.setLayout(new GridLayout(6, false));
		GridData gd_group_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_group_3.heightHint = 64;
		group_3.setLayoutData(gd_group_3);
		group_3.setText("\u5BB6\u5EAD\u652F\u51FA");
		
		Label label_3 = new Label(group_3, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u751F\u4EA7\u5E74\u5EA6\u652F\u51FA\u989D(\u4E07\u5143)\uFF1A");
		
		textProductionOutput = new Text(group_3, SWT.BORDER);
		textProductionOutput.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验生产输出
				Util.verifyMoney(e, textProductionOutput.getText());
			}
		});
		textProductionOutput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_4 = new Label(group_3, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u751F\u6D3B\u5E74\u5EA6\u652F\u51FA\u989D(\u4E07\u5143)\uFF1A");
		
		textLifeOutput = new Text(group_3, SWT.BORDER);
		textLifeOutput.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验生活支出
				Util.verifyMoney(e, textLifeOutput.getText());
			}
		});
		textLifeOutput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_5 = new Label(group_3, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u5176\u4ED6\u5E74\u5EA6\u652F\u51FA\u989D(\u4E07\u5143)\uFF1A");
		
		textOtherOutput = new Text(group_3, SWT.BORDER);
		textOtherOutput.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				//校验其他年度支出
				Util.verifyMoney(e, textOtherOutput.getText());
			}
		});
		textOtherOutput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_6 = new Label(group_3, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("\u5BB6\u5EAD\u5E74\u5EA6\u603B\u652F\u51FA(\u4E07\u5143)\uFF1A");
		
		textAllOutput = new Text(group_3, SWT.BORDER);
		textAllOutput.setEditable(false);
		textAllOutput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(group_3, SWT.NONE);
		new Label(group_3, SWT.NONE);
		new Label(group_3, SWT.NONE);
		new Label(group_3, SWT.NONE);
		
		Composite composite_6 = new Composite(this, SWT.NONE);
		composite_6.setLayout(new GridLayout(6, false));
		GridData gd_composite_6 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite_6.heightHint = 44;
		composite_6.setLayoutData(gd_composite_6);
		new Label(composite_6, SWT.NONE);
		
		Label label_7 = new Label(composite_6, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("\u5BB6\u5EAD\u5E74\u5EA6\u7EAF\u6536\u5165(\u4E07\u5143)\uFF1A");
		
		textNetIncome = new Text(composite_6, SWT.BORDER);
		textNetIncome.setEditable(false);
		textNetIncome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		
		btnSaveAll = new Button(composite_6, SWT.NONE);
		btnSaveAll.setEnabled(false);
		GridData gd_btnSaveAll = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSaveAll.widthHint = 101;
		btnSaveAll.setLayoutData(gd_btnSaveAll);
		btnSaveAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSaveAll();
				doSaveFamilyOutput();
				doCalTotalOutput();
			}
		});
		btnSaveAll.setImage(SWTResourceManager.getImage(InOutMgrCmp.class, "/icons/save.gif"));
		btnSaveAll.setText("\u4FDD\u5B58");

		regMsgListener();
	}

	protected void doSaveAll() {
		doSaveOtherIncome();
		doSaveFamilyOutput();
		doCalTotalOutput();
		doCalTotalIncome();
		doCalNetIncome();
		UIUtil.showMessage("保存完成。");
	}
	
	/**
	 * 保存其他收入
	 */
	protected void doSaveOtherIncome() {
		if(this.customer == null)
			return;
		
		this.otherIncome = getOtherIncome();
		if(otherIncome.getRowID() != null){
			//更新
			IncomeService.getInstance().update(otherIncome);
		}else{
			//新增
			IncomeService.getInstance().add(otherIncome);
		}
		
	}

	/**
	 * 保存家庭开支
	 */
	protected void doSaveFamilyOutput() {
		if(customer == null)
			return;
		this.output = getOutput();
		
		if(output.getRowID() == null){
			//新增
			IncomeService.getInstance().add(output);
		}else{
			//更新
			IncomeService.getInstance().update(output);
		}
		
	}
	
	private void doCalTotalOutput(){
		if(this.output == null){
			textAllOutput.setText("");
			return;
		}
		
		BigDecimal allOutput = BigDecimal.ZERO;
		if(output.getProductionOutput() != null){
			allOutput = allOutput.add(BigDecimal.valueOf(output.getProductionOutput()));
		}
		if(output.getLiftOutput() != null){
			allOutput = allOutput.add(BigDecimal.valueOf(output.getLiftOutput()));
		}
		if(output.getOtherOutput() != null){
			allOutput = allOutput.add(BigDecimal.valueOf(output.getOtherOutput()));
		}
		
		textAllOutput.setText(Util.toPlainString(allOutput));
	}

	protected void doCalNetIncome() {
		//计算家庭纯收入：纯收入=收入总额-支出总额
		BigDecimal netIncome = BigDecimal.ZERO;
		if(this.customer != null){
			netIncome = IncomeService.getInstance().getFamilyNetIncome(this.customer.getRowID());
		}
		textNetIncome.setText(Util.toPlainString(netIncome));
	}

	/**
	 * 计算收入总额
	 */
	protected void doCalTotalIncome() {
		if(this.customer == null){
			textAllIncome.setText("");
		}
		
		BigDecimal totalIncome = IncomeService.getInstance().getFamilyTotalIncome(customer.getRowID());
		if(totalIncome != null){
			textAllIncome.setText(Util.toPlainString(totalIncome));
		}else{
			textAllIncome.setText("");
		}
	}

	protected void doDelete() {
		List<FarmIncome> selFa = incomeTableCmp.getChecked();
		if(selFa.isEmpty()){
			UIUtil.showMessage("请选择要删除的收入信息。");
			return;
		}
		
		boolean yes = MessageDialog.openConfirm(getShell(), "提示", "确定要删除这些收入信息？");
		if(!yes)
			return;
		
		//删除数据库
		IncomeService.getInstance().delete(selFa);
		//删除界面数据
		incomeTableCmp.remove(selFa);
		UIUtil.showMessage("删除完成。");
		//计算总额
		doCalNetIncome();
		doCalTotalIncome();
		doCalTotalOutput();
	}
	
	protected void doCheck() {
		// 
		if(btnCheck.getData() == null){
			btnCheck.setData(Boolean.TRUE);
		}
		
		Boolean checked = (Boolean) btnCheck.getData();
		incomeTableCmp.checkAll(checked);
		btnCheck.setData(!checked);
	}

	protected void doSave() {
		// 保存
		//先校验
		String errMsg = incomeInfoCmp.validateData();
		if(errMsg != null){
			UIUtil.showMessage(errMsg);
			return;
		}
		
		FarmIncome house = incomeInfoCmp.getFarmIncome();
		boolean isAdd = false;
		if(house.getRowID() != null){
			//更新
			IncomeService.getInstance().update(house);
		}else{
			//新增
			IncomeService.getInstance().add(house);
			isAdd = true;
		}
		//保存完成
		//保存完成
		UIUtil.showMessage("保存完成。");
		btnSave.setEnabled(false);
		btnModify.setEnabled(true);
		btnAdd.setEnabled(true);
		// 通知表格刷新数据
		if(isAdd){
			incomeTableCmp.addRow(house);
		}else{
			incomeTableCmp.refresh(house);
		}
		
		//计算收入和支出总额
		doCalNetIncome();
		doCalTotalIncome();
		doCalTotalOutput();
	}

	protected void doModify() {
		if(incomeInfoCmp.getModifing()== null){
			//没有可修改的信息对象
			btnModify.setEnabled(false);
			btnAdd.setEnabled(true);
			return;
		}
		//修改按钮动作，新增按钮不可用，保存按钮可用
		btnSave.setEnabled(true);
		btnAdd.setEnabled(false);
		
	}

	protected void doAdd() {
		//新增动作，编辑按钮不可用，保存可用，清空输入框内容
		incomeInfoCmp.setIncome(null);
		btnModify.setEnabled(false);
		btnSave.setEnabled(true);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setIncome(FarmIncome h){
		btnModify.setEnabled(h != null);
		btnSave.setEnabled(false);
		btnAdd.setEnabled(true);
		incomeInfoCmp.setIncome(h);
	}
	
	private OtherIncome otherIncome;
	
	private FamilyOutput output;
	
	private OtherIncome getOtherIncome(){
		if(customer == null)
			return null;
		if(otherIncome == null){
			otherIncome = new OtherIncome();
			otherIncome.setOwnerID(customer.getRowID());
		}
		if(!Util.isEmpty(textWorkIncome.getText())){
			otherIncome.setWorkIncome(Float.valueOf(textWorkIncome.getText()));
		}else{
			otherIncome.setWorkIncome(null);
		}
		if(!Util.isEmpty(textOtherIncome.getText())){
			otherIncome.setOtherIncome(Float.valueOf(textOtherIncome.getText()));
		}else{
			otherIncome.setOtherIncome(null);
		}
		
		return this.otherIncome;
	}
	
	private FamilyOutput getOutput(){
		if(this.customer == null)
			return null;
		
		if(this.output == null){
			output = new FamilyOutput();
			output.setOwnerID(this.customer.getRowID());
		}
		if(!Util.isEmpty(textProductionOutput.getText())){
			output.setProductionOutput(Float.valueOf(textProductionOutput.getText()));
		}else{
			output.setProductionOutput(null);
		}
		if(!Util.isEmpty(textLifeOutput.getText())){
			output.setLiftOutput(Float.valueOf(textLifeOutput.getText()));
		}else{
			output.setLiftOutput(null);
		}
		if(!Util.isEmpty(textOtherOutput.getText())){
			output.setOtherOutput(Float.valueOf(textOtherOutput.getText()));
		}else{
			output.setOtherOutput(null);
		}
		
		return output;
	}
	
	private void setOtherIncome(OtherIncome oi){
		this.otherIncome = oi;
		if(this.otherIncome == null){
			textWorkIncome.setText("");
			textOtherIncome.setText("");
		}else{
			if(this.otherIncome.getWorkIncome() != null){
				textWorkIncome.setText(otherIncome.getWorkIncome().toString());
			}else{
				textWorkIncome.setText("");
			}
			if(this.otherIncome.getOtherIncome() != null){
				textOtherIncome.setText(otherIncome.getOtherIncome().toString());
			}else{
				textOtherIncome.setText("");
			}
		}
	}
	
	private void setFamilyOutput(FamilyOutput out){
		this.output = out;
		if(output == null){
			textProductionOutput.setText("");
			textLifeOutput.setText("");
			textOtherOutput.setText("");
			return;
		}
		
		if(output.getProductionOutput() != null){
			textProductionOutput.setText(output.getProductionOutput().toString());
		}else{
			textProductionOutput.setText("");
		}
		if(output.getLiftOutput() != null){
			textLifeOutput.setText(output.getLiftOutput().toString());
		}else{
			textLifeOutput.setText("");
		}
		if(output.getOtherOutput() != null){
			textOtherOutput.setText(output.getOtherOutput().toString());
		}else{
			textOtherOutput.setText("");
		}
		
		doCalTotalOutput();
	}
	
	public void setCustomer(Customer cus){
		textAllIncome.setText("");
		textAllOutput.setText("");
		textNetIncome.setText("");
		boolean enabled = cus != null;
		btnAdd.setEnabled(enabled);
		btnSaveAll.setEnabled(enabled);
		btnModify.setEnabled(false);
		btnSave.setEnabled(false);
		
		this.customer = cus;
		incomeTableCmp.setCustomer(customer);
		incomeInfoCmp.setIncome(null);
		incomeInfoCmp.setCustomer(cus);
		
		if(cus != null){
			this.otherIncome = IncomeService.getInstance().getOtherIncome(customer.getRowID());
			setOtherIncome(this.otherIncome);
			this.output = IncomeService.getInstance().getFamilyOutput(customer.getRowID());
			setFamilyOutput(this.output);
			doCalNetIncome();
			doCalTotalIncome();
			doCalTotalOutput();
		}else{
			setOtherIncome(null);
			setFamilyOutput(null);
		}
	}
	
	private void regMsgListener(){
		if(fsl == null){
			fsl = new IncomeSelectListener(this);
		}
		MessageService.getInstance().addMessageListener(IncomeSelectMsg.class, fsl);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MessageService.getInstance().removeMessageListener(IncomeSelectMsg.class, fsl);
	}
}
