package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 林权情况
 * @author wjl
 *
 */
@Table("t_forestrights")
public class ForestRights {

	@Name
	private String rowID;
	
	private String ownerID;
	
	private String rightsID;
	
	/**
	 * 品种
	 */
	private String variety;
	
	
	private Float area;
	
	private Float price;

	private String desc;
	
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

	public String getRightsID() {
		return rightsID;
	}

	public void setRightsID(String rightsID) {
		this.rightsID = rightsID;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
