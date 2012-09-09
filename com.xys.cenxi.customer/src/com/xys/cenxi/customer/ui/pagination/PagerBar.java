package com.xys.cenxi.customer.ui.pagination;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * ��ҳ�ؼ����ṩ���ֻ������ķ�ҳ��ʽ��
 * <p>
 * <b>���:</b> <code>Pager.FULL_STYLE</code>:ȫ��񡢰�����ǰҳ����Ϣ���硰��ǰ��ʾ2����
 * <code>Pager.SIMPLE_STYLE</code>:�򵥷�񣬲�������ǰҳ����Ϣ
 * </p>
 * �ڵ��øÿؼ���paint()�������Ʒ�ҳǰ��Ҫ׼���Ĳ�������
 * <ol>
 * <li><code>recordCount</code>:������Ŀ����</li>
 * <li><code>pageSize</code>:ÿҳ��ʾ��Ŀ��</li>
 * <li><code>pageIndex</code>:��ʾҳҳ�룬<b>ע��:����Ŵ�1��ʼ��1��ʾ��һҳ</b></li>
 * </ol>
 * ����Ҫ�����������ķ�ҳ�ؼ�ʱ���̳�Pager�࣬����дpaint()���� ʹ��������<code>
 * </br>Pager pager = new Pager(pagerArea, Pager.SIMPLE_STYLE, 100,
 * </br>				10, 1);//��ҳ�ؼ���100����Ŀ��ÿҳ��ʾ10������ǰҳ��Ϊ��һҳ
 * </br>		try{
 * </br>			pager.paint();//���Ʒ�ҳ�ؼ�
 * </br>		}catch(Exception e){
 * </br>			System.out.println(e);
 * </br>		}
 * </br>		pager.addPageChangedListener(new PageChangedListener() {
 * </br>
 * </br>			public void pageChanged(PageChangedEvent e) {
 * </br>				int index;
 * </br>				index=e.getPager().getPageIndex();//��ȡ����ǰҳ��
 * </br>			}
 * </br>		});
 *</code> </br>
 * 
 * @author wjl
 * 
 */
public class PagerBar extends Composite {
	/**
	 * <code>FULL_STYLE</code> ȫ���
	 */
	public static final int FULL_STYLE = 1;
	/**
	 * <code>SIMPLE_STYLE</code> �򵥷��
	 */
	public static final int SIMPLE_STYLE = 1 << 1;

	public static final int GOOGLE_STYLE = 1 << 2;
	
	public static final int PAGE_SIZE = 10;

	protected int recordCount;
	protected int pageSize;
	protected int pageIndex;
	protected int style;

	protected int currRecordCount;
	protected int maxPageCount;
	protected List<PageChangedListener> listenerList;

	/**
	 * 
	 * @param parent
	 *            ������
	 * @param style
	 *            ���
	 *            <ul>
	 *            <li><code>FULL_STYLE</code>:ȫ���</li>
	 *            <li><code>SIMPLE_STYLE</code>:�򵥷��</li>
	 *            <li><code>GOOGLE_STYLE</code>:�ȸ���</li>
	 *            </ul>
	 * @param recordCount
	 *            ����Ŀ��������С��0
	 * @param pageSize
	 *            ÿҳ��ʾ��Ŀ�����������0
	 * @param pageIndex
	 *            ��ʾ�ڼ�ҳ ��һҳΪ1����1��ʼ
	 * @exception IllegalArgumentException
	 *                ����Ĳ������Ϸ�
	 */
	public PagerBar(Composite parent, int style, int recordCount, int pageSize,
			int pageIndex) {
		super(parent, 0);
		this.style = style;
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		paint();
	}
	
	private void removeChildren(){
		Control[] children = this.getChildren();
		for(Control child : children){
			if(child != null && !child.isDisposed()){
				child.dispose();
			}
		}
	}

	/**
	 * ���Ʒ�ҳ�ؼ�
	 */
	protected void paint() {
		// this.setBackground(Color.COLOR_DARKCYAN);
		checkArgs();
		removeChildren();
		switch (this.style) {
		case PagerBar.FULL_STYLE:
			paintFull();
			break;
		case PagerBar.SIMPLE_STYLE:
			paintSimple();
			break;
		case PagerBar.GOOGLE_STYLE:
			paintGoogle();
			break;
		default:
			paintFull();
		}
		this.layout();

	}

	protected boolean checkArgs() {
		if (recordCount < 0) {
			throw new java.lang.IllegalArgumentException(
					"Pager��������ȷ����¼������recordCount������С��0");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException(
					"Pager��������ȷ��ÿҳ��ʾ����pageSize������С�ڵ���0");
		}
		if (pageIndex < 1) {
			throw new IllegalArgumentException(
					"Pager��������ȷ����ʾҳ�ţ�pageIndex������С��1");
		}
		maxPageCount = recordCount / pageSize
				+ (recordCount % pageSize == 0 ? 0 : 1);
		currRecordCount = 0;
		if (maxPageCount != 0) {
			currRecordCount = (pageIndex == maxPageCount) ? (recordCount - (pageIndex - 1)
					* pageSize)
					: (pageSize);
		}
		return true;
	}

