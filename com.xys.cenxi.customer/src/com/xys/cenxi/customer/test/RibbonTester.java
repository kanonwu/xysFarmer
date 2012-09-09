package com.xys.cenxi.customer.test;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.hexapixel.widgets.generic.ImageCache;
import com.hexapixel.widgets.generic.Utils;
import com.hexapixel.widgets.ribbon.ButtonSelectGroup;
import com.hexapixel.widgets.ribbon.QuickAccessShellToolbar;
import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonButtonGroup;
import com.hexapixel.widgets.ribbon.RibbonCheckbox;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonGroupSeparator;
import com.hexapixel.widgets.ribbon.RibbonShell;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.hexapixel.widgets.ribbon.RibbonToolbar;
import com.hexapixel.widgets.ribbon.RibbonToolbarGrouping;
import com.hexapixel.widgets.ribbon.RibbonTooltip;

public class RibbonTester {
	
	public static void main(String[] args)
	  {
//	    Display display = new Display();
	    Display display = Display.getDefault();
	    final RibbonShell shell = new RibbonShell(display);
	    shell.setButtonImage(ImageCache.getImage("selection_recycle_24.png"));

	    shell.setText("SWT Ribbon Tester");
	    shell.setSize(714, 500);

//	    Text text = new Text(shell.getShell(), 2048);
	    Composite cmp = new Composite(shell.getShell(), 0);
	    cmp.setLayout(new FillLayout());

	    QuickAccessShellToolbar mtb = shell.getToolbar();
	    RibbonButton mtbtb1 = new RibbonButton(mtb, ImageCache.getImage("gear_ok_16.gif"), null, 0);
	    RibbonButton mtbtb2 = new RibbonButton(mtb, ImageCache.getImage("gantt_16.gif"), null, 0);
	    shell.setBigButtonTooltip(new RibbonTooltip("Big", "I'm the tooltip for the big button"));
	    mtb.setArrowTooltip(new RibbonTooltip("Oh", "Jeez"));

	    Menu shellMenu = shell.getBigButtonMenu();
	    MenuItem btest = new MenuItem(shellMenu, 8);
	    btest.setText("Testing a menu");

	    shell.addBigButtonListener(new SelectionListener()
	    {
	      public void widgetDefaultSelected(SelectionEvent e) {
	      }

	      public void widgetSelected(SelectionEvent e) {
	        System.err.println("Clicked big button");
	        shell.showBigButtonMenu();
	      }
	    });
	    RibbonTabFolder ftf = shell.getRibbonTabFolder();
	    ftf.setHelpImage(ImageCache.getImage("questionmark.gif"));
	    ftf.getHelpButton().setToolTip(new RibbonTooltip("Title", "Get Help Using Whatever This Is"));

	    RibbonTab ft0 = new RibbonTab(ftf, "Home");
	    RibbonTab ft1 = new RibbonTab(ftf, "Insert");
	    new RibbonTab(ftf, "Page Layout");
	    new RibbonTab(ftf, "References");
	    new RibbonTab(ftf, "Empty");

	    RibbonTooltip toolTip = new RibbonTooltip("Some Action Title", 
	    		"This is content text that\nsplits over\nmore than one\nline\n\\b\\c255000000and \\xhas \\bdifferent \\c000000200look \\xand \\bfeel.",
	    		ImageCache.getImage("tooltip.jpg"), ImageCache.getImage("questionmark.gif"), "Press F1 for more help");

	    RibbonGroup tbGroup = new RibbonGroup(ft0, "Toolbar Here");
	    RibbonToolbar toolbar = new RibbonToolbar(tbGroup, 0, 2);
	    RibbonToolbarGrouping rtg = new RibbonToolbarGrouping(toolbar, 1);
	    RibbonToolbarGrouping rtg2 = new RibbonToolbarGrouping(toolbar, 1);
	    RibbonToolbarGrouping rtg3 = new RibbonToolbarGrouping(toolbar, 1);

	    RibbonToolbarGrouping rtg4 = new RibbonToolbarGrouping(toolbar, 2);

	    RibbonButton rbTb1 = new RibbonButton(rtg, ImageCache.getImage("books_16.gif"), null, 10);
	    RibbonButton rbTb2 = new RibbonButton(rtg2, ImageCache.getImage("gear_ok_16.gif"), null, 0);
	    RibbonButton rbTb3 = new RibbonButton(rtg2, ImageCache.getImage("gantt_16.gif"), null, 1);
	    RibbonButton rbTb4 = new RibbonButton(rtg3, ImageCache.getImage("gantt_16.gif"), null, 2);

	    RibbonButton rbTb5 = new RibbonButton(rtg4, ImageCache.getImage("enabled_small.gif"), null, 4);
	    RibbonButton rbTb6 = new RibbonButton(rtg4, ImageCache.getImage("selection_recycle_16.gif"), null, 2);

	    rbTb4.setEnabled(false);

	    RibbonGroup ftg = new RibbonGroup(ft0, "Category Name", toolTip);

	    RibbonButton rb = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "I have two\nrows", 17);
	    RibbonButton rb2 = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "I'm split\ntoggle", 26);
	    rb2.setBottomOrRightToolTip(toolTip);
	    MenuItem test = new MenuItem(rb2.getMenu(), 8);
	    test.setText("Testing a menu");

	    rb2.addSelectionListener(new SelectionListener()
	    {
	      public void widgetDefaultSelected(SelectionEvent e) {
	      }

	      public void widgetSelected(SelectionEvent e) {
	        RibbonButton rb = (RibbonButton)e.data;
	        if (rb.isTopSelected()) {
	          System.err.println("Top clicked");
	        } else {
	          System.err.println("Bottom clicked");
	          rb.showMenu();
	        }
	      }
	    });
	    RibbonGroup cb = new RibbonGroup(ft1, "Checkboxes", toolTip);
	    RibbonButtonGroup cbg = new RibbonButtonGroup(cb);

	    RibbonCheckbox rc = new RibbonCheckbox(cbg, "I'm checked", 0);
	    rc.setSelected(true);
	    new RibbonCheckbox(cbg, "I'm not", 0);
	    RibbonCheckbox rc3 = new RibbonCheckbox(cbg, "I'm disabled", 0);
	    new RibbonCheckbox(cbg, "Also a checkbox", 0);
	    new RibbonCheckbox(cbg, "Many, aren't we?", 0);
	    rc3.setEnabled(false);
	    RibbonTooltip cbTip = new RibbonTooltip("Title", "Checkbox description\n\\b\\c255000000Some bold and red \\xand\nSome not!", null, ImageCache.getImage("questionmark.gif"), "Press F1 for more help");
	    rc3.setToolTip(cbTip);

	    rb.setToolTip(toolTip);

	    new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "I am longer and do not depress", 4);

	    RibbonGroup ftg2 = new RibbonGroup(ft1, "Group 1");
	    RibbonButton rb1 = new RibbonButton(ftg2, ImageCache.getImage("olb_picture2.gif"), "Button 1", 0);

	    RibbonGroup ftg3 = new RibbonGroup(ft1, "Group 2");
	    RibbonButton rb3 = new RibbonButton(ftg3, ImageCache.getImage("olb_picture4.gif"), "Button 3", 0);
	    RibbonButton rb4 = new RibbonButton(ftg3, ImageCache.getImage("olb_picture6.gif"), "Button 4", 0);
	    rb4.setToolTip(toolTip);

	    ButtonSelectGroup group = new ButtonSelectGroup();

	    RibbonGroup ftg4 = new RibbonGroup(ft1, "Native");
	    GridLayout gl = new GridLayout(1, false);
	    gl.marginHeight = 7;
	    gl.marginLeft = 0;
	    gl.marginRight = 0;
	    gl.verticalSpacing = 1;
	    gl.horizontalSpacing = 0;
	    gl.marginBottom = 7;
	    ftg4.setLayout(gl);
	    Combo foo = new Combo(ftg4, 8);
	    foo.setText("Testing");
	    foo.add("Testing 2");
	    foo.add("Testing 3");
	    foo.add("Testing 4");
	    Button b = new Button(ftg4, 8);
	    b.setText("Test");

	    new RibbonGroupSeparator(ftg);

	    RibbonButtonGroup sub = new RibbonButtonGroup(ftg);
	    RibbonButton sub1 = new RibbonButton(sub, ImageCache.getImage("enabled_small.gif"), ImageCache.getImage("disabled_small.gif"), "Disabled", 0);
	    sub1.setEnabled(false);
	    new RibbonCheckbox(sub, "I'm mixed in", 0);

	    RibbonButton rb5 = new RibbonButton(sub, ImageCache.getImage("olb_small2.gif"), "I am toggle split", 10);
	    RibbonButton rb6 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I am a quite long button", 0);
	    RibbonButton rb7 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I split normal", 2);
	    RibbonButton rb8 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I am arrowed", 1);

	    MenuItem test2 = new MenuItem(rb8.getMenu(), 8);
	    test2.setText("Testing an arrow down menu");

	    rb8.addSelectionListener(new SelectionListener()
	    {
	      public void widgetDefaultSelected(SelectionEvent e) {
	      }

	      public void widgetSelected(SelectionEvent e) {
	        RibbonButton rb = (RibbonButton)e.data;
	        rb.showMenu();
	      }
	    });
	    rb1.setButtonSelectGroup(group);
	    rb2.setButtonSelectGroup(group);
	    rb3.setButtonSelectGroup(group);
	    rb4.setButtonSelectGroup(group);
	    rb5.setButtonSelectGroup(group);
	    rb6.setButtonSelectGroup(group);

	    Utils.centerDialogOnScreen(shell.getShell());

	    shell.open();

	    while (!shell.isDisposed()) {
	      if (display.readAndDispatch()) continue; display.sleep();
	    }
	    display.dispose();
	  }

}
