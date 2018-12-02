package com.project.millatinventory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseBean {
	@Column(name ="CREATED_DATE")	
private Date createdDate;
	
	@Column(name ="CREATEDBY")	
private String createdBy;
	
	@Column(name ="MODIFIED_DATE")	
private Date modifiedDate;
	
	@Column(name ="MODIFIEDBY")	
private String modifiedBy;

public BaseBean(){}

public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public Date getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}
public String getModifiedBy() {
	return modifiedBy;
}
public void setModifiedBy(String modifiedby) {
	this.modifiedBy = modifiedby;
}
}
