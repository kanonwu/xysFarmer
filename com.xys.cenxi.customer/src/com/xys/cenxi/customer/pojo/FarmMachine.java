package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Å©Òµ»úÐµ
 * @author wjl
 *
 */
@Table("t_farmmachine")
public class FarmMachine {

	@Name
	private String rowID;
	
	private String ownerID;
		
	private String name;
	
	private Integer buyYear;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(Integer buyYear) {
		this.buyYear = buyYear;
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
