package com.project.millatinventory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FileHeader")
public class FileHeader extends BaseBean implements Serializable{
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "File_ID")
private Integer fileId;

@Column(name = "PYM03BID")
private String batchId;

@Column(name = "PYM02TOT")
private Integer no_of_transaction;

@Column(name = "PYM02SUC")
private Integer success;

@Column(name = "PYM02FAIL")
private Integer fail;


@Column(name = "PYM02PBR")
private String referance;

@Column(name = "PYM02TAMT")
private Integer amount;

public Integer getNo_of_transaction() {
	return no_of_transaction;
}

public void setNo_of_transaction(Integer no_of_transaction) {
	this.no_of_transaction = no_of_transaction;
}

public Integer getSuccess() {
	return success;
}

public void setSuccess(Integer success) {
	this.success = success;
}

public Integer getFail() {
	return fail;
}

public void setFail(Integer fail) {
	this.fail = fail;
}

public String getReferance() {
	return referance;
}

public void setReferance(String referance) {
	this.referance = referance;
}

public Integer getAmount() {
	return amount;
}

public void setAmount(Integer amount) {
	this.amount = amount;
}

@OneToMany(fetch=FetchType.LAZY,mappedBy="fileHeader",cascade=CascadeType.ALL)
private List<FileData> FileData;

@Temporal(TemporalType.DATE)
@Column(name = "UPLOAD_DATE")
private Date uploadDate;

@Temporal(TemporalType.TIME)
@Column(name = "UPLOAD_TIME")
private Date uploadTime;

public Date getUploadTime() {
	return uploadTime;
}

public void setUploadTime(Date uploadTime) {
	this.uploadTime = uploadTime;
}

public Date getCheckerTime() {
	return checkerTime;
}

public void setCheckerTime(Date checkerTime) {
	this.checkerTime = checkerTime;
}

@Column(name = "FILE_NAME")
private String fileName;

@Column(name = "RECORD_COUNT")
private Integer recordCount;

@Column(name = "STATUS")
private String status;

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public String getBatchId() {
	return batchId;
}

public void setBatchId(String batchId) {
	this.batchId = batchId;
}

@Column(name = "COMMENTS")
private String comments;


@Temporal(TemporalType.DATE)
@Column(name ="CHECKER_DATE")
private Date checkerDate;

@Temporal(TemporalType.TIME)
@Column(name ="CHECKER_TIME")
private Date checkerTime;

@Column(name ="CHECKER_ID")	
private String checkerId;



public Date getCheckerDate() {
	return checkerDate;
}

public void setCheckerDate(Date checkerDate) {
	this.checkerDate = checkerDate;
}

public String getCheckerId() {
	return checkerId;
}

public void setCheckerId(String checkerId) {
	this.checkerId = checkerId;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public List<FileData> getFileData() {
	return FileData;
}

public void setFileData(List<FileData> fileData) {
	FileData = fileData;
}

public Date getUploadDate() {
	return uploadDate;
}

public void setUploadDate(Date uploadDate) {
	this.uploadDate = uploadDate;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public Integer getFileId() {
	return fileId;
}

@Override
public String toString() {
	return "FileHeader [fileId=" + fileId + ", batchId=" + batchId + ", no_of_transaction=" + no_of_transaction
			+ ", success=" + success + ", fail=" + fail + ", referance=" + referance + ", amount=" + amount
			+ ", uploadDate=" + uploadDate + ", uploadTime=" + uploadTime + ", fileName=" + fileName + ", recordCount="
			+ recordCount + ", status=" + status + ", comments=" + comments + ", checkerDate=" + checkerDate
			+ ", checkerTime=" + checkerTime + ", checkerId=" + checkerId + "]";
}

public void setFileId(Integer fileId) {
	this.fileId = fileId;
}


public Integer getRecordCount() {
	return recordCount;
}

public void setRecordCount(Integer recordCount) {
	this.recordCount = recordCount;
}

}