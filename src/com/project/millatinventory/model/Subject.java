package com.project.millatinventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SubjectId")
	private Integer subjectId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StudentId")
	private Student student;

	@Column(name = "SubjectName",length=20)
	private String subjectName;

	@Column(name = "Marks")
	private Integer marks;

	@Column(name = "Results")
	private Character results;

	public Integer getSubjectId() {
		return subjectId;
	}

	public Character getResults() {
		return results;
	}

	public void setResults(Character results) {
		this.results = results;
	}

	public void setSubjectId(Integer sujectId) {
		this.subjectId = sujectId;
	}

	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}


	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", student=" + student
				+ ", subjectName=" + subjectName + ", marks=" + marks
				+ ", results=" + results + "]";
	}

}
