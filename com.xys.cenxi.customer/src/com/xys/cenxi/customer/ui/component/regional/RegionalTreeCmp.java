package com.xys.cenxi.customer.ui.component.regional;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.xys.cenxi.customer.pojo.Regional;
import com.xys.cenxi.customer.ui.component.provider.RegionalTreeContentProvider;

public class RegionalTreeCmp extends Composite {
	private Tree tree;
	private TreeViewer treeViewer;
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			if(element instanceof Regional){
				Regional re = (Regional) element;
				return re.getName();
			}else{
				return super.getText(element);
			}
		}
	}

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RegionalTreeCmp(Composite parent, int style, boolean check) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new TreeColumnLayout());
		
		int treeStyle = SWT.BORDER;
		if(check){
			treeStyle |= SWT.CHECK;
		}
		
		treeViewer = new TreeViewer(composite,  treeStyle);
		tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new RegionalTreeContentProvider());

		treeViewer.setInput(new Object());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * 得到选择的节点
	 * @return
	 */
	public Regional getSelect(){
		TreeItem[] selItems = tree.getSelection();
		if(selItems.length > 0){
			return (Regional) selItems[0].getData();
		}
		return null;
	}
	
	public List<Regional> getCheck(){
		List<Regional> result = new LinkedList<Regional>();
		for(TreeItem ti : tree.getItems()){
			getCheckeds(result, ti);
		}
		
		return result;
	}
	
	private void getCheckeds(List<Regional> result, TreeItem parent){
		//节点勾选，表示下级节点全部勾选，故不需要检查已勾选的下级节点
		if(parent.getChecked()){
			result.add((Regional) parent.getData());
		}else{
			for(TreeItem child : parent.getItems()){
				if(child.getChecked()){
					result.add((Regional) child.getData());
				}else{
					getCheckeds(result, child);
				}
			}
		}
	}
}
