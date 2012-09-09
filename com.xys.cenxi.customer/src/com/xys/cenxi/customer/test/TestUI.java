package com.xys.cenxi.customer.test;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.SWT;

public class TestUI extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TestUI(Composite parent, int style) {
		super(parent, style);
		
		Link link = new Link(this, SWT.NONE);
		link.setBounds(10, 10, 53, 17);
		link.setText("<a>New Link</a>");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
