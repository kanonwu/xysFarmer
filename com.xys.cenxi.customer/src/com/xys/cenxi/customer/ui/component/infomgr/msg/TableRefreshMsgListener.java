package com.xys.cenxi.customer.ui.component.infomgr.msg;

import org.eclipse.jface.viewers.TableViewer;

import com.xys.cenxi.customer.msg.RefreshCustomerTableMsg;
import com.xys.cenxi.message.IMessageListener;

/**
 * ����ˢ�±����Ϣ
 * @author wjl
 *
 */
public class TableRefreshMsgListener implements IMessageListener{
	
	private TableViewer tv;
	
	public TableRefreshMsgListener(TableViewer tv){
		this.tv = tv;
	}

	@Override
	public void handerMessage(Object msg) {
		if(msg instanceof RefreshCustomerTableMsg){
			RefreshCustomerTableMsg ref = (RefreshCustomerTableMsg) msg;
			tv.setInput(ref.key);
		}
	}

}
