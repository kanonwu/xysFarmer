package com.xys.cenxi.customer.ui.component.machine;

import java.util.ArrayList;
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

import com.xys.cenxi.customer.msg.MachineSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.FarmMachine;
import com.xys.cenxi.customer.ui.component.provider.MachineTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class MachineTableCmp extends Composite {
	private Table table;
	
	private Customer customer;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MachineTableCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		TableColumnLayout tcl_composite_1 = new TableColumnLayout();
		composite_1.setLayout(tcl_composite_1);
		
		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				doSelectTableRow();
			}
		});
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//机械名称
				if(element instanceof FarmMachine){
					FarmMachine ve = (FarmMachine) element;
					return ve.getName();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(123, true, true));
		tableColumn.setText("\u673A\u68B0\u540D\u79F0");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//购置年份
				if(element instanceof FarmMachine){
					FarmMachine ve = (FarmMachine) element;
					if(ve.getBuyYear() != null){
						return ve.getBuyYear().toString();
					}
					return "";
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(77, true, true));
		tableColumn_1.setText("\u8D2D\u7F6E\u5E74\u4EFD");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//评估价格
				if(element instanceof FarmMachine){
					FarmMachine ve = (FarmMachine) element;
					if(ve.getPrice() != null){
						return Util.toPlainString(ve.getPrice());
					}else{
						return "";
					}
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(101, true, true));
		tableColumn_2.setText("\u8BC4\u4F30\u4EF7\u683C");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			//备注
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof FarmMachine){
					FarmMachine fm = (FarmMachine) element;
					return fm.getDesc();
				}
				return element == null ? "" : element.toString();
			}
		});
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(82, true, true));
		tableColumn_3.setText("\u5907\u6CE8");
		tableViewer.setContentProvider(new MachineTableProvider());

	}

	protected void doSelectTableRow() {
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		MachineSelectMsg msg = new MachineSelectMsg();
		msg.machine = (FarmMachine) selTi.getData();
		MessageService.getInstance().postMessage(msg);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setCustomer(Customer cus){
		this.customer = cus;
		tableViewer.setInput(customer);
	}
	
	public void addRow(FarmMachine ve){
		tableViewer.add(ve);
		table.setSelection(table.getItemCount() - 1);
	}
	
	public void refresh(FarmMachine fa){
		tableViewer.refresh(fa);
	}
	
	public void checkAll(boolean checked){
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			ti.setChecked(checked);
		}
	}
	
	public List<FarmMachine> getChecked(){
		List<FarmMachine> result = new ArrayList<FarmMachine>();
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			if(ti.getChecked()){
				result.add((FarmMachine) ti.getData());
			}
		}
		return result;
	}
	
	public void remove(List<FarmMachine> fas){
		tableViewer.remove(fas.toArray());
	}

}
