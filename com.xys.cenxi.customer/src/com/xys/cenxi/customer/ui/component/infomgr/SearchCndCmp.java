package com.xys.cenxi.customer.ui.component.infomgr;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xys.cenxi.customer.data.EducationService;
import com.xys.cenxi.customer.data.GenderService;
import com.xys.cenxi.customer.data.MarryService;
import com.xys.cenxi.customer.data.query.CustomerQueryKey;
import com.xys.cenxi.customer.pojo.Regional;
import com.xys.cenxi.customer.ui.component.regional.RegionalDialog;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class SearchCndCmp extends Composite {
	private Text textName;
	private Text textIdentify;
	private Text textRegional;
	private Text textAddress;
	private Combo comboGender;
	private Combo comboMarry;
	private Combo comboEducation;
	private Button btnRegional;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchCndCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(5, false));
		
		Label label = new Label(this, SWT.NONE);
		label.setText("\u59D3\u540D\uFF1A");
		
		textName = new Text(this, SWT.BORDER);
		GridData gd_textName = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_textName.widthHint = 61;
		textName.setLayoutData(gd_textName);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u8EAB\u4EFD\u8BC1\uFF1A");
		
		textIdentify = new Text(this, SWT.BORDER);
		textIdentify.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("\u6027\u522B\uFF1A");
		
		comboGender = new Combo(this, SWT.READ_ONLY);
		comboGender.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u5A5A\u59FB\uFF1A");
		
		comboMarry = new Combo(this, SWT.READ_ONLY);
		comboMarry.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u6587\u5316\u7A0B\u5EA6\uFF1A");
		
		comboEducation = new Combo(this, SWT.READ_ONLY);
		comboEducation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u884C\u653F\u533A\u5212\uFF1A");
		
		textRegional = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		textRegional.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.DEL || e.keyCode == SWT.BS){
					//¼àÌýÉ¾³ýºÍ»ØÍË°´¼ü
					doClearRegional();
					e.doit = false;
				}
			}
		});
		textRegional.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		btnRegional = new Button(this, SWT.NONE);
		btnRegional.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSelectRegional();
			}
		});
		btnRegional.setText("...");
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u5730\u5740\uFF1A");
		
		textAddress = new Text(this, SWT.BORDER);
		textAddress.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));

		initInput();
	}

	protected void doClearRegional() {
		textRegional.setText("");
		textRegional.setData(null);
	}

	protected void doSelectRegional() {
		RegionalDialog rd = new RegionalDialog(getShell());
		rd.setCheck(false);
		rd.open();
		Object result = rd.getResult();
		if(result instanceof Regional){
			Regional re = (Regional) result;
			textRegional.setText(re.getName());
			textRegional.setData(re);
		}
	}

	private void initInput(){
		String[] genders = GenderService.getInstant().getGenders();
		comboGender.add("");
		for(String it : genders){
			comboGender.add(it);
		}
		if(genders.length > 0){
			comboGender.select(0);
		}
		
		String[] marry = MarryService.getInstant().getMarrys();
		comboMarry.add("");
		for(String it : marry){
			comboMarry.add(it);
		}
		if(marry.length > 0){
			comboMarry.select(0);
		}
		
		String[] edu = EducationService.getInstant().getEducations();
		comboEducation.add("");
		for(String it : edu){
			comboEducation.add(it);
		}
		if(edu.length > 0){
			comboEducation.select(0);
		}
	}
	
	public CustomerQueryKey getQueryKey(){
		CustomerQueryKey key = new CustomerQueryKey();
		key.address = textAddress.getText();
		key.education = comboEducation.getText();
		key.gender = comboGender.getText();
		key.identify = textIdentify.getText();
		key.name = textName.getText();
		if(textRegional.getData() != null){
			Regional re = (Regional) textRegional.getData();
			key.regional = re.getRegionalCode();
		}else{
			key.regional = null;
		}
		
		return key;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
