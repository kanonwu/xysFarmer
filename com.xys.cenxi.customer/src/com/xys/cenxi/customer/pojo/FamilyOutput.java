package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 	家庭支出表
 * @author wjl
 *
 */
@Table("t_output")
public class FamilyOutput {

	@Name
	private String rowID;
	
	private String ownerID;
	
	private Float productionOutput;
	
	private Float liftOutput;
	
	private Float otherOutput;

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

	public Float getProductionOutput() {
		return productionOutput;
	}

	public void setProductionOutput(Float productionOutput) {
		this.productionOutput = productionOutput;
	}

	public Float getLiftOutput() {
		return liftOutput;
	}

	public void setLiftOutput(Float liftOutput) {
		this.liftOutput = liftOutput;
	}

	public Float getOtherOutput() {
		return otherOutput;
	}

	public void setOtherOutput(Float otherOutput) {
		this.otherOutput = otherOutput;
	}
}
