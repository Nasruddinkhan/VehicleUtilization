package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VENDORS")
public class Vendors extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="VendorId")
	private int vendorId;
	
	@Column(name="Vendor_Name")
	private String vendor_Name;
	
	@Column(name="Vendor_Contact_no")
	private String vendor_Contact_no;
	
	@Column(name="Address")
	private String address;

	@Column(name="status")
	private String status;
	
	@Column(name="Vlocation")
	private String vlocation;
	
	public String getVlocation() {
		return vlocation;
	}

	public void setVlocation(String vlocation) {
		this.vlocation = vlocation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendor_Name() {
		return vendor_Name;
	}

	public void setVendor_Name(String vendor_Name) {
		this.vendor_Name = vendor_Name;
	}

	public String getVendor_Contact_no() {
		return vendor_Contact_no;
	}

	public void setVendor_Contact_no(String vendor_Contact_no) {
		this.vendor_Contact_no = vendor_Contact_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	
	}

	@Override
	public String toString() {
		return "Vendors [vendorId=" + vendorId + ", vendor_Name=" + vendor_Name + ", vendor_Contact_no="
				+ vendor_Contact_no + ", address=" + address + ", status=" + status + ", vlocation=" + vlocation
				+ ", getVlocation()=" + getVlocation() + ", getVendorId()=" + getVendorId() + ", getVendor_Name()="
				+ getVendor_Name() + ", getVendor_Contact_no()=" + getVendor_Contact_no() + ", getAddress()="
				+ getAddress() + ", getStatus()=" + getStatus() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getModifiedBy()=" + getModifiedBy() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
