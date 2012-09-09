package com.xys.cenxi.customer.start;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexapixel.widgets.ribbon.RibbonShell;
import com.xys.cenxi.customer.db.DataSourceManager;
import com.xys.cenxi.customer.db.TableCreater;
import com.xys.cenxi.customer.ui.ribbon.CustomerInfoTab;
import com.xys.cenxi.customer.ui.ribbon.SettingTab;

public class AppMain{

	private RibbonShell shell;
	private CTabFolder tabFolderMain;
	
	private Logger logger = LoggerFactory.getLogger(AppMain.class);
	
	public AppMain(Display displaly) {
		logger.info("�������");
		shell = new RibbonShell(displaly);
		shell.setText("ũ����Ϣ����");
		Content.setAppMain(this);
	}


	/**
	 * Create the actions.
	 */
	public void createActions() {
		// Create the actions
	}

	public void createContent(){
		shell.getContent().setLayout(new FillLayout());
		tabFolderMain = new CTabFolder(shell.getContent(), SWT.BORDER | SWT.CLOSE);
	}
	
	
	public CTabFolder getTabFolder(){
		return tabFolderMain;
	}


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		//�������ݿ�
		DataSourceManager.getDataSource();
//		����
		TableCreater tc = new TableCreater();
		tc.createTable();
		Display display = Display.getDefault();
		//��֤��¼�û�����
		LoginUI login = new LoginUI(display.getActiveShell());
		login.setTitle("ũ����Ϣ����");
		if(login.open() != Window.OK){
			return;
		}
		
		
		//��������
		AppMain app = new AppMain(display);
		
		app.start(display);
	}
	
	public void start(Display display){
		configureShell();
		createMenu();
		createFolder();
		createContent();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	public void configureShell() {
		//�˵���ťͼƬ
		shell.setButtonImage(SWTResourceManager.getImage(AppMain.class, "/icons/photo.gif"));
	}
	
	public void createFolder(){
		CustomerInfoTab customerTab = new CustomerInfoTab(shell.getRibbonTabFolder());
		customerTab.createContent();
		SettingTab settingTab = new SettingTab(shell.getRibbonTabFolder());
		settingTab.createContent();
	}

	public void createMenu(){
		//TODO:�ݲ���Ҫ�˵���ť
	}
	
	public CTabItem getTabItem(String name){
		CTabItem[] items = tabFolderMain.getItems();
		for(CTabItem it : items){
			if(it.getText().equals(name)){
				return it;
			}
		}
		return null;
	}
}
