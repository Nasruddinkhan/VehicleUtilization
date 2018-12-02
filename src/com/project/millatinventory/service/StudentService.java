package com.project.millatinventory.service;

import java.text.ParseException;
import java.util.List;

import com.project.millatinventory.model.Student;
import com.project.millatinventory.model.StudentSearch;


public interface StudentService {
	public List<Object[]> getStudent(StudentSearch studentSearch) throws ParseException;
}
