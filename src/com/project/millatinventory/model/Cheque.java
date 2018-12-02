package com.project.millatinventory.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHEQUE_PRINTING")
public class Cheque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cheque_No")
	private String cheque_No;
	@Column(name = "shareholder_No")
	private Integer shareholder_No;
	@Column(name = "chequeDate")
	private String chequeDate ;
	@Column(name = "shareholderName")
	private String shareholderName ;
	@Column(name = "address1")
	private String address1;
	@Column(name = "address2")
	private String address2;
	@Column(name = "countryName")
	private String countryName ;
	@Column(name = "cityName")
	private String cityName;
	@Column(name = "noOfShares")
	private Integer noOfShares;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "currency")
	private String currency ;
	@Column(name = "accountNo")
	private String accountNo ;
	@Column(name = "micrNo")
	private String micrNo; 
	
	public String getCheque_No() {
		return cheque_No;
	}
	public void setCheque_No(String cheque_No) {
		this.cheque_No = cheque_No;
	}
	public Integer getShareholder_No() {
		return shareholder_No;
	}
	public void setShareholder_No(Integer shareholder_No) {
		this.shareholder_No = shareholder_No;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getShareholderName() {
		return shareholderName;
	}
	public void setShareholderName(String shareholderName) {
		this.shareholderName = shareholderName;
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Integer noOfShares) {
		this.noOfShares = noOfShares;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	@Override
	public String toString() {
		return "Cheque [cheque_No=" + cheque_No + ", shareholder_No=" + shareholder_No + ", chequeDate=" + chequeDate
				+ ", shareholderName=" + shareholderName + ", address1=" + address1 + ", address2=" + address2
				+ ", countryName=" + countryName + ", cityName=" + cityName + ", noOfShares=" + noOfShares + ", amount="
				+ amount + ", currency=" + currency + ", accountNo=" + accountNo + ", micrNo=" + micrNo + "]";
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}


}