package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Embeddable
public class AccountTypes implements Serializable{
private static final long serialVersionUID = 1L;

@Column(name="ApplicantId")
private String applicantId;

public String getAccounType() {
	return accounType;
}
public void setAccounType(String accounType) {
	this.accounType = accounType;
}
public String getApplicantId() {
	return applicantId;
}
public void setApplicantId(String applicantId) {
	this.applicantId = applicantId;
}

@Column(name = "AccounType")
private String accounType;

}