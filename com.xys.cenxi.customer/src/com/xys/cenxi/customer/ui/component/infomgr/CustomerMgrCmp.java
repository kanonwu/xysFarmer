package com.xys.cenxi.customer.ui.component.infomgr;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.xys.cenxi.customer.data.CustomerService;
import com.xys.cenxi.customer.data.query.CustomerQueryKey;
import com.xys.cenxi.customer.data.query.PagerInfo;
import com.xys.cenxi.customer.msg.CustomerSelectMsg;
import com.xys.cenxi.customer.msg.RefreshCustomerTableMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.ui.component.credidt.CredidiMgrCmp;
import com.xys.cenxi.customer.ui.component.income.InOutMgrCmp;
import com.xys.cenxi.customer.ui.component.infomgr.msg.CustomerSelectListener;
import com.xys.cenxi.customer.ui.pagination.PageChangedEvent;
import com.xys.cenxi.customer.ui.pagination.PageChangedListener;
import com.xys.cenxi.customer.ui.pagination.PagerBar;
import com.xys.cenxi.customer.util.UIUtil;
import com.xys.cenxi.ext.excel.CustomToExcel;
import com.xys.cenxi.message.MessageService;

public class CustomerMgrCmp extends Composite {
	private Button button;
	private SearchCndCmp searchCndCmp;
	private CustomerCmp customCmp;
	private Button btnMidify;
	private Button btnSave;
	private Button btnNewButton;
	private Group groupPager;
	private PagerBar pager;
	private CustomerQueryKey queryKey;

	public static String NAME = "农户资料信息";
	private Button btnCheckAll;
	private Button btnDelete;
	private CustomerTableCmp customTableCmp;
	
