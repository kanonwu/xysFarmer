package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 其他家庭收入，包括外出务工收入和其他收入
 * @author wjl
 *
 */
@Table("t_otherIncome")
public class OtherIncome {
	@Name
	private String rowID;
	
	private String ownerID;
	
	private Float workIncome;
	
	private Float otherIncome;

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public Float getWorkIncome() {
		return workIncome;
	}

	public void setWorkIncome(Float workIncome) {
		this.workIncome = workIncome;
	}

	public Float getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Float otherIncome) {
		this.otherIncome = otherIncome;
	}
}
