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
 * 分页控件，提供两种基本风格的分页方式。
 * <p>
 * <b>风格:</b> <code>Pager.FULL_STYLE</code>:全风格、包含当前页的信息，如“当前显示2条”
 * <code>Pager.SIMPLE_STYLE</code>:简单风格，不包含当前页的信息
 * </p>
 * 在调用该控件的paint()方法绘制分页前需要准备的参数包括
 * <ol>
 * <li><code>recordCount</code>:所有条目总数</li>
 * <li><code>pageSize</code>:每页显示条目数</li>
 * <li><code>pageIndex</code>:显示页页码，<b>注意:该序号从1开始，1表示第一页</b></li>
 * </ol>
 * 当需要创建其他风格的分页控件时，继承Pager类，并重写paint()方法 使用样例：<code>
 * </br>Pager pager = new Pager(pagerArea, Pager.SIMPLE_STYLE, 100,
 * </br>				10, 1);//分页控件共100个条目，每页显示10条，当前页码为第一页
 * </br>		try{
 * </br>			pager.paint();//绘制分页控件
 * </br>		}catch(Exception e){
 * </br>			System.out.println(e);
 * </br>		}
 * </br>		pager.addPageChangedListener(new PageChangedListener() {
 * </br>
 * </br>			public void pageChanged(PageChangedEvent e) {
 * </br>				int index;
 * </br>				index=e.getPager().getPageIndex();//获取到当前页码
 * </br>			}
 * </br>		});
 *</code> </br>
 * 
 * @author wjl
 * 
 */
public class PagerBar extends Composite {
	/**
	 * <code>FULL_STYLE</code> 全风格
	 */
	public static final int FULL_STYLE = 1;
	/**
	 * <code>SIMPLE_STYLE</code> 简单风格
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
	 *            父容器
	 * @param style
	 *            风格
	 *            <ul>
	 *            <li><code>FULL_STYLE</code>:全风格</li>
	 *            <li><code>SIMPLE_STYLE</code>:简单风格</li>
	 *            <li><code>GOOGLE_STYLE</code>:谷歌风格</li>
	 *            </ul>
	 * @param recordCount
	 *            总条目数，不能小于0
	 * @param pageSize
	 *            每页显示条目数，必须大于0
	 * @param pageIndex
	 *            显示第几页 第一页为1，从1开始
	 * @exception IllegalArgumentException
	 *                传入的参数不合法
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
	 * 绘制分页控件
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
					"Pager参数不正确，记录总数（recordCount）不能小于0");
		}
		if (pageSize <= 0) {
			throw new IllegalArgumentException(
					"Pager参数不正确，每页显示数（pageSize）不能小于等于0");
		}
		if (pageIndex < 1) {
			throw new IllegalArgumentException(
					"Pager参数不正确，显示页号（pageIndex）不能小于1");
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
		String tips = "第" + pageIndex + "页，" + "共" + maxPageCount + "页，每页" + pageSize + "行，共" + recordCount + "行";

		label	.setText(tips);
		label.setLayoutData(gd);

		new Label(this, 0).setText("共"+" " + maxPageCount +" 页");
		final Combo cl = new Combo(this, SWT.READ_ONLY);
		if (this.recordCount <= 0) {
			return;
		}
		List<String> listItem = new ArrayList<String>(pageSize);
				
		for (int i = 1; i <= maxPageCount; i++) {
			String item = "第" + i + "页";
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
		String tips = "第" + pageIndex + "页，" + "共" + maxPageCount + "页，每页" + pageSize + "行，共" + recordCount + "行";
		label	.setText(tips);
		label.setLayoutData(gd);
		
		Link prev = new Link(all, SWT.NONE);
		prev.setLayoutData(new GridData());
		prev.setText("<a>上一页</a>");
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
		next.setText("<a>下一页</a>");
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
		l.setText("共" +" "+ maxPageCount+" 页");
		l.setLayoutData(gd);
		final Combo cl = new Combo(this, SWT.READ_ONLY);
		if (this.recordCount <= 0) {
			return;
		}
		
		List<String> listItem = new ArrayList<String>(pageSize);
		for (int i = 1; i <= maxPageCount; i++) {
			String item = "第" + i + "页";
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
	 * 增加分页变化监听器
	 * 
	 * @param o
	 *            <code>PageChangedListener</code>对象
	 */
	public void addPageChangedListener(PageChangedListener o) {
		if (listenerList == null) {
			listenerList = new ArrayList<PageChangedListener>();
		}
		listenerList.add(o);
	}

	/**
	 * 删除分页变化监听器
	 * 
	 * @param o
	 *            监听器对象
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
	 * 获取条目总数
	 * 
	 * @return 返回条目总数
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * 设置条目总数
	 * 
	 * @param recordCount
	 *            待设置的条目总数
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		this.paint();
	}

	/**
	 * 获取每页显示条目数
	 * 
	 * @return 返回每页显示条目数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页显示条目数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.paint();
	}

	/**
	 * 获取当前页序号。页序号从1开始
	 * 
	 * @return 返回当前页序号 第一页为1
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置当前页 第一页为1
	 * 
	 * @param pageIndex
	 *            页序号
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
