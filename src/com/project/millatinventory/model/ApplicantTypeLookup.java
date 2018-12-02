package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ApplicantTypeLookup")
public class ApplicantTypeLookup implements Serializable{
private static final long serialVersionUID = 1L;

@Id
@Column(name = "TypeCode")
private String typeCode;
public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}
public String getTypeDescription() {
	return typeDescription;
}
public void setTypeDescription(String typeDescription) {
	this.typeDescription = typeDescription;
}
@Column(name = "TypeDescription")
private String typeDescription;

}