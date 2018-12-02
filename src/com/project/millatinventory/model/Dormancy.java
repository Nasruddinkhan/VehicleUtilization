package com.project.millatinventory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MRDORP10")
public class Dormancy {

	@Id
	@Column(name = "DOR10AN", length = 34)
	private String accountNumber;
	
	@Column(name = "DOR10NAME", length = 60)
	private String name;
	
	@Column(name = "DOR10A1", length = 60)
	private String address1;
	
	@Column(name = "DOR10A2", length = 60)
	private String address2;
	
	@Column(name = "DOR10A3", length = 60)
	private String address3;
	
	@Column(name = "DOR10A4", length = 60)
	private String address4;
	
	@Column(name = "DOR10A5", length = 60)
	private String address5;
	
	@Column(name = "DOR10EML", length = 60)
	private String emailAddress;
	
	@Column(name = "DOR10AIG0", length = 1)
	private String emailSentStatus;
	
	@Column(name = "DOR10LNM", length = 2)
	private String language;
	
	@Column(name = "DOR10STS", length = 1)
	private String status;
	
	
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "ACCOUNT_TYPE", length = 20)
	private String accountType;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailSentStatus() {
		return emailSentStatus;
	}

	public void setEmailSentStatus(String emailSentStatus) {
		this.emailSentStatus = emailSentStatus;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
