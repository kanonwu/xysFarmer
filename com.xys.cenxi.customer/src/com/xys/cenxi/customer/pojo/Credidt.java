package com.xys.cenxi.customer.pojo;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 其他重要信用信息
 * @author wjl
 *
 */

@Table("t_credidt")
public class Credidt {

	@Name
	private String rowID;
	
	private String ownerID;
	
	private String loadFor;
	
	private Float loadAmount;
	
	private String oldPeople;
	
	private String neighbourhood;
	
	private String publicGood;
	
	/**
	 * 商业保险
	 */
	private String businessInsurance;
	
	/**
	 * 商业保险金额
	 */
	private Float biAmount;
	
	/**
	 * 养老保险
	 */
	private String endowmentInsurance;
	
	/**
	 * 养老保险人数
	 */
	private Integer eiCount;
	
	/**
	 * 合作医疗
	 */
	private String cooperativeMedical;
	
	/**
	 * 合作医疗参加人数
	 */
	private Integer cmCount;

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

	public String getLoadFor() {
		return loadFor;
	}

	public void setLoadFor(String loadFor) {
		this.loadFor = loadFor;
	}

	public Float getLoadAmount() {
		return loadAmount;
	}

	public void setLoadAmount(Float loadAmount) {
		this.loadAmount = loadAmount;
	}

	public String getOldPeople() {
		return oldPeople;
	}

	public void setOldPeople(String oldPeople) {
		this.oldPeople = oldPeople;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getPublicGood() {
		return publicGood;
	}

	public void setPublicGood(String publicGood) {
		this.publicGood = publicGood;
	}

	public String getBusinessInsurance() {
		return businessInsurance;
	}

	public void setBusinessInsurance(String businessInsurance) {
		this.businessInsurance = businessInsurance;
	}

	public Float getBiAmount() {
		return biAmount;
	}

	public void setBiAmount(Float biAmount) {
		this.biAmount = biAmount;
	}

	public String getEndowmentInsurance() {
		return endowmentInsurance;
	}

	public void setEndowmentInsurance(String endowmentInsurance) {
		this.endowmentInsurance = endowmentInsurance;
	}

	public Integer getEiCount() {
		return eiCount;
	}

	public void setEiCount(Integer eiCount) {
		this.eiCount = eiCount;
	}

	public String getCooperativeMedical() {
		return cooperativeMedical;
	}

	public void setCooperativeMedical(String cooperativeMedical) {
		this.cooperativeMedical = cooperativeMedical;
	}

	public Integer getCmCount() {
		return cmCount;
	}

	public void setCmCount(Integer cmCount) {
		this.cmCount = cmCount;
	}
	
	
}
