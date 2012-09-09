package com.xys.cenxi.customer.ui.component.vehicle;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.data.VehicleService;
import com.xys.cenxi.customer.msg.VehicleSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Vehicle;
import com.xys.cenxi.customer.ui.component.infomgr.msg.VehicleSelectListener;
import com.xys.cenxi.customer.util.UIUtil;
import com.xys.cenxi.message.MessageService;

public class VehicleMgrCmp extends Composite {
	private VehicleTableCmp vehicleTableCmp;
	private VehicleInfoCmp vehicleInfoCmp;
	private Button btnAdd;
	private Button btnModify;
	private Button btnSave;
	
	private VehicleSelectListener fsl;
	
	private Customer customer;
	private Composite composite_4;
	private Button btnCheck;
	private Button btnDelete;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VehicleMgrCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_1.heightHint = 136;
		gd_composite_1.widthHint = 257;
		composite_1.setLayoutData(gd_composite_1);
		
		vehicleTableCmp = new VehicleTableCmp(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_composite_2.widthHint = 168;
		composite_2.setLayoutData(gd_composite_2);
		
		vehicleInfoCmp = new VehicleInfoCmp(composite_2, SWT.NONE);
		vehicleInfoCmp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayout(new GridLayout(5, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_3.heightHint = 34;
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
		btnAdd.setImage(SWTResourceManager.getImage(VehicleMgrCmp.class, "/icons/add_obj.gif"));
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
		btnModify.setImage(SWTResourceManager.getImage(VehicleMgrCmp.class, "/icons/ico_edit.gif"));
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
		btnSave.setImage(SWTResourceManager.getImage(VehicleMgrCmp.class, "/icons/save.gif"));
		btnSave.setText("\u4FDD\u5B58");
		
		composite_4 = new Composite(composite, SWT.NONE);
		composite_4.setLayout(new GridLayout(3, false));
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_4.heightHint = 37;
		composite_4.setLayoutData(gd_composite_4);
		
		btnCheck = new Button(composite_4, SWT.NONE);
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCheck();
			}
		});
		btnCheck.setImage(SWTResourceManager.getImage(VehicleMgrCmp.class, "/icons/complete_tsk.gif"));
		btnCheck.setText("\u5168\u9009");
		new Label(composite_4, SWT.NONE);
		
		btnDelete = new Button(composite_4, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doDelete();
			}
		});
		btnDelete.setImage(SWTResourceManager.getImage(VehicleMgrCmp.class, "/icons/delete_edit.gif"));
		btnDelete.setText("\u5220\u9664");
		new Label(composite, SWT.NONE);

		regMsgListener();
	}

	protected void doDelete() {
		List<Vehicle> selFa = vehicleTableCmp.getChecked();
		if(selFa.isEmpty()){
			UIUtil.showMessage("请选择要删除的车辆信息。");
			return;
		}
		
		boolean yes = MessageDialog.openConfirm(getShell(), "提示", "确定要删除这些车辆信息？");
		if(!yes)
			return;
		
		//删除数据库
		VehicleService.getInstance().delete(selFa);
		//删除界面数据
		vehicleTableCmp.remove(selFa);
		UIUtil.showMessage("删除完成。");
	}

	protected void doCheck() {
		// 
		if(btnCheck.getData() == null){
			btnCheck.setData(Boolean.TRUE);
		}
		
		Boolean checked = (Boolean) btnCheck.getData();
		vehicleTableCmp.checkAll(checked);
		btnCheck.setData(!checked);
	}

	protected void doSave() {
		// 保存
		//先校验
		String errMsg = vehicleInfoCmp.validateData();
		if(errMsg != null){
			UIUtil.showMessage(errMsg);
			return;
		}
		
		Vehicle house = vehicleInfoCmp.getVehicle();
		boolean isAdd = false;
		if(house.getRowID() != null){
			//更新
			VehicleService.getInstance().update(house);
		}else{
			//新增
			VehicleService.getInstance().add(house);
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
			vehicleTableCmp.addRow(house);
		}else{
			vehicleTableCmp.refresh(house);
		}
		
	}

	protected void doModify() {
		if(vehicleInfoCmp.getModifing()== null){
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
		vehicleInfoCmp.setVehicle(null);
		btnModify.setEnabled(false);
		btnSave.setEnabled(true);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setVehicle(Vehicle h){
		if(h != null){
			btnModify.setEnabled(true);
		}
		btnAdd.setEnabled(true);
		btnSave.setEnabled(false);
		vehicleInfoCmp.setVehicle(h);
	}
	
	public void setCustomer(Customer cus){
		btnAdd.setEnabled(true);
		btnModify.setEnabled(false);
		btnSave.setEnabled(false);
		this.customer = cus;
		vehicleTableCmp.setCustomer(customer);
		vehicleInfoCmp.setVehicle(null);
		vehicleInfoCmp.setCustomer(cus);
	}
	
	private void regMsgListener(){
		if(fsl == null){
			fsl = new VehicleSelectListener(this);
		}
		MessageService.getInstance().addMessageListener(VehicleSelectMsg.class, fsl);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		MessageService.getInstance().removeMessageListener(VehicleSelectMsg.class, fsl);
	}
	
}
