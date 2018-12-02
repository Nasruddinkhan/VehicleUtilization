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
@Table(name="AccountTable")
public class AccountTable implements Serializable{
private static final long serialVersionUID = 1L;

@Id
@Column(name = "AccountType")
private String accountType;

@Column(name = "IBAN")
private String iBAN;
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public String getiBAN() {
	return iBAN;
}
public void setiBAN(String iBAN) {
	this.iBAN = iBAN;
}

}