	private CustomerSelectListener csl;
	private CTabFolder tabFolder;
	private Composite composite_5;
	private FamilyMgrCmp familyMgrCmp;
	private CTabItem tabItem_2;
	private ScrolledComposite scrolledComposite;
	private Composite composite_6;
	private CTabItem tabItem;
	private HouseForestMgrCmp houseForestMgrCmp;
	private CTabItem tabItem_3;
	private VehicleMachineMgrCmp vehicleMachineMgrCmp;
	private CTabItem tabItem_4;
	private ScrolledComposite scrolledComposite_1;
	private InOutMgrCmp inOutMgrCmp;
	private CTabItem tabItem_5;
	private ScrolledComposite scrolledComposite_2;
	private Composite composite_7;
	private CredidiMgrCmp credidiMgrCmp;
	private CTabItem tabItem_6;
	private ScrolledComposite scrolledComposite_3;
	private Composite composite_8;
	private Button button_1;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CustomerMgrCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.BORDER | SWT.SMOOTH);
		sashForm.setSashWidth(1);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.SHADOW_NONE);
		group.setText("\u67E5\u8BE2\u6761\u4EF6");
		group.setLayout(new GridLayout(1, false));
		
		searchCndCmp = new SearchCndCmp(group, SWT.NONE);
		GridData gd_searchCndCmp = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_searchCndCmp.heightHint = 124;
		searchCndCmp.setLayoutData(gd_searchCndCmp);

		button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PagerInfo info = new PagerInfo();
				info.setPageNumber(1);
				info.setPageSize(PagerBar.PAGE_SIZE);
				query(info);
				refreshPager(info);
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 86;
		button.setLayoutData(gd_button);
		button.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/photo.gif"));
		button.setText("\u67E5\u8BE2");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.BORDER | SWT.SMOOTH | SWT.VERTICAL);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.verticalSpacing = 0;
		gl_composite_3.horizontalSpacing = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		
		customTableCmp = new CustomerTableCmp(composite_3, SWT.NONE);
		customTableCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		groupPager = new Group(composite_3, SWT.NONE);
		groupPager.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_groupPager = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_groupPager.heightHint = 30;
		groupPager.setLayoutData(gd_groupPager);
		
		tabFolder = new CTabFolder(sashForm_1, SWT.BORDER);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
				
				tabItem_2 = new CTabItem(tabFolder, SWT.NONE);
				tabItem_2.setText("\u6237\u4E3B\u4FE1\u606F");
				
				scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				tabItem_2.setControl(scrolledComposite);
				scrolledComposite.setExpandHorizontal(true);
				scrolledComposite.setExpandVertical(true);
				
				composite_6 = new Composite(scrolledComposite, SWT.NONE);
				composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
				
				composite_5 = new Composite(composite_6, SWT.NONE);
				composite_5.setLayout(new GridLayout(1, false));
				
				customCmp = new CustomerCmp(composite_5, SWT.NONE);
				customCmp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				customCmp.setSize(598, 189);
								Composite composite_4 = new Composite(composite_5, SWT.NONE);
								composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
								composite_4.setSize(598, 194);
								composite_4.setLayout(new GridLayout(6, false));
								new Label(composite_4, SWT.NONE);
								
								btnNewButton = new Button(composite_4, SWT.NONE);
								btnNewButton.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										doAdd();
									}
								});
								GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
								gd_btnNewButton.widthHint = 90;
								btnNewButton.setLayoutData(gd_btnNewButton);
								btnNewButton.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/add_obj.gif"));
								btnNewButton.setText("\u65B0\u589E");
								new Label(composite_4, SWT.NONE);
								
								btnMidify = new Button(composite_4, SWT.NONE);
								btnMidify.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										doModify();
									}
								});
								GridData gd_btnMidify = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
								gd_btnMidify.widthHint = 90;
								btnMidify.setLayoutData(gd_btnMidify);
								btnMidify.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/ico_edit.gif"));
								btnMidify.setText("\u4FEE\u6539");
								new Label(composite_4, SWT.NONE);
								
								btnSave = new Button(composite_4, SWT.NONE);
								btnSave.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										doSave();
									}
								});
								btnSave.setEnabled(false);
								GridData gd_btnSave = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
								gd_btnSave.widthHint = 90;
								btnSave.setLayoutData(gd_btnSave);
								btnSave.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/save.gif"));
								btnSave.setText("\u4FDD\u5B58");
				scrolledComposite.setContent(composite_6);
				scrolledComposite.setMinSize(composite_6.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				tabItem_6 = new CTabItem(tabFolder, SWT.NONE);
				tabItem_6.setText("\u5BB6\u5EAD\u6210\u5458");
				
				scrolledComposite_3 = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				tabItem_6.setControl(scrolledComposite_3);
				scrolledComposite_3.setExpandHorizontal(true);
				scrolledComposite_3.setExpandVertical(true);
				
				composite_8 = new Composite(scrolledComposite_3, SWT.NONE);
				composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
				
				familyMgrCmp = new FamilyMgrCmp(composite_8, SWT.NONE);
				scrolledComposite_3.setContent(composite_8);
				scrolledComposite_3.setMinSize(composite_8.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setText("\u623F\u4EA7\u6797\u6743\u60C5\u51B5");
				
				houseForestMgrCmp = new HouseForestMgrCmp(tabFolder, SWT.NONE);
				tabItem.setControl(houseForestMgrCmp);
				
				tabItem_3 = new CTabItem(tabFolder, SWT.NONE);
				tabItem_3.setText("\u8F66\u8F86\u519C\u4E1A\u673A\u68B0\u60C5\u51B5");
				
				vehicleMachineMgrCmp = new VehicleMachineMgrCmp(tabFolder, SWT.NONE);
				tabItem_3.setControl(vehicleMachineMgrCmp);
				
				tabItem_4 = new CTabItem(tabFolder, SWT.NONE);
				tabItem_4.setText("\u5BB6\u5EAD\u6536\u652F\u60C5\u51B5");
				
				scrolledComposite_1 = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				tabItem_4.setControl(scrolledComposite_1);
				scrolledComposite_1.setExpandHorizontal(true);
				scrolledComposite_1.setExpandVertical(true);
				
				inOutMgrCmp = new InOutMgrCmp(scrolledComposite_1, SWT.NONE);
				scrolledComposite_1.setContent(inOutMgrCmp);
				scrolledComposite_1.setMinSize(inOutMgrCmp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				tabItem_5 = new CTabItem(tabFolder, SWT.NONE);
				tabItem_5.setText("\u501F\u8D37\u53CA\u5176\u4ED6\u91CD\u8981\u4FE1\u606F");
				
				scrolledComposite_2 = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				tabItem_5.setControl(scrolledComposite_2);
				scrolledComposite_2.setExpandHorizontal(true);
				scrolledComposite_2.setExpandVertical(true);
				
				composite_7 = new Composite(scrolledComposite_2, SWT.NONE);
				composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
				
				credidiMgrCmp = new CredidiMgrCmp(composite_7, SWT.NONE);
				scrolledComposite_2.setContent(composite_7);
				scrolledComposite_2.setMinSize(composite_7.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				sashForm_1.setWeights(new int[] {291, 296});
		sashForm.setWeights(new int[] {1, 3});

		createPager();
	}
	
	private void createPager(){
		
		Composite composite = new Composite(groupPager, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		btnCheckAll = new Button(composite, SWT.NONE);
		btnCheckAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doCheckAll();
			}
		});
		btnCheckAll.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/complete_tsk.gif"));
		btnCheckAll.setText("\u5168\u9009");
		
		btnDelete = new Button(composite, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doDelete();
			}
		});
		btnDelete.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/delete_edit.gif"));
		btnDelete.setText("\u5220\u9664");
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doExport();
			}
		});
		button_1.setImage(SWTResourceManager.getImage(CustomerMgrCmp.class, "/icons/export.gif"));
		button_1.setText("\u5BFC\u51FA");
		pager = new PagerBar(groupPager, PagerBar.GOOGLE_STYLE, 0, PagerBar.PAGE_SIZE, 1);
		pager.addPageChangedListener(new PageChangedListener() {
			
			@Override
			public void pageChanged(PageChangedEvent e) {
				// TODO Auto-generated method stub
				if(queryKey == null || queryKey.pagerInfo == null){
					return;
				}
				
				queryKey.pagerInfo.setPageNumber(e.getPager().getPageIndex());
				query(queryKey.pagerInfo);
			}
		});
		
		tabFolder.setSelection(0);
		regMessageListener();
	}
	
	protected void doExport() {
		// TODO Auto-generated method stub
		List<Customer> checkedCus = customTableCmp.getChecked();
		if(checkedCus.isEmpty())
			return;
		
		Customer theCus = checkedCus.get(0);
		FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
		fd.setText("保存文件");
		fd.setFileName(theCus.getName()+ ".xls");
		String filePath = fd.open();
		if(filePath == null || filePath.length() < 1){
			return;
		}
		CustomToExcel exporter = new CustomToExcel(filePath);
		exporter.writeExcel(theCus);
	}

	protected void doDelete() {
		//删除选择的农户
		List<Customer> checkeds = customTableCmp.getChecked();
		if(checkeds.isEmpty()){
			UIUtil.showMessage("请选择要删除的信息。");
			return;
		}
		
		boolean yes = MessageDialog.openConfirm(getShell(), "提示", "确定要删除这些信息？");
		if(!yes)
			return;
		
		//删除数据库数据
		CustomerService.getService().deleteCustomer(checkeds);
		//删除界面数据
		customTableCmp.remove(checkeds);
		UIUtil.showMessage("删除完成。");
	}

	protected void doCheckAll() {
		if(btnCheckAll.getData() == null){
			btnCheckAll.setData(Boolean.TRUE);
		}
		Boolean check = (Boolean) btnCheckAll.getData();
		customTableCmp.checkAll(check);
		btnCheckAll.setData(!check);
	}

	protected void doSave() {
		//保存按钮动作
		//先校验
		String errMsg = customCmp.validateData();
		if(errMsg != null){
			UIUtil.showMessage(errMsg);
			return;
		}
		
		Customer cus = customCmp.getCustomer();
		boolean isAdd = false;
		if(cus.getRowID() != null){
			//更新
			CustomerService.getService().updateCustomer(cus);
		}else{
			Customer newCus = CustomerService.getService().addCustomer(cus);
			cus.setRowID(newCus.getRowID());
			isAdd = true;
		}
		//保存完成
		UIUtil.showMessage("保存完成。");
		btnSave.setEnabled(false);
		btnMidify.setEnabled(true);
		btnNewButton.setEnabled(true);
		//TODO: 通知表格刷新数据
		if(isAdd){
			customTableCmp.addItem(cus);
		}else{
			customTableCmp.refresh(cus);
		}
	}

	protected void doModify() {
		if(customCmp.getModifingCustomer() == null){
			//没有可修改的信息对象
			btnMidify.setEnabled(false);
			btnNewButton.setEnabled(true);
			return;
		}
		// 修改按钮动作，新增按钮不可用，保存按钮可用
		btnSave.setEnabled(true);
		btnNewButton.setEnabled(false);
	}

	protected void doAdd() {
		//增加按钮动作，输入框内容清空，修改按钮不可用，保存按钮可用
		customCmp.setCustomer(null);
		btnMidify.setEnabled(false);
		btnSave.setEnabled(true);
	}

	private void query(PagerInfo pi){
		queryKey = searchCndCmp.getQueryKey();
		RefreshCustomerTableMsg msg = new RefreshCustomerTableMsg();
		msg.key = queryKey;
		msg.key.pagerInfo = pi;
		MessageService.getInstance().postMessage(msg);
	}
	
	private void refreshPager(PagerInfo info){
		pager.reset(info.getRecordCount(), info.getPageSize(), info.getPageNumber());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setCustomer(Customer cus){
		btnMidify.setEnabled(true);
		btnSave.setEnabled(false);
		btnNewButton.setEnabled(true);
		customCmp.setCustomer(cus);
		familyMgrCmp.setCustomer(cus);
		houseForestMgrCmp.getHouseMgrCmp().setCustomer(cus);
		houseForestMgrCmp.getForestryMgrCmp().setCustomer(cus);
		vehicleMachineMgrCmp.getVehicleMgrCmp().setCustomer(cus);
		vehicleMachineMgrCmp.getMachineMgrCmp().setCustomer(cus);
		inOutMgrCmp.setCustomer(cus);
		credidiMgrCmp.setCustomer(cus);
	}
	
	private void regMessageListener(){
		csl = new CustomerSelectListener(this);
		MessageService.getInstance().addMessageListener(CustomerSelectMsg.class, csl);
	}
	
	@Override
	public void dispose() {
		MessageService.getInstance().removeMessageListener(CustomerSelectMsg.class, csl);
		super.dispose();
	}
}
