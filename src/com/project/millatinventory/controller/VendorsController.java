package com.project.millatinventory.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.VendorsService;


@Controller
@RequestMapping(value="/vendor")
public class VendorsController {
	 private static final Logger logger = LoggerFactory.getLogger(VendorsController.class);
	 @Autowired
		private VendorsService vendorsService;

		@Autowired
		private CommonService commonService;
	
	@RequestMapping(value = "/showAddVendors", method = RequestMethod.GET)
	public ModelAndView showAddVendors(@ModelAttribute("vendors") Vendors vendors, BindingResult result) {
		logger.info("VendorsController.showAddVendors");
		ModelAndView mv = new ModelAndView("addVendors");
		logger.info("Hi");
		logger.info("return to Add Vendors page ");
		return mv;
	}
	
	@RequestMapping(value = "/saveVendors", method = RequestMethod.POST)
	public String saveVendor(HttpServletRequest req, Model model,@ModelAttribute("vendors") Vendors vendors, BindingResult result) {
		logger.info("in saveVendor Method()");
		logger.info("saveVendor!");
		if (null ==(Object)vendors.getVendorId() || 0 == vendors.getVendorId()) {
			model.addAttribute("message", "Vendor added successfully");
			logger.info("ADD");
		}else {
			model.addAttribute("message", "Vendor updated successfully");
			logger.info("Edit");
		}
		vendorsService.saveVendor(vendors);
		return "forward:/vendor/vendors.do";
	}
	

	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public String getAllVendors(Model model) {
		
		logger.info("VendorsController.getVendors()");
		List<Vendors> vendors = vendorsService.getVendors();
		model.addAttribute("vendorsList", vendors);
	
		return "vendorsList";
	}
	@RequestMapping(value = "/vendors", method = RequestMethod.POST)
	public String getSites(Model model) {
		
		logger.info("vendorsList", model.asMap());
		logger.info("VendorsController.getSites()");
		List<Vendors> vendors = vendorsService.getVendors();
		model.addAttribute("vendorsList", vendors);
				System.out.println("1"+vendors);
		   return "vendorsList";
	}
	
	@RequestMapping(value = "/ShowUpdateVendor/{vendorId}", method = RequestMethod.GET)
	public ModelAndView ShowUpdateVendor(@PathVariable("vendorId") Integer vendorId,
			ModelMap map) {
		logger.info("VendorController.ShowUpdateVendor()");
		Vendors VendorById = vendorsService.getVendorById(vendorId);
		ModelAndView mv = new ModelAndView("addVendors");
		
		//mv.addObject("departmentList", getSites());
		mv.addObject(VendorById);
		return mv;
	}

	@RequestMapping(value = "/deleteVendor/{vendorId}", method = RequestMethod.GET)
	public String deleteVendor(Model model, @PathVariable("vendorId") int vendorId) {
		logger.info("deleteSite", vendorId);
		logger.info("VendorController.deleteVendor()" + vendorId);
		try {
		int deleteVendoreById = vendorsService.deleteVendorById(vendorId);
		if (deleteVendoreById == 0)
			model.addAttribute("message", "Vendor not deleted successfully");
		else
			model.addAttribute("message", "Vendor deleted successfully");
		}catch(ConstraintViolationException e) {
			model.addAttribute("Errormessage", "Vendor can not be deleted as it has associated Vehicle & Entries");
		}
		logger.info("return to Vendors List ");
		
		return "forward:/vendor/vendors.do";
	}
	
	@RequestMapping(value = "/ViewVendor/{vendorId}", method = RequestMethod.GET)
	
	public ModelAndView viewVendor(@PathVariable("vendorId") Integer vendorId,
			ModelMap map ) {
		logger.info("viewVendor"+vendorId);
		logger.info("VendorController.viewVendor()");
		Vendors vendorById = vendorsService.getVendorById(vendorId);
	ModelAndView mv = new ModelAndView("ViewVendor");
		System.out.println(1);
		mv.addObject(vendorById);
		logger.info("Return ViewVendor");
		return mv;
	}
	
	@RequestMapping(value = "/checkVendor", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody String checkVendorName(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkVendorName AJAX");
		
		logger.info(request.getParameter("vendor_Name"));
		logger.info(request.getParameter("vendorId"));
		String vendor_Name = request.getParameter("vendor_Name");
		int vendorId = Integer.parseInt(request.getParameter("vendorId"));
		Vendors V = new Vendors();
		V.setVendorId(vendorId);
		V.setVendor_Name(vendor_Name);
				Vendors vehicleById = vendorsService.getVendorCriteria(V);
		logger.info("Vehicle "+vehicleById);
		if (null != vehicleById)
			return "Vendor  is already exist";
		else
			return "";
	}
}
