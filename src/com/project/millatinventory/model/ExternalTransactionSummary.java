package com.project.millatinventory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ExternalTransactionSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	private String PYM02BID;
	public String getPYM02BID() {
		return PYM02BID;
	}
	public void setPYM02BID(String pYM02BID) {
		PYM02BID = pYM02BID;
	}
	public String getPYM02FIL() {
		return PYM02FIL;
	}
	public void setPYM02FIL(String pYM02FIL) {
		PYM02FIL = pYM02FIL;
	}
	public Integer getPYM02TOT() {
		return PYM02TOT;
	}
	public void setPYM02TOT(Integer pYM02TOT) {
		PYM02TOT = pYM02TOT;
	}
	public String getPYM02SUC() {
		return PYM02SUC;
	}
	public void setPYM02SUC(String pYM02SUC) {
		PYM02SUC = pYM02SUC;
	}
	public String getPYM02FAIL() {
		return PYM02FAIL;
	}
	public void setPYM02FAIL(String pYM02FAIL) {
		PYM02FAIL = pYM02FAIL;
	}
	public String getPYM02PBR() {
		return PYM02PBR;
	}
	public void setPYM02PBR(String pYM02PBR) {
		PYM02PBR = pYM02PBR;
	}
	public BigDecimal getPYM02TAMT() {
		return PYM02TAMT;
	}
	public void setPYM02TAMT(BigDecimal pYM02TAMT) {
		PYM02TAMT = pYM02TAMT;
	}
	public String getPYM02USRU() {
		return PYM02USRU;
	}
	public void setPYM02USRU(String pYM02USRU) {
		PYM02USRU = pYM02USRU;
	}
	public Date getPYM02DATU() {
		return PYM02DATU;
	}
	public void setPYM02DATU(Date pYM02DATU) {
		PYM02DATU = pYM02DATU;
	}
	public String getPYM02USRP() {
		return PYM02USRP;
	}
	public void setPYM02USRP(String pYM02USRP) {
		PYM02USRP = pYM02USRP;
	}
	public Date getPYM02DATP() {
		return PYM02DATP;
	}
	public void setPYM02DATP(Date pYM02DATP) {
		PYM02DATP = pYM02DATP;
	}
	public String getPYM02ACT() {
		return PYM02ACT;
	}
	public void setPYM02ACT(String pYM02ACT) {
		PYM02ACT = pYM02ACT;
	}
	public String getPYM02REM() {
		return PYM02REM;
	}
	public void setPYM02REM(String pYM02REM) {
		PYM02REM = pYM02REM;
	}
	public String getPYM02STS() {
		return PYM02STS;
	}
	public void setPYM02STS(String pYM02STS) {
		PYM02STS = pYM02STS;
	}
	private String PYM02FIL;

	private Integer PYM02TOT;

	private String PYM02SUC;

	private String PYM02FAIL;

	private String PYM02PBR;

	private BigDecimal PYM02TAMT;

	private String PYM02USRU;

	private Date PYM02DATU;

	private String PYM02USRP;

	private Date PYM02DATP;

	private String PYM02ACT;
	private String PYM02REM;
	public List<ExternalTransations> getFileData() {
		return fileData;
	}
	public void setFileData(List<ExternalTransations> fileData) {
		this.fileData = fileData;
	}
	private String PYM02STS;

	private List<ExternalTransations> fileData;
	

}