package com.xys.cenxi.customer.ui.component.infomgr;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ColumnPixelData;

import com.xys.cenxi.customer.msg.FamilySelectMsg;
import com.xys.cenxi.customer.pojo.Customer;
import com.xys.cenxi.customer.pojo.Family;
import com.xys.cenxi.customer.ui.component.provider.FamilyTableProvider;
import com.xys.cenxi.customer.util.Util;
import com.xys.cenxi.message.MessageService;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class FamilyTableCmp extends Composite {
	private Table table;
	
	private Customer customer;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FamilyTableCmp(Composite parent, int style) {
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
				doFamilySelect();
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
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getName();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn, new ColumnPixelData(75, true, true));
		tableColumn.setText("\u59D3\u540D");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getRelation();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_1, new ColumnPixelData(77, true, true));
		tableColumn_1.setText("\u4E0E\u6237\u4E3B\u5173\u7CFB");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getIdentify();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_2, new ColumnPixelData(134, true, true));
		tableColumn_2.setText("\u8EAB\u4EFD\u8BC1");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getGender();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_3, new ColumnPixelData(55, true, true));
		tableColumn_3.setText("\u6027\u522B");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					if(fm.getBirthday() != null){
						return Util.DATE_SDF.format(fm.getBirthday());
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
		tableColumn_4.setText("\u51FA\u751F\u65E5\u671F");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_5.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getEducation();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_5 = tableViewerColumn_5.getColumn();
		tableColumn_5.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_5, new ColumnPixelData(64, true, true));
		tableColumn_5.setText("\u6587\u5316\u7A0B\u5EA6");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_6.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				return null;
			}
			public String getText(Object element) {
				if(element instanceof Family){
					Family fm = (Family) element;
					return fm.getMarry();
				}else{
					return element == null ? "" : element.toString();
				}
			}
		});
		TableColumn tableColumn_6 = tableViewerColumn_6.getColumn();
		tableColumn_6.setAlignment(SWT.CENTER);
		tcl_composite_1.setColumnData(tableColumn_6, new ColumnPixelData(67, true, true));
		tableColumn_6.setText("\u5A5A\u59FB\u72B6\u51B5");
		tableViewer.setContentProvider(new FamilyTableProvider());

	}

	protected void doFamilySelect() {
		TableItem ti[] = table.getSelection();
		if(ti.length < 1){
			return;
		}
		TableItem selTi = ti[0];
		FamilySelectMsg msg = new FamilySelectMsg();
		msg.family = (Family) selTi.getData();
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
	
	public void addFamily(Family fa){
		tableViewer.add(fa);
		table.setSelection(table.getItemCount() - 1);
	}
	
	public void refresh(Family fa){
		tableViewer.refresh(fa);
	}
	
	public void checkAll(boolean checked){
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			ti.setChecked(checked);
		}
	}
	
	public List<Family> getChecked(){
		List<Family> result = new ArrayList<Family>();
		TableItem[] items = table.getItems();
		for(TableItem ti : items){
			if(ti.getChecked()){
				result.add((Family) ti.getData());
			}
		}
		return result;
	}
	
	public void remove(List<Family> fas){
		tableViewer.remove(fas.toArray());
	}

}
