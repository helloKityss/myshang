package com.myshang.server.common;

/**
 * @author lidongdong
 * 2017年11月21日 上午9:43:57
 */
public class Page {
	public static final int DEFAULT_PAGESIZE = 10;	//默认每页10条记录
	public static final int DEFAULT_CURRENTPAGE = 1;//默认起始页为第一页
	private int pageSize;	//分页大小
	private int currentPage;//当前页码
	private int totalPage;	//总页数
	private int totalCount;	//总记录数
	private transient int offset;
	private Object data = null;
	public Page(){
		this.pageSize = DEFAULT_PAGESIZE;
		this.currentPage = DEFAULT_CURRENTPAGE;
		this.offset = (currentPage-1) * pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
