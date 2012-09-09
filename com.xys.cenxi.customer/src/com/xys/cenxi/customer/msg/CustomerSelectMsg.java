package com.xys.cenxi.customer.msg;

import com.xys.cenxi.customer.pojo.Customer;

/**
 * 表格选择一行记录，发出消息，由CustomerTableCmp发出，CustomerMgrCmp接收
 * @author wjl
 *
 */
public class CustomerSelectMsg {

	public Customer selectCustomer;
}
