package com.project.millatinventory.model;

import java.io.Serializable;
import java.sql.Date;
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

@Entity
@Table(name = "Students")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "StudentId")
	private Integer studentId;

	@Column(name = "StudentName",length=20)
	private String studentName;

	@Column(name = "StudentClass",length=20)
	private String studentClass;

	@Column(name = "StudentAge")
	private Integer studentAge;

	@Column(name = "JoiningDate")
	private Date joiningDate;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	

	public Date getJoiningDate() {
		return joiningDate;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}



	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
	private List<Subject> subject;

}
