package com.xys.cenxi.customer.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cueeditor.ribbon.RButton;
import cueeditor.ribbon.RCheckBox;
import cueeditor.ribbon.RCombo;
import cueeditor.ribbon.RExpander;
import cueeditor.ribbon.RGroup;
import cueeditor.ribbon.RLabel;
import cueeditor.ribbon.RMenu;
import cueeditor.ribbon.RMenuItem;
import cueeditor.ribbon.RMerger;
import cueeditor.ribbon.RRadioBox;
import cueeditor.ribbon.RSeparator;
import cueeditor.ribbon.RSpinner;
import cueeditor.ribbon.RTabFolder;
import cueeditor.ribbon.RTabHelp;
import cueeditor.ribbon.RTabItem;
import cueeditor.ribbon.RTabMain;
import cueeditor.ribbon.RText;
import cueeditor.ribbon.RToolTip;
import cueeditor.ribbon.RWindow;

public class RwtDemo extends RWindow {

	public RwtDemo() {
		super(null);
	}
	
	public void configureShell(Shell shell) {
		shell.setText("Ribbon Window");
		shell.setSize(500, 350);
	}

	public void createFolder(final RTabFolder folder) {
		folder.setInternalFont(new Font(Display.getCurrent(), "Microsoft YaHei", 9, SWT.NONE));
		List<String> list = new ArrayList<String>();
		list.add("C:\\Program Files\\Internet Explorer\\iexplore.exe");
		list.add("D:\\Program Files\\PHP\\license.txt");
		list.add("E:\\TEMP\\download.zip");
		list.add("F:\\FALCOM NEO CLASSIC From Studios In London City Disc1 London Symphony Orchestra Version.wv");
		list.add("G:\\Fragment Reactions\\CDImage.cue");
		RTabMain main = new RTabMain(folder, "File");
		RMenu fileMenu = new RMenu(main);
		fileMenu.setRecentText("Recent Files");
		fileMenu.setRecentList(list);
		fileMenu.setRecentListener(new Listener() {
			public void handleEvent(Event event) {
				System.out.println(event.index + " | " + event.text);
			}
		});
		RMenuItem new_ = new RMenuItem(fileMenu, SWT.PUSH);
		new_.setText("&New");
		new_.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "/icons/pic1.png").createImage());
		RMenuItem open = new RMenuItem(fileMenu, SWT.PUSH);
		open.setText("&Open");
		open.setAccelerator('O');
		open.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "/icons/pic2.png").createImage());
		open.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("executed open!");
			}
		});
		new RExpander(folder);
		new RTabHelp(folder);
		
		RTabItem item0 = new RTabItem(folder, "Widget");
		RGroup g0 = new RGroup(item0, "Buttons");
		RButton b0 = new RButton(g0, SWT.ARROW);
		b0.setText("Large One");
		b0.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "/icons/jpic3.png").createImage());
		b0.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println(event.detail == SWT.ARROW ? "clicked arrow part!" : "clicked main part!");
			}
		});
		new RSeparator(g0);
		RMerger m0 = new RMerger(g0, 1);
		RButton b1 = new RButton(m0, SWT.PUSH);
		b1.setText("Small One");
		b1.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "/icons/pic4.png").createImage());
		RButton b2 = new RButton(m0, SWT.ARROW);
		b2.setText("Arrow Style");
		RButton b3 = new RButton(m0, SWT.TOGGLE | SWT.PUSH);
		b3.setText("Toggle Style");
		b3.setSelection(true);
		RGroup g1 = new RGroup(item0, "Inputs");
		RMerger m1 = new RMerger(g1, 1);
		RText text = new RText(m1, SWT.NONE);
		text.setText("Text");
		RCombo combo = new RCombo(m1, SWT.NONE);
		combo.setText("Combo");
		combo.setItems(new String[] {"item0", "item1", "item2", "item4"});
		RSpinner spinner = new RSpinner(m1, SWT.NONE);
		spinner.setValues(99, 0, 10000, 2, 1, 10);
		RGroup g2 = new RGroup(item0, "Others");
		RMerger m2 = new RMerger(g2, 1);
		RLabel label = new RLabel(m2);
		label.setText("Label with Image");
		label.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "/icons/pic5.png").createImage());
		RRadioBox radio = new RRadioBox(m2);
		radio.setText("RadioBox");
		RCheckBox check = new RCheckBox(m2);
		check.setText("CheckBox");
		
		final RMenu testMenu = new RMenu();
		testMenu.setText("Simple Menu", 0);
		RMenuItem radioItem = new RMenuItem(testMenu, SWT.RADIO);
		radioItem.setText("SWT.RADIO");
		radioItem.setSelection(true);
		RMenuItem checkItem = new RMenuItem(testMenu, SWT.CHECK);
		checkItem.setText("SWT.CHECK");
		checkItem.setSelection(true);
		new RMenuItem(testMenu, SWT.SEPARATOR);
		RMenuItem dropItem = new RMenuItem(testMenu, SWT.PUSH);
		dropItem.setText("Sub Menu");
		RMenu subMenu = new RMenu();
		RMenuItem subItem = new RMenuItem(subMenu, SWT.PUSH);
		subItem.setText("SWT.PUSH");
		subItem.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "pic4.png").createImage());
		dropItem.setMenu(subMenu);
		RTabItem item1 = new RTabItem(folder, "Menu");
		RGroup g3 = new RGroup(item1, null);
		RButton b4 = new RButton(g3, SWT.PUSH | SWT.ARROW);
		b4.setText("Custom\nLocation");
		b4.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "pic3.png").createImage());
		b4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				testMenu.setLocation(getShell().toDisplay(225, 125));
				testMenu.setVisible(true);
			}
		});
		RButton b5 = new RButton(g3, SWT.PUSH | SWT.ARROW);
		b5.setText("Pop-Up\nMenu");
		b5.setImage(ImageDescriptor.createFromFile(RwtDemo.class, "pic3.png").createImage());
		b5.setMenu(testMenu);
		b5.setToolTip(new RToolTip("ToolTip", "please right click me"));
	}

	public void createContents(Composite contents) {
		contents.setLayout(new FillLayout());
		new Text(contents,SWT.MULTI).setText("Contents here");
	}

	public static void main(final String[] args) {
		new RwtDemo().open();
	}

}
