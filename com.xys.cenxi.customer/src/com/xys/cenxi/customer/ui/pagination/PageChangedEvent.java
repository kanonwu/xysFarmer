package com.xys.cenxi.customer.ui.pagination;
/**
 * ��ҳ�ؼ�ҳ��ı��¼�����
 * �Ӹö�����Ի�ȡ����ҳ�ؼ������ӷ�ҳ�ؼ������ȡ����ǰ��ҳ���
 * @author wjl
 *
 */
public class PageChangedEvent {
	protected PagerBar source;
	/**
	 * 
	 * @param source �¼�Դ�ؼ�
	 */
	public PageChangedEvent(PagerBar source){
		this.source=source;
	}
	/**
	 * ��ȡ�¼�Դ
	 * @return Դ��ҳ�ؼ�
	 */
	public PagerBar getPager(){
		return this.source;
	}
	

}
