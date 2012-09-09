package com.xys.cenxi.customer.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.hexapixel.widgets.generic.ImageCache;
import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonButtonGroup;
import com.hexapixel.widgets.ribbon.RibbonCheckbox;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonShell;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.hexapixel.widgets.ribbon.RibbonTooltip;

public class TestRibbon {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		// Ribbon ×é¼þ¹Ù·½ÍøÖ· http://hexapixel.com/projects/ribbon
		final RibbonShell shell = new RibbonShell(display);
		shell.setText("SWT Ribbon Demo");

		RibbonTabFolder folder = shell.getRibbonTabFolder();
		RibbonTab tab1 = new RibbonTab(folder, "snakedj.ch - swiss java blog");
		RibbonTab tab2 = new RibbonTab(folder,
				"jugr.ch - Java User Group Graub¨¹nden");
		folder.pack();

		RibbonTooltip toolTip = new RibbonTooltip(
				"RibbonTooltip",
				"This is a RibbonTooltip content text.\n\nhttp://www.snakedj.ch - \\c255000000swiss java blog \\x\nhttp://www.jugr.ch - \\c255000000Java User Group Graub¨¹nden \\x",
				ImageCache.getImage("tooltip.jpg"), ImageCache
						.getImage("questionmark.gif"), "Press F1 for more help");
		RibbonGroup group1 = new RibbonGroup(tab1, "Java", toolTip);
		new RibbonButton(group1, ImageCache.getImage("olb_picture4.gif"),
				"J2SE", RibbonButton.STYLE_ARROW_DOWN);
		new RibbonButton(group1, ImageCache.getImage("olb_picture5.gif"),
				"J2EE", RibbonButton.STYLE_ARROW_DOWN);
		new RibbonButton(group1, ImageCache.getImage("olb_picture6.gif"),
				"J2ME", RibbonButton.STYLE_ARROW_DOWN);

		RibbonGroup group2 = new RibbonGroup(tab1, "Miscellaneous", toolTip);
		RibbonButtonGroup buttonGroup = new RibbonButtonGroup(group2);
		new RibbonCheckbox(buttonGroup, "C++", SWT.NONE);
		new RibbonCheckbox(buttonGroup, "PHP", SWT.NONE);
		new RibbonCheckbox(buttonGroup, "PL / SQL", SWT.NONE);
		new RibbonCheckbox(buttonGroup, "Scala", SWT.NONE);

		Menu bigButtonMenu = shell.getBigButtonMenu();
		MenuItem menuItem1 = new MenuItem(bigButtonMenu, SWT.POP_UP);
		menuItem1.setText("Item1");
		MenuItem menuItem2 = new MenuItem(bigButtonMenu, SWT.POP_UP);
		menuItem2.setText("Item2");

		shell.addBigButtonListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.showBigButtonMenu();

			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
