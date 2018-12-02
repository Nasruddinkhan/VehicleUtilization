package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle")
public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name = "VEHICLE_ID")
	private int vehicleId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VEHICLES_TYPE")
	private Moter vehiclesType; 	
	

	public Moter getVehiclesType() {
		return vehiclesType;
	}

	public void setVehiclesType(Moter vehiclesType) {
		this.vehiclesType = vehiclesType;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VENDOR_TYPE")
	private Vendors vendorType; 	
	
	public Vendors getVendorType() {
		return vendorType;
	}

	public void setVendorType(Vendors vendorType) {
		this.vendorType = vendorType;
	}

	@Column(name = "VEHICLES_NUMBER")
	private String vehiclesNumber;
	
	@Column(name = "TYPE")
		private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "Model_Number")
	private String modelNumber;
	@Column(name = "Issue_Date")
	private String issueDate;
	@Column(name = "STATUS")
	private String status;


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	/*public String getVehiclesType() {
		return vehiclesType;
	}
	public void setVehiclesType(String vehiclesType) {
		this.vehiclesType = vehiclesType;
	}*/
	public String getVehiclesNumber() {
		return vehiclesNumber;
	}
	public void setVehiclesNumber(String vehiclesNumber) {
		this.vehiclesNumber = vehiclesNumber;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehiclesType=" + vehiclesType + ", vendorType=" + vendorType
				+ ", vehiclesNumber=" + vehiclesNumber + ", type=" + type + ", modelNumber=" + modelNumber
				+ ", issueDate=" + issueDate + ", status=" + status + "]";
	}
	
	
	
}
