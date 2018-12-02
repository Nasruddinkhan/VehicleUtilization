package com.project.millatinventory.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class ExternalTransations implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PYM03BID; //BATCH ID 
	private Integer PYM03ITM ; // Item Number
	private String PYM03AB1 ; // A/C Branch
	private String PYM03AN1 ; // A/C Number
	private String PYM03AS1 ; // A/C Sufix
	private BigDecimal PYM03TAMT ; // Transfer Amount
	private String PYM03OAN1 ; // Beneficiary A/C
	private String PYM03BAD1 ; //Name & Address
	private String PYM03PYP ; // Payment Purpose Code
	private String PYM03PD1 ; //Payment Details 1
	private String PYM03PD2 ; // Payment Details 2
	private String PYM03SWT ; // Beneficiary Swift
	private String PYM03PCCY; // Pay Currency
	public String getPYM03BID() {
		return PYM03BID;
	}
	public void setPYM03BID(String pYM03BID) {
		PYM03BID = pYM03BID;
	}
	public Integer getPYM03ITM() {
		return PYM03ITM;
	}
	public void setPYM03ITM(Integer pYM03ITM) {
		PYM03ITM = pYM03ITM;
	}
	public String getPYM03AB1() {
		return PYM03AB1;
	}
	public void setPYM03AB1(String pYM03AB1) {
		PYM03AB1 = pYM03AB1;
	}
	public String getPYM03AN1() {
		return PYM03AN1;
	}
	public void setPYM03AN1(String pYM03AN1) {
		PYM03AN1 = pYM03AN1;
	}
	public String getPYM03AS1() {
		return PYM03AS1;
	}
	public void setPYM03AS1(String pYM03AS1) {
		PYM03AS1 = pYM03AS1;
	}
	public BigDecimal getPYM03TAMT() {
		return PYM03TAMT;
	}
	public void setPYM03TAMT(BigDecimal pYM03TAMT) {
		PYM03TAMT = pYM03TAMT;
	}
	public String getPYM03OAN1() {
		return PYM03OAN1;
	}
	public void setPYM03OAN1(String pYM03OAN1) {
		PYM03OAN1 = pYM03OAN1;
	}
	public String getPYM03BAD1() {
		return PYM03BAD1;
	}
	public void setPYM03BAD1(String pYM03BAD1) {
		PYM03BAD1 = pYM03BAD1;
	}
	public String getPYM03PYP() {
		return PYM03PYP;
	}
	public void setPYM03PYP(String pYM03PYP) {
		PYM03PYP = pYM03PYP;
	}
	public String getPYM03PD1() {
		return PYM03PD1;
	}
	public void setPYM03PD1(String pYM03PD1) {
		PYM03PD1 = pYM03PD1;
	}
	public String getPYM03PD2() {
		return PYM03PD2;
	}
	public void setPYM03PD2(String pYM03PD2) {
		PYM03PD2 = pYM03PD2;
	}
	public String getPYM03SWT() {
		return PYM03SWT;
	}
	public void setPYM03SWT(String pYM03SWT) {
		PYM03SWT = pYM03SWT;
	}
	public String getPYM03PCCY() {
		return PYM03PCCY;
	}
	public void setPYM03PCCY(String pYM03PCCY) {
		PYM03PCCY = pYM03PCCY;
	}
	public String getPYM03CCODE() {
		return PYM03CCODE;
	}
	public void setPYM03CCODE(String pYM03CCODE) {
		PYM03CCODE = pYM03CCODE;
	}
	public BigDecimal getPYM03CAMT() {
		return PYM03CAMT;
	}
	public void setPYM03CAMT(BigDecimal pYM03CAMT) {
		PYM03CAMT = pYM03CAMT;
	}
	public String getPYM03SWTI() {
		return PYM03SWTI;
	}
	public void setPYM03SWTI(String pYM03SWTI) {
		PYM03SWTI = pYM03SWTI;
	}
	public String getPYM03REF() {
		return PYM03REF;
	}
	public void setPYM03REF(String pYM03REF) {
		PYM03REF = pYM03REF;
	}
	public String getPYM03REC() {
		return PYM03REC;
	}
	public void setPYM03REC(String pYM03REC) {
		PYM03REC = pYM03REC;
	}
	public String getPYM03MES7() {
		return PYM03MES7;
	}
	public void setPYM03MES7(String pYM03MES7) {
		PYM03MES7 = pYM03MES7;
	}
	public String getPYM03MESGD() {
		return PYM03MESGD;
	}
	public void setPYM03MESGD(String pYM03MESGD) {
		PYM03MESGD = pYM03MESGD;
	}
	public String getPYM03PUSR() {
		return PYM03PUSR;
	}
	public void setPYM03PUSR(String pYM03PUSR) {
		PYM03PUSR = pYM03PUSR;
	}
	public Date getPYM03PDAT() {
		return PYM03PDAT;
	}
	public void setPYM03PDAT(Date pYM03PDAT) {
		PYM03PDAT = pYM03PDAT;
	}
	private String PYM03CCODE ; // Charge Code
	private BigDecimal PYM03CAMT ; // Charge Amount
	private String PYM03SWTI; // Intermediary Swift
	private String PYM03REF; // Payment Reference
	private String PYM03REC ; //Recovery Ststus
	private String PYM03MES7 ; // Error ID
	private String PYM03MESGD ; // Error Message
	private String PYM03PUSR ; // Log User
	private Date PYM03PDAT ;// Date &Time
	
}