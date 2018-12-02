package com.project.millatinventory.controller;

public class SearchBean {

	private int vehicleType; 
	private int vendorType; 
	private int siteId;
	private int vehicleNumber;
	public int getVehicleType() {
		return vehicleType;
	}
	@Override
	public String toString() {
		return "SearchBean [vehicleType=" + vehicleType + ", siteId=" + siteId + ", vehicleNumber=" + vehicleNumber
				+ ", entryFromDate=" + entryFromDate + ", entryToDate=" + entryToDate + ", vendorType=" + vendorType + "]";
	}
	
	
	public int getVendorType() {
		return vendorType;
	}
	public void setVendorType(int vendorType) {
		this.vendorType = vendorType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(int vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getEntryFromDate() {
		return entryFromDate;
	}
	public void setEntryFromDate(String entryFromDate) {
		this.entryFromDate = entryFromDate;
	}
	public String getEntryToDate() {
		return entryToDate;
	}
	public void setEntryToDate(String entryToDate) {
		this.entryToDate = entryToDate;
	}
	private String entryFromDate;
	private String entryToDate;
	
}
