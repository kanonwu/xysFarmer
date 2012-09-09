package com.xys.cenxi.customer.ui.component.house;

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

import com.xys.cenxi.customer.msg.HousingSelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Housing;
import com.xys.cenxi.customer.ui.component.provider.HouseTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

public class HousingTableCmp extends Composite {
	private Table table;
	
	private Customer customer;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HousingTableCmp(Composite parent, int style) {
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
				//建筑地址
				if(element instanceof Housing){
					Housing house = (Housing) element;
					return house.getAddress();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(75, true, true));
		tableColumn.setText("\u5EFA\u7B51\u5730\u5740");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//房产性质
				if(element instanceof Housing){
					Housing house = (Housing) element;
					return house.getProperties();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(77, true, true));
		tableColumn_1.setText("\u623F\u4EA7\u7528\u9014");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//结构类别
				if(element instanceof Housing){
					Housing house = (Housing) element;
					return house.getStructure();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(83, true, true));
		tableColumn_2.setText("\u7ED3\u6784\u7C7B\u522B");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//购建年份
				if(element instanceof Housing){
					Housing house = (Housing) element;
					if(house.getYear() != null){
						return house.getYear().toString();
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
		tableColumn_4.setText("\u8D2D\u5EFA\u5E74\u4EFD");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_5.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//建筑面积
				if(element instanceof Housing){
					Housing house = (Housing) element;
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
		TableColumn tableColumn_5 = tableViewerColumn_5.getColumn();
		tableColumn_5.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_5, new ColumnPixelData(64, true, true));
		tableColumn_5.setText("\u5EFA\u7B51\u9762\u79EF");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_6.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//评估价格
				if(element instanceof Housing){
					Housing house = (Housing) element;
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
		TableColumn tableColumn_6 = tableViewerColumn_6.getColumn();
		tableColumn_6.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_6, new ColumnPixelData(67, true, true));
		tableColumn_6.setText("\u8BC4\u4F30\u4EF7\u683C");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				//备注
				if(element instanceof Housing){
					Housing house = (Housing) element;
					return house.getDesc();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(66, true, true));
		tableColumn_3.setText("\u5907\u6CE8");
		tableViewer.setContentProvider(new HouseTableProvider());

	}

	protected void doSelectTableRow() {
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		HousingSelectMsg msg = new HousingSelectMsg();
		msg.house = (Housing) selTi.getData();
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
	
	public void addRow(Housing house){
		tableViewer.add(house);
		table.setSelection(table.getItemCount() - 1);
	}
	
	public void refresh(Housing fa){
		tableViewer.refresh(fa);
	}
	
	public void checkAll(boolean checked){
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			ti.setChecked(checked);
		}
	}
	
	public List<Housing> getChecked(){
		List<Housing> result = new ArrayList<Housing>();
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			if(ti.getChecked()){
				result.add((Housing) ti.getData());
			}
		}
		return result;
	}
	
	public void remove(List<Housing> fas){
		tableViewer.remove(fas.toArray());
	}

}
