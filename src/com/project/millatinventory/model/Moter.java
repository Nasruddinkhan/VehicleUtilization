package com.project.millatinventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Moter")
public class Moter {

	@Id@GeneratedValue
@Column(name="m_Id")	
private int m_Id;

@Column(name="m_Name")
private String m_Name;

public int getM_Id() {
	return m_Id;
}
public void setM_Id(int m_Id) {
	this.m_Id = m_Id;
}
public String getM_Name() {
	return m_Name;
}
public void setM_Name(String m_Name) {
	this.m_Name = m_Name;
}
@Override
public String toString() {
	return "Moter [m_Id=" + m_Id + ", m_Name=" + m_Name + "]";
}
}
