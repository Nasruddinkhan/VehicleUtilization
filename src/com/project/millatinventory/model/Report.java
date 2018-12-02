package com.project.millatinventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Report")
public class Report {
@Column(name="siteId")
private String siteId;
@Column(name="VehicleType")
private String VehicleType;
@Column(name="VehicleNumber")
private String VehicleNumber;
public String getSiteId() {
	return siteId;
}
public void setSiteId(String siteId) {
	this.siteId = siteId;
}
public String getVehicleType() {
	return VehicleType;
}
public void setVehicleType(String vehicleType) {
	VehicleType = vehicleType;
}
public String getVehicleNumber() {
	return VehicleNumber;
}
public void setVehicleNumber(String vehicleNumber) {
	VehicleNumber = vehicleNumber;
}

}
