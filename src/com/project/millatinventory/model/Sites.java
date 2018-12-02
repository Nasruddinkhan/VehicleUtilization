package com.project.millatinventory.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name ="SITES")

public class Sites extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="siteId")
	private int siteId;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name ="siteName")
	private String siteName;
	
	@Column(name ="siteLocation")
	private String siteLocation;
	
	@Column(name ="siteManager")
	private String siteManager;
	
	@Column(name ="siteSupervisor")
	private String siteSupervisor;
	
	@Column(name = "COMMENTS")
	private String comments;

	@Transient
	private String imageFile;

	@Column(name = "File_Name")
	private String fileName;
	
	@Column(name = "Construction_type")
	private String construction_type;
	
	@Column(name = "Pro_Start_Date")
	private String pro_Start_Date;
	
	public String getPro_Start_Date() {
		return pro_Start_Date;
	}

	public void setPro_Start_Date(String pro_Start_Date) {
		this.pro_Start_Date = pro_Start_Date;
	}

	public String getPro_End_Date() {
		return pro_End_Date;
	}

	public void setPro_End_Date(String pro_End_Date) {
		this.pro_End_Date = pro_End_Date;
	}

	@Column(name = "Pro_End_Date")
	private String pro_End_Date;
	
	@Column(name = "Start_Date")
	private String start_Date ;
	
	@Column(name = "End_Date")
	private String end_Date ;
	
	@Column(name = "Stage")
	private String stage ;
	
	
	
	@Override
	public String toString() {
		return "Sites [siteId=" + siteId + ", status=" + status + ", siteName=" + siteName + ", siteLocation="
				+ siteLocation + ", siteManager=" + siteManager + ", siteSupervisor=" + siteSupervisor + ", comments="
				+ comments + ", imageFile=" + imageFile + ", fileName=" + fileName + ", construction_type="
				+ construction_type + ", pro_Start_Date=" + pro_Start_Date + ", pro_End_Date=" + pro_End_Date
				+ ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", stage=" + stage + ", file="
				+ Arrays.toString(file) + ", image=" + Arrays.toString(image) + "]";
	}

	public String getConstruction_type() {
		return construction_type;
	}

	public void setConstruction_type(String construction_type) {
		this.construction_type = construction_type;
	}

	public String getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
	}

	public String getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Lob
	@Column(name = "files", columnDefinition = "blob")
	private byte[] file;
	
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getImageFile() {
		
		return imageFile;
	}
	
	
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String string) {
		this.fileName = string;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteLocation() {
		return siteLocation;
	}

	public void setSiteLocation(String siteLocation) {
		this.siteLocation = siteLocation;
	}

	public String getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(String siteManager) {
		this.siteManager = siteManager;
	}

	public String getSiteSupervisor() {
		return siteSupervisor;
	}

	public void setSiteSupervisor(String siteSupervisor) {
		this.siteSupervisor = siteSupervisor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
