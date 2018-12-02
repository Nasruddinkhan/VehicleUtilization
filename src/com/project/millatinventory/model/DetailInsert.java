package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DetailInsertTable")
public class DetailInsert extends BaseBean implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "ApplicantID", length = 50)
	private String applicantID;

	@Column(name = "ApplicantNAME", length = 100)
	private String applicantName;

	@Override
	public String toString() {
		return "DetailInsert [applicantID=" + applicantID + ", applicantName="
				+ applicantName + ", applicantNIN=" + applicantNIN
				+ ", guardianID=" + guardianID + ", passportNo=" + passportNo
				+ ", dob=" + dob + ", pastCode=" + pastCode + ", poBox="
				+ poBox + ", city=" + city + ", country=" + country
				+ ", telephone=" + telephone + ", fax=" + fax + ", email="
				+ email + ", nationality=" + nationality + ", nin1=" + nin1
				+ ", nin2=" + nin2 + ", nin3=" + nin3 + ", nin4=" + nin4
				+ ", acountNumber=" + accountNumber + ", accountName="
				+ accountName + ", bank=" + bank + ", branch=" + branch
				+ ", swift=" + swift + ", currency=" + currency
				+ ", uploaderId=" + uploaderId + ", verifireId=" + verifireId
				+ ", verifiedDate=" + verifiedDate + ", uploadedDate="
				+ uploadedDate + ", verifierComment=" + verifierComment
				+ ", status=" + status + ", iban=" + iban + ", applicantType="
				+ applicantType + "]";
	}

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

	
	@Column(name = "Nationality", length = 50)
	private String nationality;
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "NIN1", length = 30)
	private String nin1;
	@Column(name = "NIN2", length = 30)
	private String nin2;
	@Column(name = "NIN3", length = 30)
	private String nin3;
	@Column(name = "NIN4", length = 30)
	private String nin4;

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
	@Column(name = "UploaderID", length = 15)
	private String uploaderId;

	@Column(name = "VerifierID", length = 30)
	private String verifireId;

	@Column(name = "VerifiedDate", length = 30)
	private String verifiedDate;
	@Column(name = "UploadedDate", length = 30)
	private String uploadedDate;
	@Column(name = "VerifierComment", length = 250)
	private String verifierComment;

	@Column(name = "Status", length = 15)
	private String status;

	@Column(name = "IBAN", length = 70)
	private String iban;

	@Column(name = "ApplicantType", length = 30)
	private String applicantType;

	public String getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
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

	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getVerifireId() {
		return verifireId;
	}

	public void setVerifireId(String verifireId) {
		this.verifireId = verifireId;
	}

	public String getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(String verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public String getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getVerifierComment() {
		return verifierComment;
	}

	public void setVerifierComment(String verifierComment) {
		this.verifierComment = verifierComment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	@Lob
    @Column(name="SIGN", columnDefinition="mediumblob")
    private byte[] signature;

	@Transient
	 private String signatureFile;

	public String getSignatureFile() {
		return signatureFile;
	}

	public void setSignatureFile(String signatureFile) {
		this.signatureFile = signatureFile;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

}
