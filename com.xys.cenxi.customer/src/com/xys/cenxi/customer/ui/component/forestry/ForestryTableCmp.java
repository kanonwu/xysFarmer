package com.xys.cenxi.customer.ui.component.forestry;

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

import com.xys.cenxi.customer.msg.ForestSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.ForestRights;
import com.xys.cenxi.customer.ui.component.provider.ForestTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class ForestryTableCmp extends Composite {
	private Table table;
	
	private Customer customer;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ForestryTableCmp(Composite parent, int style) {
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
				//林权证号
				if(element instanceof ForestRights){
					ForestRights house = (ForestRights) element;
					return house.getRightsID();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(75, true, true));
		tableColumn.setText("\u6797\u6743\u8BC1\u53F7");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//主要品种
				if(element instanceof ForestRights){
					ForestRights house = (ForestRights) element;
					return house.getVariety();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(77, true, true));
		tableColumn_1.setText("\u4E3B\u8981\u54C1\u79CD");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//面积(亩)
				if(element instanceof ForestRights){
					ForestRights house = (ForestRights) element;
					if(house.getArea() != null){
						return Util.toPlainString(house.getArea());
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
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(55, true, true));
		tableColumn_3.setText("\u9762\u79EF(\u4EA9)");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//评估价格
				if(element instanceof ForestRights){
					ForestRights house = (ForestRights) element;
					if(house.getPrice() != null){
						return Util.toPlainString(house.getPrice());
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
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//备注
				if(element instanceof ForestRights){
					ForestRights house = (ForestRights) element;
					return house.getDesc();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(83, true, true));
		tableColumn_2.setText("\u5907\u6CE8");
		tableViewer.setContentProvider(new ForestTableProvider());

	}

	protected void doSelectTableRow() {
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		ForestSelectMsg msg = new ForestSelectMsg();
		msg.forest = (ForestRights) selTi.getData();
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
	
	public void addRow(ForestRights house){
		tableViewer.add(house);
		table.setSelection(table.getItemCount() - 1);
	}
	
	public void refresh(ForestRights fa){
		tableViewer.refresh(fa);
	}
	
	public void checkAll(boolean checked){
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			ti.setChecked(checked);
		}
	}
	
	public List<ForestRights> getChecked(){
		List<ForestRights> result = new ArrayList<ForestRights>();
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			if(ti.getChecked()){
				result.add((ForestRights) ti.getData());
			}
		}
		return result;
	}
	
	public void remove(List<ForestRights> fas){
		tableViewer.remove(fas.toArray());
	}

}
