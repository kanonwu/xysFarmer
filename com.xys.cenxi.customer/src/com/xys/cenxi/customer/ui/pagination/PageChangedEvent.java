package com.xys.cenxi.customer.ui.pagination;
/**
 * 分页控件页码改变事件对象
 * 从该对象可以获取到分页控件，并从分页控件里可以取到当前的页序号
 * @author wjl
 *
 */
public class PageChangedEvent {
	protected PagerBar source;
	/**
	 * 
	 * @param source 事件源控件
	 */
	public PageChangedEvent(PagerBar source){
		this.source=source;
	}
	/**
	 * 获取事件源
	 * @return 源分页控件
	 */
	public PagerBar getPager(){
		return this.source;
	}
	

}
