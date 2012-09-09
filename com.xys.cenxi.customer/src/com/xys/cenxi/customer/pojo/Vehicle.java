package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 车辆情况
 * @author wjl
 *
 */
@Table("t_vehicle")
public class Vehicle {

	@Name
	private String rowID;
	
	private String ownerID;
	
	/**
	 * 车牌号
	 */
	private String license;
	
	private String displacement;
	
	private Integer buyYear;
	
	private String type;
	
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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(Integer buyYear) {
		this.buyYear = buyYear;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
