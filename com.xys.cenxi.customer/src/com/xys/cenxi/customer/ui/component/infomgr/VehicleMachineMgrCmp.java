package com.xys.cenxi.customer.ui.component.infomgr;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.xys.cenxi.customer.ui.component.machine.MachineMgrCmp;
import com.xys.cenxi.customer.ui.component.vehicle.VehicleMgrCmp;

public class VehicleMachineMgrCmp extends Composite {
	private VehicleMgrCmp vehicleMgrCmp;
	private MachineMgrCmp machineMgrCmp;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VehicleMachineMgrCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("\u8F66\u8F86\u60C5\u51B5");
		
		vehicleMgrCmp = new VehicleMgrCmp(group, SWT.NONE);
		
		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_1.setText("\u519C\u4E1A\u673A\u68B0\u60C5\u51B5");
		
		machineMgrCmp = new MachineMgrCmp(group_1, SWT.NONE);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public VehicleMgrCmp getVehicleMgrCmp() {
		return vehicleMgrCmp;
	}
	public MachineMgrCmp getMachineMgrCmp() {
		return machineMgrCmp;
	}
}
