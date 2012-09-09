package com.xys.cenxi.customer.ui.component.vehicle;

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

import com.xys.cenxi.customer.msg.VehicleSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Vehicle;
import com.xys.cenxi.customer.ui.component.provider.VehicleTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class VehicleTableCmp extends Composite {
	private Table table;
	
	private Customer customer;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VehicleTableCmp(Composite parent, int style) {
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
				//车牌号码
				if(element instanceof Vehicle){
					Vehicle ve = (Vehicle) element;
					return ve.getLicense();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(75, true, true));
		tableColumn.setText("\u8F66\u724C\u53F7\u7801");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//车辆类型
				if(element instanceof Vehicle){
					Vehicle ve = (Vehicle) element;
					if(!Util.isEmpty(ve.getType())){
						return ve.getType();
					}else{
						return "";
					}
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(65, true, true));
		tableColumn_3.setText("\u8F66\u8F86\u7C7B\u578B");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//排量吨位
				if(element instanceof Vehicle){
					Vehicle ve = (Vehicle) element;
					return ve.getDisplacement();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(77, true, true));
		tableColumn_1.setText("\u6392\u91CF\u5428\u4F4D");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//种植年份
				if(element instanceof Vehicle){
					Vehicle ve = (Vehicle) element;
					if(ve.getBuyYear() != null){
						return ve.getBuyYear().toString();
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
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(83, true, true));
		tableColumn_2.setText("\u8D2D\u7F6E\u5E74\u4EFD");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//评估价格
				if(element instanceof Vehicle){
					Vehicle ve = (Vehicle) element;
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
		TableColumn tableColumn_4 = tableViewerColumn_4.getColumn();
		tableColumn_4.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_4, new ColumnPixelData(68, true, true));
		tableColumn_4.setText("\u8BC4\u4F30\u4EF7\u683C");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_5.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Vehicle){
					return ((Vehicle) element).getDesc();
				}
				return element == null ? "" : element.toString();
			}
		});
		TableColumn tableColumn_5 = tableViewerColumn_5.getColumn();
		tableColumn_5.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_5, new ColumnPixelData(84, true, true));
		tableColumn_5.setText("\u5907\u6CE8");
		tableViewer.setContentProvider(new VehicleTableProvider());

	}

	protected void doSelectTableRow() {
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		VehicleSelectMsg msg = new VehicleSelectMsg();
		msg.vehicle = (Vehicle) selTi.getData();
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
	
	public void addRow(Vehicle ve){
		tableViewer.add(ve);
		table.setSelection(table.getItemCount() - 1);
	}
	
	public void refresh(Vehicle fa){
		tableViewer.refresh(fa);
	}
	
	public void checkAll(boolean checked){
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			ti.setChecked(checked);
		}
	}
	
	public List<Vehicle> getChecked(){
		List<Vehicle> result = new ArrayList<Vehicle>();
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			if(ti.getChecked()){
				result.add((Vehicle) ti.getData());
			}
		}
		return result;
	}
	
	public void remove(List<Vehicle> fas){
		tableViewer.remove(fas.toArray());
	}

}
