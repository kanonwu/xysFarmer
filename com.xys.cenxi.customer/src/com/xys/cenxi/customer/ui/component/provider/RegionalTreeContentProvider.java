package com.xys.cenxi.customer.ui.component.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.xys.cenxi.customer.data.RegionalService;
import com.xys.cenxi.customer.pojo.Regional;

public class RegionalTreeContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		String parentID = null;
		if(parentElement != null){
			if(parentElement instanceof Regional){
				Regional re = (Regional) parentElement;
				parentID = re.getRowID();
			}
		}
		List<Regional> result = null;
		result = RegionalService.getInstance().getRegionalByParentID(parentID);
		return result.toArray();
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof Regional){
			Regional re = (Regional) element;
			return RegionalService.getInstance().getRegional(re.getParentID());
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element == null){
			return true;
		}
		if(element instanceof Regional){
			return RegionalService.getInstance().hasChildren((Regional) element);
		}else{
			return true;
		}
	}

}
