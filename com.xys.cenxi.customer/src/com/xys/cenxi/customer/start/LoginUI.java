package com.xys.cenxi.customer.start;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.LoggerFactory;

import com.xys.cenxi.customer.data.UserService;
import com.xys.cenxi.customer.pojo.User;
import com.xys.cenxi.customer.util.UIUtil;

public class LoginUI extends TitleAreaDialog {
	private Text textName;
	private Text textPw;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public LoginUI(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("\u5C91\u6EAA\u519C\u6751\u5408\u4F5C\u94F6\u884C\u519C\u6237\u4FE1\u606F\u7BA1\u7406\r\n");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(null);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(126, 30, 48, 17);
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		textName = new Text(container, SWT.BORDER);
		textName.setBounds(180, 27, 134, 23);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(138, 59, 36, 17);
		label_1.setText("\u5BC6\u7801\uFF1A");
		
		textPw = new Text(container, SWT.BORDER | SWT.PASSWORD);
		textPw.setBounds(180, 56, 134, 23);

		return area;
	}

	@Override
	protected void okPressed() {
		String err = validateLogin();
		if(err != null){
			UIUtil.showMessage(err);
			setReturnCode(CANCEL);
		}else{
			setReturnCode(OK);
			close();
		}
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("\u786E\u5B9A");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("\u53D6\u6D88");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	private String validateLogin(){
		String name = textName.getText();
		if(name == null || name.length() == 0){
			textName.setFocus();
			return "请输入用户名。";
		}
		String pwd = textPw.getText();
		if(pwd == null || pwd.length() == 0){
			textPw.setFocus();
			return "请输入密码。";
		}
		
		User user = UserService.getInstance().getUser(name, pwd);
		if(user == null){
			textName.setFocus();
			return "用户名或密码错误。";
		}
		
		Content.setUser(user);
		
		LoggerFactory.getLogger(LoginUI.class).info("用户{}登录。", name);
		
		return null;
	}
}
