package com.project.millatinventory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Print_Receipt")
public class Deal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REF_NO", length = 10)
	private Integer refNo;
	public Integer getRefNo() {
		return refNo;
	}
	public void setRefNo(Integer refNo) {
		this.refNo = refNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress1() {
		return customerAddress1;
	}
	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}
	public String getCustomerAddress2() {
		return customerAddress2;
	}
	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}
	public Integer getCustomerPinCode() {
		return customerPinCode;
	}
	public void setCustomerPinCode(Integer customerPinCode) {
		this.customerPinCode = customerPinCode;
	}
	public Integer getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(Integer principalAmount) {
		this.principalAmount = principalAmount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAcctBankName() {
		return acctBankName;
	}
	public void setAcctBankName(String acctBankName) {
		this.acctBankName = acctBankName;
	}
	public String getAcctBankAddress1() {
		return acctBankAddress1;
	}
	public void setAcctBankAddress1(String acctBankAddress1) {
		this.acctBankAddress1 = acctBankAddress1;
	}
	public String getAcctBankAddress2() {
		return acctBankAddress2;
	}
	public void setAcctBankAddress2(String acctBankAddress2) {
		this.acctBankAddress2 = acctBankAddress2;
	}
	public Integer getAcctBankPinCode() {
		return acctBankPinCode;
	}
	public void setAcctBankPinCode(Integer acctBankPinCode) {
		this.acctBankPinCode = acctBankPinCode;
	}
	public String getPayBankName() {
		return payBankName;
	}
	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}
	public Integer getPayBankPinCode() {
		return payBankPinCode;
	}
	public void setPayBankPinCode(Integer payBankPinCode) {
		this.payBankPinCode = payBankPinCode;
	}
	public String getReceivedCompanyName() {
		return receivedCompanyName;
	}
	public void setReceivedCompanyName(String receivedCompanyName) {
		this.receivedCompanyName = receivedCompanyName;
	}
	public String getReceivedCompanyAddress() {
		return receivedCompanyAddress;
	}
	public void setReceivedCompanyAddress(String receivedCompanyAddress) {
		this.receivedCompanyAddress = receivedCompanyAddress;
	}
	@Column(name = "CUST_NAME", length = 50)
	private String customerName;
	@Column(name = "CUST_ADDRESS1", length = 50)
	private String customerAddress1;
	@Column(name = "CUST_ADDRESS2", length = 50)
	private String customerAddress2;
	@Column(name = "CUST_PINCODE", length = 6)
	private Integer customerPinCode;
	@Column(name = "PRINC_AMT", length = 10)
	private Integer principalAmount;
	@Column(name = "START_DT", length = 50)
	private Date startDate;
	@Column(name = "MATURITY_DT", length = 50)
	private Date maturityDate;
	@Column(name = "NOOFDAYS", length = 10)
	private Integer numberOfDays;
	@Column(name = "PROFIT_RATE")
	private BigDecimal profitRate;
	@Column(name = "TOT_AMT", length = 10)
	private Integer totalAmount;
	@Column(name = "ACC_WITH_BANK_NAME", length = 60)
	private String acctBankName;
	@Column(name = "ACC_WITH_BANK_ADD1", length = 60)
	private String acctBankAddress1;
	@Override
	public String toString() {
		return "Deal [refNo=" + refNo + ", customerName=" + customerName + ", customerAddress1=" + customerAddress1
				+ ", customerAddress2=" + customerAddress2 + ", customerPinCode=" + customerPinCode
				+ ", principalAmount=" + principalAmount + ", startDate=" + startDate + ", maturityDate=" + maturityDate
				+ ", numberOfDays=" + numberOfDays + ", profitRate=" + profitRate + ", totalAmount=" + totalAmount
				+ ", acctBankName=" + acctBankName + ", acctBankAddress1=" + acctBankAddress1 + ", acctBankAddress2="
				+ acctBankAddress2 + ", acctBankPinCode=" + acctBankPinCode + ", payBankName=" + payBankName
				+ ", payBankPinCode=" + payBankPinCode + ", receivedCompanyName=" + receivedCompanyName
				+ ", receivedCompanyAddress=" + receivedCompanyAddress + "]";
	}
	@Column(name = "ACC_WITH_BANK_ADD2", length = 60)
	private String acctBankAddress2;
	@Column(name = "ACC_WITH_BANK_PINCODE", length = 6)
	private Integer acctBankPinCode;
	@Column(name = "PAY_BANK_NAME", length = 60)
	private String payBankName;
	@Column(name = "PAY_BANK_PINCODE", length = 6)
	private Integer payBankPinCode;
	@Column(name = "RECEIVEDCOMPNAME", length = 60)
	private String receivedCompanyName;
	@Column(name = "RECEIVEDCOMPADD", length = 60)
	private String receivedCompanyAddress;

}
