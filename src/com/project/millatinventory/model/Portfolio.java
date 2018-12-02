package com.project.millatinventory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MRARIP02")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLIENT_NAME",length=100)
	private String clientName;
	
	
	@Column(name = "TOTALINJECTION", length = 14,precision=12,scale=2)
	private BigDecimal totalInjection;
	@Column(name = "TOTALWIHDRWAWAL", length = 14,precision=12,scale=2)
	private BigDecimal totalWithdrawal;
	@Override
	public String toString() {
		return "Portfolio [clientName=" + clientName + ", totalInjection=" + totalInjection + ", totalWithdrawal="
				+ totalWithdrawal + ", totalProfit=" + totalProfit + ", accountValue=" + accountValue + "]";
	}
	@Column(name = "TOTAL_PROFIT", length = 14,precision=12,scale=2)
	private BigDecimal totalProfit;
	@Column(name = "ACCOUNT_VALUES", length = 14,precision=12,scale=2)
	private BigDecimal accountValue;
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public BigDecimal getTotalInjection() {
		return totalInjection;
	}
	public void setTotalInjection(BigDecimal totalInjection) {
		this.totalInjection = totalInjection;
	}
	public BigDecimal getTotalWithdrawal() {
		return totalWithdrawal;
	}
	public void setTotalWithdrawal(BigDecimal totalWithdrawal) {
		this.totalWithdrawal = totalWithdrawal;
	}
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	public BigDecimal getAccountValue() {
		return accountValue;
	}
	public void setAccountValue(BigDecimal accountValue) {
		this.accountValue = accountValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
