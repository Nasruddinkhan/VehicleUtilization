package com.project.millatinventory.dao;

import java.text.ParseException;
import java.util.List;

import com.project.millatinventory.model.Student;
import com.project.millatinventory.model.StudentSearch;

public interface StudentDao {
	

	List<Object[]> getStudent(StudentSearch studentSearch) throws ParseException;
}
