package com.project.millatinventory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DetailLookupTable")
public class DetailLookup implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	@Column(name = "Nationality", length = 50)
	private String nationality;

	@Id
	@Column(name = "ApplicantID", length = 50)
	private String applicantID;

	@Override
	public String toString() {
		return "DetailLookup [nationality=" + nationality + ", applicantID="
				+ applicantID + ", applicantName=" + applicantName
				+ ", applicantNIN=" + applicantNIN + ", guardianID="
				+ guardianID + ", passportNo=" + passportNo + ", dob=" + dob
				+ ", pastCode=" + pastCode + ", poBox=" + poBox + ", city="
				+ city + ", country=" + country + ", telephone=" + telephone
				+ ", fax=" + fax + ", email=" + email + ", nin1=" + nin1
				+ ", nin2=" + nin2 + ", nin3=" + nin3 + ", nin4=" + nin4
				+ ", acountNumber=" + accountNumber + ", accountName="
				+ accountName + ", bank=" + bank + ", branch=" + branch
				+ ", swift=" + swift + ", currency=" + currency + "]";
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Column(name = "ApplicantNAME", length = 100)
	private String applicantName;

	@Column(name = "ApplicantNIN", length = 15)
	private String applicantNIN;

	@Column(name = "GuardianID", length = 50)
	private String guardianID;

	@Column(name = "PassportNo", length = 50)
	private String passportNo;

	@Column(name = "DOB", length = 30)
	private String dob;

	@Column(name = "PastCode", length = 30)
	private String pastCode;

	@Column(name = "POBOX", length = 30)
	private String poBox;

	@Column(name = "CITY", length = 50)
	private String city;

	@Column(name = "Country", length = 50)
	private String country;

	@Column(name = "Telephone", length = 30)
	private String telephone;

	@Column(name = "Fax", length = 30)
	private String fax;

	@Column(name = "Email", length = 100)
	private String email;

	@Column(name = "NIN1", length = 30)
	private String nin1;
	@Column(name = "NIN2", length = 30)
	private String nin2;
	@Column(name = "NIN3", length = 30)
	private String nin3;
	@Column(name = "NIN4", length = 30)
	private String nin4;

	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getApplicantID() {
		return applicantID;
	}
	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}
	
	public String getApplicantNIN() {
		return applicantNIN;
	}
	public void setApplicantNIN(String applicantNIN) {
		this.applicantNIN = applicantNIN;
	}
	public String getGuardianID() {
		return guardianID;
	}
	public void setGuardianID(String guardianID) {
		this.guardianID = guardianID;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPastCode() {
		return pastCode;
	}
	public void setPastCode(String pastCode) {
		this.pastCode = pastCode;
	}
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNin1() {
		return nin1;
	}
	public void setNin1(String nin1) {
		this.nin1 = nin1;
	}
	public String getNin2() {
		return nin2;
	}
	public void setNin2(String nin2) {
		this.nin2 = nin2;
	}
	public String getNin3() {
		return nin3;
	}
	public void setNin3(String nin3) {
		this.nin3 = nin3;
	}
	public String getNin4() {
		return nin4;
	}
	public void setNin4(String nin4) {
		this.nin4 = nin4;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name = "AccountNumber", length = 50)
	private String accountNumber;
	@Column(name = "AccountName", length = 50)
	private String accountName;
	@Column(name = "Bank", length = 30)
	private String bank;
	@Column(name = "Branch", length = 30)
	private String branch;

	@Column(name = "Swift", length = 30)
	private String swift;
	@Column(name = "Currency", length = 30)
	private String currency;
	
	@Lob
    @Column(name="SIGN", columnDefinition="mediumblob")
    private byte[] signature;

	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}		

}
