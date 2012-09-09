package com.xys.cenxi.customer.ui.component.infomgr;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xys.cenxi.customer.data.RegionalService;
import com.xys.cenxi.customer.msg.CustomerSelectMsg;
import com.xys.cenxi.customer.msg.RefreshCustomerTableMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Regional;
import com.xys.cenxi.customer.ui.component.infomgr.msg.TableRefreshMsgListener;
import com.xys.cenxi.customer.ui.component.provider.CustomerTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class CustomerTableCmp extends Composite {
	private Table table;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CustomerTableCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				TableItem ti[] = table.getSelection();
				if(ti.length < 1){
					return;
				}
				TableItem selTi = ti[0];
				CustomerSelectMsg msg = new CustomerSelectMsg();
				msg.selectCustomer = (Customer) selTi.getData();
				MessageService.getInstance().postMessage(msg);
				table.setFocus();
			}
		});
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_7.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getArchivesID();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_3 = tableViewerColumn_7.getColumn();
		tableColumn_3.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(96, true, true));
		tableColumn_3.setText("\u6863\u6848\u7F16\u53F7");
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getName();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tblclmnNewColumn, new ColumnPixelData(98, true, true));
		tblclmnNewColumn.setText("\u59D3\u540D");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getIdentify();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tblclmnNewColumn_1, new ColumnPixelData(150, true, true));
		tblclmnNewColumn_1.setText("\u8EAB\u4EFD\u8BC1");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getMarry();
				}else{
					return element == null ? "" : element.toString();
				}

			}
		});
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tblclmnNewColumn_2, new ColumnPixelData(74, true, true));
		tblclmnNewColumn_2.setText("\u5A5A\u59FB");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getEducation();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tblclmnNewColumn_3, new ColumnPixelData(94, true, true));
		tblclmnNewColumn_3.setText("\u6587\u5316");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					if(!Util.isEmpty(cus.getRegional())){
						Regional re = RegionalService.getInstance().getRegionalByCode(cus.getRegional());
						if(re != null){
							return re.getName();
						}else{
							return cus.getRegional();
						}
					}else{
						return "";
					}
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn_4.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(107, true, true));
		tableColumn.setText("\u884C\u653F\u533A\u5212");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_5.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getAddress();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_5.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(150, true, true));
		tableColumn_1.setText("\u5730\u5740");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_6.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Customer){
					Customer cus = (Customer) element;
					return cus.getMobilePhone();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_2 = tableViewerColumn_6.getColumn();
		tableColumn_2.setAlignment(SWT.CENTER);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(118, true, true));
		tableColumn_2.setText("\u8054\u7CFB\u7535\u8BDD");
		tableViewer.setContentProvider(new CustomerTableProvider());

		regListener();
	}

	private TableRefreshMsgListener listener;
	
	private void regListener(){
		listener = new TableRefreshMsgListener(tableViewer);
		MessageService.getInstance().addMessageListener(RefreshCustomerTableMsg.class, listener);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void dispose() {
		super.dispose();
		MessageService.getInstance().removeMessageListener(RefreshCustomerTableMsg.class, listener);
	}
	
	public void checkAll(boolean checked){
		table.setSelection(0, table.getItemCount() - 1);
		for(TableItem ti : table.getItems()){
			ti.setChecked(checked);
		}
	}
	
	public List<Customer> getChecked(){
		List<Customer> checkeds = new LinkedList<Customer>();
		for(TableItem ti : table.getItems()){
			if(ti.getChecked()){
				checkeds.add((Customer) ti.getData());
			}
		}
		
		return checkeds;
	}
	
	public void remove(List<Customer> items){
		tableViewer.remove(items.toArray());
	}
	
	public void addItem(Customer cus){
		tableViewer.add(cus);
		table.setSelection(table.getItemCount() - 1);
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		CustomerSelectMsg msg = new CustomerSelectMsg();
		msg.selectCustomer = (Customer) selTi.getData();
		MessageService.getInstance().postMessage(msg);
	}
	
	public void refresh(Customer cus){
		tableViewer.refresh(cus);
	}
}