	private void paintFull() {
		GridLayout gl = new GridLayout(4, false);
		this.setLayout(gl);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		Label label = new Label(this, 0);
		String tips = "��" + pageIndex + "ҳ��" + "��" + maxPageCount + "ҳ��ÿҳ" + pageSize + "�У���" + recordCount + "��";

		label	.setText(tips);
		label.setLayoutData(gd);

		new Label(this, 0).setText("��"+" " + maxPageCount +" ҳ");
		final Combo cl = new Combo(this, SWT.READ_ONLY);
		if (this.recordCount <= 0) {
			return;
		}
		List<String> listItem = new ArrayList<String>(pageSize);
				
		for (int i = 1; i <= maxPageCount; i++) {
			String item = "��" + i + "ҳ";
			listItem.add(item);
			cl.add(item);
		}
		cl.setText(listItem.get(pageIndex));
		cl.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pageIndex = cl.getSelectionIndex() + 1;
				try {
					paint();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				notifyListener();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private void paintGoogle() {
		this.setLayout(new FillLayout());
		Composite real;
		real = new Composite(this, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.verticalSpacing = 0 ;
		real.setLayout(layout);
//		real.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
//				| GridData.HORIZONTAL_ALIGN_FILL  
//				| GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL));
		Composite all = new Composite(real, SWT.NONE);
		GridData gd1 = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END  
				| GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		
		all.setLayoutData(gd1);
		all.setLayout(new GridLayout(4, false));
		
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		Label label = new Label(all, 0);
		String tips = "��" + pageIndex + "ҳ��" + "��" + maxPageCount + "ҳ��ÿҳ" + pageSize + "�У���" + recordCount + "��";
		label	.setText(tips);
		label.setLayoutData(gd);
		
		Link prev = new Link(all, SWT.NONE);
		prev.setLayoutData(new GridData());
		prev.setText("<a>��һҳ</a>");
		Composite middle = new Composite(all, SWT.NONE);
		int beforeCount = this.pageIndex > 9 ? 9 : (pageIndex - 1);
		int afterCount = (this.maxPageCount - this.pageIndex) >= 9 ? 9
				: (this.maxPageCount - this.pageIndex);
		int allCount = beforeCount + 1 + afterCount;
		middle.setLayout(new GridLayout(allCount, false));
		Link temp;
		Label curr;
		for (int i = 0; i < allCount; i++) {
			if (i == beforeCount) {
				curr = new Label(middle, SWT.NONE);
				curr.setText(this.pageIndex + "");
			} else {
				temp = new Link(middle, SWT.NONE);
				final int index = this.pageIndex + (i - beforeCount);
				temp.setText("<a>" + index + "</a>");
				temp.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						PagerBar.this.pageIndex = index;
						paint();
						notifyListener();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});
			}
		}
		Link next = new Link(all, SWT.NONE);
		next.setLayoutData(new GridData());
		next.setText("<a>��һҳ</a>");
		next.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PagerBar.this.pageIndex++;
				paint();
				notifyListener();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		prev.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PagerBar.this.pageIndex--;
				paint();
				notifyListener();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		if (this.pageIndex <= 1) {
			prev.setEnabled(false);
		}
		if (this.pageIndex >= this.maxPageCount) {
			next.setEnabled(false);
		}
	}

	private void paintSimple() {
		GridLayout gl = new GridLayout(3, false);
		this.setLayout(gl);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END
				| GridData.FILL_HORIZONTAL);
		Label l = new Label(this, SWT.NONE);
		l.setText("��" +" "+ maxPageCount+" ҳ");
		l.setLayoutData(gd);
		final Combo cl = new Combo(this, SWT.READ_ONLY);
		if (this.recordCount <= 0) {
			return;
		}
		
		List<String> listItem = new ArrayList<String>(pageSize);
		for (int i = 1; i <= maxPageCount; i++) {
			String item = "��" + i + "ҳ";
			listItem.add(item);
		}
		cl.setText(listItem.get(pageIndex));
		cl.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				pageIndex = cl.getSelectionIndex() + 1;
				try {
					paint();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				notifyListener();
			}
		});
	}

	/**
	 * ���ӷ�ҳ�仯������
	 * 
	 * @param o
	 *            <code>PageChangedListener</code>����
	 */
	public void addPageChangedListener(PageChangedListener o) {
		if (listenerList == null) {
			listenerList = new ArrayList<PageChangedListener>();
		}
		listenerList.add(o);
	}

	/**
	 * ɾ����ҳ�仯������
	 * 
	 * @param o
	 *            ����������
	 */
	public void removePageChangedListener(PageChangedListener o) {
		if (listenerList != null && !listenerList.isEmpty()) {
			listenerList.remove(o);
		}

	}

	private void notifyListener() {
		if (listenerList != null && !listenerList.isEmpty()) {
			for (Iterator<PageChangedListener> iterator = listenerList
					.iterator(); iterator.hasNext();) {
				iterator.next().pageChanged(new PageChangedEvent(this));
			}
		}
	}

	/**
	 * ��ȡ��Ŀ����
	 * 
	 * @return ������Ŀ����
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * ������Ŀ����
	 * 
	 * @param recordCount
	 *            �����õ���Ŀ����
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		this.paint();
	}

	/**
	 * ��ȡÿҳ��ʾ��Ŀ��
	 * 
	 * @return ����ÿҳ��ʾ��Ŀ��
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ����ÿҳ��ʾ��Ŀ��
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.paint();
	}

	/**
	 * ��ȡ��ǰҳ��š�ҳ��Ŵ�1��ʼ
	 * 
	 * @return ���ص�ǰҳ��� ��һҳΪ1
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * ���õ�ǰҳ ��һҳΪ1
	 * 
	 * @param pageIndex
	 *            ҳ���
	 */
	public void setPageIndex(int pageIndex) {
		if (pageIndex != this.pageIndex) {
			this.pageIndex = pageIndex;
			this.paint();
			notifyListener();
		}
	}
	
	public void reset(int recordCount, int pageSize, int pageIndex){
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		
		paint();
	}
}
