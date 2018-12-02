package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name="ApplicantAccount")
public class ApplicantAccount implements Serializable{
private static final long serialVersionUID = 1L;

@EmbeddedId
private AccountTypes accountTypes;

public AccountTypes getAccountTypes() {
	return accountTypes;
}

public void setAccountTypes(AccountTypes accountTypes) {
	this.accountTypes = accountTypes;
}
}