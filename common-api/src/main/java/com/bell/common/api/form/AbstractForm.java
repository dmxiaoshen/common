package com.bell.common.api.form;

import java.io.Serializable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.github.pagehelper.PageHelper;

public class AbstractForm<T extends AbstractForm<T>> implements Serializable {

	/** */
	private static final long serialVersionUID = -7696827439061145429L;

	@DefaultValue("1")
	@QueryParam("pageNum")
	private int pageNum = 1;

	@DefaultValue("10")
	@QueryParam("pageSize")
	private int pageSize = 10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings("unchecked")
	public final T enablePage() {
		PageHelper.startPage(pageNum, pageSize);
		return (T) this;
	}

}
