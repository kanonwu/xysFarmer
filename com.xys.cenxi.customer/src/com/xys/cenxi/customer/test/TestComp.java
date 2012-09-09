package com.xys.cenxi.customer.test;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.FillLayout;

public class TestComp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TestComp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Spinner spinner = new Spinner(this, SWT.BORDER);
		
		List list = new List(this, SWT.BORDER);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
