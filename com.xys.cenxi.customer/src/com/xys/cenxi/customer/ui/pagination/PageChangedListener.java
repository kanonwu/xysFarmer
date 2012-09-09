package com.xys.cenxi.customer.ui.pagination;
/**
 * 分页控件页码改变监听器
 * @author zhouqiufang
 * <p>
 * <b>触发：</b>页码变化时，如用户选择了在页码列表中的不同页码，或者点击了“上一页”，“下一页”等
 * </p>
 */
public interface PageChangedListener {
	/**
	 * 页码改变处理方法
	 * @param e 页码改变事件
	 */
	public void pageChanged(PageChangedEvent e);

}
