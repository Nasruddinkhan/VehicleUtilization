package com.project.millatinventory.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.millatinventory.model.Student;
import com.project.millatinventory.model.StudentSearch;
import com.project.millatinventory.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	Logger logger = Logger.getLogger(ReportController.class);
	@Autowired
	private StudentService studentService;
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String getAllUsers(Model model) throws ParseException {
		System.out.println("StudentController.getAllStudent()");
		List<Object[]> student = studentService.getStudent(new StudentSearch());
		logger.info("Student list=" + student.size());
		System.out.println(student);
		model.addAttribute("student", student);
		model.addAttribute(new StudentSearch());
		logger.info("return to student page ");
		return "student";
	}
	
	@RequestMapping(value = "/Searchstudents", method = RequestMethod.POST)
	public String searchUsers(@ModelAttribute("studentSearch") StudentSearch studentSearch,BindingResult results, Model model) throws ParseException {
		System.out.println("StudentController.getAllStudent()");
		List<Object[]> student = studentService.getStudent(studentSearch);
		logger.info("Student search list=" + student.size());
		System.out.println(student);
		model.addAttribute("student", student);
		logger.info("return to student search result page ");
		return "student";
	}
}
