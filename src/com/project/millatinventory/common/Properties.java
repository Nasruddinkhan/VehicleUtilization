package com.project.millatinventory.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

	@Value("${rel-version}")
	private String relVersion;
	
	@Value("${rel-Date}")
	private String relDate;
	
	@Value("${dataFile.storageLocation}")
	private String storageLocation;

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getFilesqllocation() {
		return filesqllocation;
	}

	public void setFilesqllocation(String filesqllocation) {
		this.filesqllocation = filesqllocation;
	}

	@Value("${mysql.installation.location}")
	private String filesqllocation;
	
	@Value("${mysql.database.user}")
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Value("${mysql.database.password}")
	private String pass; 
	
	@Value("${mysql.database.url}")
	private String resource;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRelDate() {
		return relDate;
	}

	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}

	public String getRelVersion() {
		return relVersion;
	}

	public void setRelVersion(String relVersion) {
		this.relVersion = relVersion;
	}
}
