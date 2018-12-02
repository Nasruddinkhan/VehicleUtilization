package com.project.millatinventory.controller;

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

import com.google.gson.Gson;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.DataEntryService;
import com.project.millatinventory.service.VehicleService;

@Controller
@RequestMapping(value = "/vehicle")
public class VehiclesController {
	
	private static final Logger logger = LoggerFactory.getLogger(VehiclesController.class);
	
	@Autowired VehicleService  vehicleService;
	@Autowired CommonService commonService;
	@Autowired
	private DataEntryService dataEntryService;

	@RequestMapping(value = "/showAddVehicle", method = RequestMethod.GET)
	public ModelAndView showAddVehicle(@ModelAttribute("vehicle") Vehicle vehicle,BindingResult result) {
		ModelAndView mv = new ModelAndView("addVehicle");
		mv.addObject("moter", getallmoter());
		mv.addObject("vendor", getallVendor());
		return mv;
	}
	private List<Moter> getallmoter() {
		return commonService.getmoter();
	
	}
	private List<Vendors> getallVendor() {
		return commonService.getVendor();
	
	}
	
	@RequestMapping(value = "/checkVehiclesNumber", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody String checkVehiclesNumber(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkVehiclesNumber AJAX");
		
		logger.info(request.getParameter("vehiclesNumber"));
		logger.info(request.getParameter("vehicleId"));
		String vehiclesNumber = request.getParameter("vehiclesNumber");
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		Vehicle V = new Vehicle();
		V.setVehicleId(vehicleId);
		V.setVehiclesNumber(vehiclesNumber);
		Vehicle vehicleById = vehicleService.getVehicleCriteria(V);
		logger.info("Vehicle "+vehicleById);
		if (null != vehicleById)
			return "Vehicle Number is already exist";
		else
			return "";
	}
	
	@RequestMapping(value = "/saveVehicle", method = RequestMethod.POST)
	public String saveVehicle(HttpServletRequest req,Model model,@ModelAttribute("vehicle")  Vehicle vehicle, BindingResult result) {
		logger.info("vehicle "+vehicle);
		logger.info("vehicle.getVehicleId() "+vehicle.getVehicleId());
		if (vehicle.getVehicleId() > 0 ) {
			model.addAttribute("message", "Vehicle updated successfully");
			logger.info("Update");
			vehicleService.updateVehicle(vehicle);
		    } 
		else {
			
			model.addAttribute("message", "Vehicle added successfully");
			logger.info("ADD");
		}
		vehicleService.saveVehicle(vehicle);
		return "forward:/vehicle/vehicleslist.do";
	}
	
	@RequestMapping(value = "/vehicleslist", method = {RequestMethod.GET,RequestMethod.POST})
	public String getAllVehicles(Model model) {
		
		logger.info("UserController.getAllCustomers()");
		List<Vehicle> vehicle = vehicleService.getVehicle();
		model.addAttribute("vehicleslist", vehicle);
		logger.info(""+vehicle);
		return "vehicleslist";
	}
	/*@RequestMapping(value = "/vehicleslist", method = RequestMethod.POST)
	public String getVehicles(Model model) {
		System.out.println(model.asMap());
		logger.info("vehicleslist", model.asMap());
		System.out.println("SitesController.getAllVehicles()");
		List<Vehicle> vehicle = vehicleService.getVehicle();
		model.addAttribute("vehicleslist", vehicle);
		logger.info("show vehicles list"+vehicle.size());
		return "vehicleslist";
	}*/
	
	
	@RequestMapping(value = "/getVehicleByVehicleType/{vehicleType}/{vendorId}", method = RequestMethod.GET)
	@ResponseBody
	public String getVehicleByVehicleType(@PathVariable("vehicleType") Integer vehicleType,@PathVariable("vendorId") Integer vendorId) {
		System.out.println("getVehicleByVehicleType");
		logger.info("viewVehicle"+vehicleType);
		List<Vehicle> vehiclesByVehicleType = vehicleService.getVehiclesByVehicleType(vehicleType,vendorId);		
		logger.info("Ajax vehiclesByVehicleType");
		Gson gson = new Gson();
		return gson.toJson(vehiclesByVehicleType);
	}
	
	@RequestMapping(value = "/getExcavatorTypeByVendorAndVehicleType/{vehicleType}/{vendorId}", method = RequestMethod.GET)
	@ResponseBody
	public String getExcavatorTypeByVendorAndVehicleType(@PathVariable("vehicleType") Integer vehicleType,@PathVariable("vendorId") Integer vendorId) {
		System.out.println("getVehicleByVehicleType");
		logger.info("viewVehicle"+vehicleType);
		List<String> excavatorTypeByVendorAndVehicleType = vehicleService.getExcavatorTypeByVendorAndVehicleType(vendorId, vehicleType);		
		logger.info("Ajax vehiclesByVehicleType");
		Gson gson = new Gson();
		return gson.toJson(excavatorTypeByVendorAndVehicleType);
	}
	
	@RequestMapping(value = "/getVehicleTypeByVendor/{vendorId}", method = RequestMethod.GET)
	@ResponseBody
	public String getVehicleTypeByVendor(@PathVariable("vendorId") Integer vendorId) {
		System.out.println("getVehicleTypeByVendor");
		logger.info("viewVehicle"+vendorId);
		Vendors vendor =  new Vendors();
		vendor.setVendorId(vendorId);
		List<Moter> getVehicleTypeByVendor = vehicleService.getVehicleTypeByVendor(vendor);		
		logger.info("Ajax getVehicleTypeByVendor");
		Gson gson = new Gson();
		return gson.toJson(getVehicleTypeByVendor);
	}
	
	@RequestMapping(value = "/viewVehicle/{vehicleId}", method = RequestMethod.GET)
	public ModelAndView viewVehicle(@PathVariable("vehicleId") Integer vehicleId,
			ModelMap map) {
		logger.info("viewVehicle"+vehicleId);
		Vehicle vehicleById = vehicleService.getVehicleById(vehicleId);
		ModelAndView mv = new ModelAndView("viewVehicle");
		mv.addObject(vehicleById);
		mv.addObject("vendor", getallVendor());
		logger.info("Return viewVehicle");
		return mv;
	}
	
	
	@RequestMapping(value = "/editVehicleById/{vehicleId}", method = RequestMethod.GET)
	public ModelAndView editVehicleById(@PathVariable("vehicleId") Integer vehicleId,
			ModelMap map) {
		logger.info("editVehicleById"+vehicleId);
		Vehicle vehicleById = vehicleService.getVehicleById(vehicleId);
		ModelAndView mv = new ModelAndView("addVehicle");
		mv.addObject(vehicleById);
		mv.addObject("moter", getallmoter());
		mv.addObject("vendor", getallVendor());
		logger.info("Return addVehicle");
		return mv;
	}
	@RequestMapping(value = "/deleteVehicle/{vehicleId}", method = RequestMethod.GET)
	public String deleteVehicle(Model model, @PathVariable("vehicleId") int vehicleId) {
		logger.info("deleteVehicleById", vehicleId);
		try {
			
		int deleteVehicleById = vehicleService.deleteVehicleById(vehicleId);
		if (deleteVehicleById == 0)
			model.addAttribute("message", "Vehicle not deleted successfully");
		else
			model.addAttribute("message", "Vehicle deleted successfully");
		}catch(ConstraintViolationException e) {
			model.addAttribute("Errormessage", "Vehicle can not be deleted as it has associated Entries.");
		}
		
		
		logger.info("return to Vehicle List ");
		
		return "forward:/vehicle/vehicleslist.do";
	}
	@RequestMapping(value = "/checkVehicleEntry", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody int checkVehicleEntry(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkVehiclesNumber AJAX");
		
		
		logger.info(request.getParameter("vehicleId"));
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		Vehicle V = new Vehicle();
		V.setVehicleId(vehicleId);
		List<DataEntry> dataEntryVehicleId = dataEntryService.getDataEntryByVehicleIdCriteria(V);
		logger.info("Vehicle "+dataEntryVehicleId);
		if (null != dataEntryVehicleId)
			return dataEntryVehicleId.size();
		else
			return 0;
	}
	
	
}
