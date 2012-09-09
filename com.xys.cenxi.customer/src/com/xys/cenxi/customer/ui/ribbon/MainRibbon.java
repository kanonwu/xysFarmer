package com.xys.cenxi.customer.ui.ribbon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.hexapixel.widgets.ribbon.RibbonTabFolder;

public class MainRibbon extends Composite {

	private RibbonTabFolder floder;
	
	private CustomerInfoTab customTab;
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MainRibbon(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		createRibbon();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void createRibbon(){
		floder = new RibbonTabFolder(this, SWT.NONE);
		
		customTab = new CustomerInfoTab(floder);
		customTab.createContent();
		
		SettingTab setting = new SettingTab(floder);
		setting.createContent();
	}
	
	
}
