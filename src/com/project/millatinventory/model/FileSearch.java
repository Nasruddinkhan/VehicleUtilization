package com.project.millatinventory.model;

import java.io.Serializable;
import java.sql.Date;

public class FileSearch extends BaseBean implements Serializable{
private static final long serialVersionUID = 1L;
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
private String empId;
private String mobileNumber;

private String fromDate;
private String toDate;
private String month;

}