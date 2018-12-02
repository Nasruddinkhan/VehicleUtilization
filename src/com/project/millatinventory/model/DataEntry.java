package com.project.millatinventory.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "DataEntry")
public class DataEntry extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "dataEntryId")
	private Integer dataEntryId;
	
	@Column(name="Time")
	private String Time;
	
	@Column(name="loading_unloading")
	private String loading_unloading;
	
	public String getLoading_unloading() {
		return loading_unloading;
	}

	public void setLoading_unloading(String loading_unloading) {
		this.loading_unloading = loading_unloading;
	}

	@Column(name="end_Time")
	private String endTime; 
	
	@Column(name="difftime")
	private String difftime;
		
	public String getDifftime() {
		return difftime;
	}

	public void setDifftime(String difftime) {
		this.difftime = difftime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name="Avg")
	private Double avg;
    
	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg2) {
		this.avg = avg2;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
	
		
	@Override
	public String toString() {
		return "DataEntry [dataEntryId=" + dataEntryId + ", Time=" + Time + ", endTime=" + endTime + ", avg=" + avg
				+ ", vehicle=" + vehicle + ", site=" + site + ", expense=" + expense + ", remarks=" + remarks
				+ ", entryDate=" + entryDate + ", vehicleType=" + vehicleType + ", ="
				+  ", hours=" + hours + ", loading=" + loading + ", unloading=" + unloading
				+ ", rate=" + rate + ", distance=" + distance + ", minutes=" + minutes + ", excavatorType=" + excavatorType + ",vendorType="+vendorType+"]";
	}

	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="vehicleId",referencedColumnName="VEHICLE_ID")	
	private Vehicle vehicle;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="vendorId",referencedColumnName="vendorId")
	private Vendors vendorType;
    
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name="siteId",referencedColumnName="siteId")
	private Sites site;
		
	@ManyToOne(fetch =FetchType.EAGER,optional=true)
	@JoinColumn(name="Type",referencedColumnName="Type")
	private Vehicle excavatorType;
	
	public Vehicle getExcavatorType() {
		return excavatorType;
	}

	public void setExcavatorType(Vehicle excavatorType) {
		this.excavatorType = excavatorType;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicleType")
	private Moter vehicleType; 	
    /*
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	@Column(name="SiteId")
	private String siteId;
*/	

   public Vendors getVendorType() {
		return vendorType;
	}

	public void setVendorType(Vendors vendorType) {
		this.vendorType = vendorType;
	}

	public Sites getSite() {
		return site;
	}

	public void setSite(Sites site) {
		this.site = site;
	}
	@Column(name="expense")
	private String expense;
	
	
	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	@Column(name="Remarks")
	private String remarks;
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="EntryDate")
	private Date entryDate;
    
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "dataEntry")
	private List<DataEntryExpense> dataEntryExpenses=new ArrayList<DataEntryExpense>();
*/
	/*public List<DataEntryExpense> getDataEntryExpenses() {
		return dataEntryExpenses;
	}

	public void setDataEntryExpenses(List<DataEntryExpense> dataEntryExpenses) {
		this.dataEntryExpenses = dataEntryExpenses;
	}*/

	public Moter getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Moter vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name="hours")
	private String hours;
    
	@Column(name="loading")
	private String loading;
	
	@Column(name="unloading")
	private String unloading;
	
	@Column(name="rate")
	private String rate;
	
	@Column(name="distance")
	private String distance;
	
	/*@Column(name="diesel")
	private String diesel;*/

	
	public String getLoading() {
		return loading;
	}

	public void setLoading(String loading) {
		this.loading = loading;
	}

	public String getUnloading() {
		return unloading;
	}

	public void setUnloading(String unloading) {
		this.unloading = unloading;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	/*public String getDiesel() {
		return diesel;
	}

	public void setDiesel(String diesel) {
		this.diesel = diesel;
	}*/

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name="minutes")
	private String minutes;
    
/*    @Column(name="DriverName")
	private String driver_name;
*/
	public Integer getDataEntryId() {
		return dataEntryId;
	}

	public void setDataEntryId(Integer dataEntryId) {
		this.dataEntryId = dataEntryId;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	private String workType;
	
/*	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	
	
	
		
	}

	
