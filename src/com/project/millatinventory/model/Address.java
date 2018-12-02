package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address implements Serializable{
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "ADDRESS_ID")
private int addressId;
@Column(name = "ADDRRESS")
private String address;
@Column(name = "STATE_ID")
private int stateId;
@Column(name = "CITY_ID")
private int cityId;


public Address(){
	
}
public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getStateId() {
	return stateId;
}
public void setStateId(int stateId) {
	this.stateId = stateId;
}
public int getCityId() {
	return cityId;
}
public void setCityId(int cityId) {
	this.cityId = cityId;
}
@Override
public String toString() {
	return "Address [address=" + address + ", stateId=" + stateId + ", cityId="
			+ cityId + "]";
}



}

