package com.project.millatinventory.model;

import java.io.Serializable;
import java.sql.Date;

public class ExternalTransactionSearch extends BaseBean implements Serializable{
private static final long serialVersionUID = 1L;

private String branch;
private String accountNumber;
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getPaymentPurposeCode() {
	return paymentPurposeCode;
}
public void setPaymentPurposeCode(String paymentPurposeCode) {
	this.paymentPurposeCode = paymentPurposeCode;
}
private String fromDate;
private String toDate;
private String paymentPurposeCode;

}