package com.project.millatinventory.serviceimpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.StudentDao;
import com.project.millatinventory.dao.UserDao;
import com.project.millatinventory.model.Student;
import com.project.millatinventory.model.StudentSearch;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.StudentService;
import com.project.millatinventory.service.UserService;
@Service("StudentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Object[]> getStudent(StudentSearch studentSearch) throws ParseException {	
		return studentDao.getStudent(studentSearch);
	}
	
	
}

