
package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Department extends BaseBean implements Serializable{
private static final long serialVersionUID = 1L;

	@Id		
	@Column(name="DEPARTMENT_CODE")
private String departmentCode;
	
	@Column(name ="DEPARTMENT_NAME")
private String departmentName;

	
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	


}
