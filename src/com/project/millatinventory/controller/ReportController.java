package com.project.millatinventory.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;
import com.project.millatinventory.common.Properties;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Deal;
import com.project.millatinventory.model.Dormancy;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Portfolio;
import com.project.millatinventory.model.Report;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.DataEntryService;
import com.project.millatinventory.service.ReportService;
import com.project.millatinventory.service.VehicleService;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	private DataEntryService dataEntryService;
	@Autowired
	private ReportService reportservice;
	@Autowired
	private Properties properties;
	
	@Autowired VehicleService  vehicleService;

	/*@RequestMapping(value = "/reportList")
	public String showReport(ModelMap  modelMap) throws ParseException {
		logger.info("ReportController.showReport()");		
		return "reportList";
	}*/
//	@RequestMapping(value = "/yearlyReport")
//	public String showYearlyReport(ModelMap  modelMap) throws ParseException {
//		logger.info("ReportController.showReport()");
//		List<List<Map<Object, Object>>> canvasjsDataList = reportService.getYearlyData();
//		modelMap.addAttribute("dataPointsList", canvasjsDataList);		
//		return "yearlyReport";
//	}
//	
//	@RequestMapping(value = "/monthlyReport")
//	public String showMonthlyReport(ModelMap  modelMap) throws ParseException {
//		logger.info("ReportController.showReport()");
//		List<List<Map<Object, Object>>> canvasjsDataList = reportService.getMonthlyData();
//		modelMap.addAttribute("dataPointsList", canvasjsDataList);		
//		return "monthlyReport";
//	}
	
	@Autowired
	private ReportService reportService;
	
	@Autowired private CommonService commonService;

	@RequestMapping(value = "/viewReport", method = RequestMethod.POST)
	public String dealReport(@RequestParam(value = "reportTyp	e", defaultValue = "Y") String reportType, ModelMap modelMap,
			HttpServletResponse response) throws ParseException, FileNotFoundException, IOException, DocumentException {
		logger.info(reportType);
		logger.info("ReportController.dealReport()");
		logger.info("refNo=" + reportType);
		String returnVal="";
		try {
			if (null != reportType && "Y".equalsIgnoreCase(reportType)) {
				List<List<Map<Object, Object>>> canvasjsDataList = reportService.getYearlyData();
				modelMap.addAttribute("dataPointsList", canvasjsDataList);		
				returnVal= "yearlyReport";			
			} else if (null != reportType && "M".equalsIgnoreCase(reportType)) {
				List<List<Map<Object, Object>>> canvasjsDataList = reportService.getMonthlyData();
				modelMap.addAttribute("dataPointsList", canvasjsDataList);		
				returnVal= "monthlyReport";
			}
			else {
				modelMap.addAttribute("message", "Invalid View Type");
			}
		} catch (Exception e) {
			logger.error("Report generation fails");
			logger.error(e);
			modelMap.addAttribute("message", "View Report fails");
			returnVal= "reportList";
		}
		// final File file = new File("C:\\FirstPdf10.pdf");
		/*
		 * InputStream inputStream=null; OutputStream outStream=null; try {
		 * 
		 * final int BUFFER_SIZE = 4096; inputStream = new
		 * ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(file)));
		 * 
		 * // set headers for the response String mimeType = "application/pdf";
		 * response.setContentType(mimeType); String headerKey =
		 * "Content-Disposition"; String headerValue = String.format(
		 * "attachment; filename=" + refNo + ".pdf");
		 * response.setHeader(headerKey, headerValue);
		 * 
		 * // get output stream of the response outStream =
		 * response.getOutputStream();
		 * 
		 * byte[] buffer = new byte[BUFFER_SIZE]; int bytesRead = -1;
		 * 
		 * // write bytes read from the input stream into the output stream
		 * while ((bytesRead = inputStream.read(buffer)) != -1) {
		 * outStream.write(buffer, 0, bytesRead); } } finally {
		 * inputStream.close(); outStream.close(); }
		 */
		return returnVal;
	}
	@RequestMapping(value = "/reportList", method = RequestMethod.GET)
	public String reportList(@ModelAttribute("rplist")DataEntry dentry, Model model ) {
		//List<DataEntry> list=reportService.GetDataEntry();
		//model.addAttribute("dataEntryList", list);
		model.addAttribute("vendor", getVendor());
		setValues(model);
		List<Vehicle> vlist=reportservice.GetVehicles();
		model.addAttribute("vehicle", vlist);
		
		model.addAttribute(new SearchBean());
		return "reportList";
	}
	@RequestMapping(value = "/viewDataEntryReport/{DataEntryId}", method = RequestMethod.GET)
	public ModelAndView viewDataEntryReport(@PathVariable("DataEntryId") Integer DataEntryId,ModelMap map) {
		logger.info("viewUser"+DataEntryId);
		System.out.println("UserController.viewUser()");
		DataEntry dataEntryById = dataEntryService.getDataEntryById(DataEntryId);
		List<DataEntryExpense> expenseList = dataEntryService.getExpenseByDataEntryId(DataEntryId);
		ModelAndView mv = new ModelAndView("viewReport");
		System.out.println("150");
		mv.addObject(dataEntryById);
		mv.addObject("expenseList", expenseList);
		logger.info("Return dataEntryList"+dataEntryById);
		return mv;
	}
	
	
	
	private void setValues(Model model) {
		
		List<Sites> slist=reportservice.GetSite();
		
		model.addAttribute("motor", getallmoter());
		model.addAttribute("site", slist);
		
		model.addAttribute("vendor", getVendor());
	}
	
	private List<Moter> getallmoter() {
		return commonService.getmoter();
	
	}
	private List<Vendors> getVendor(){
		return commonService.getAllvendors();
	}
	@RequestMapping(value = "/portfolioReport", method = RequestMethod.POST)
	public String portfolioReport(Model model)
			throws ParseException, FileNotFoundException, MalformedURLException, DocumentException, IOException {
		logger.info("ReportController.portfolioReport()");
		logger.info("ReportController.showReport()");
		List<Portfolio> portfolios = reportService.getPortfolioDetails();
		logger.info("portfolio list=" + portfolios.size());
		String dir = null;//properties.getFileLocation() + File.separator + "portfolio";
		File file = new File(dir);
		if (!file.isDirectory())
			file.mkdirs();

		for (Portfolio portfolio : portfolios) {

			logger.info("Report generating for " + portfolio.getClientName() + " " + dir);
			logger.info(portfolio);
			reportService.generatePortfolioReport(portfolio,
					file + File.separator + portfolio.getClientName() + ".pdf");
			logger.info("Report generated for " + portfolio.getClientName());
		}
		logger.info("Portfolio Report generated successfully at " + File.separator
				+ "portfolio");
		model.addAttribute("message", "Portfolio Report generated successfully at " 
				+ File.separator + "portfolio");

		return "report";
	}

	@RequestMapping(value = "/dormancyReport", method = RequestMethod.POST)
	public String dormancyReport(@RequestParam("option") String option, Model model)
			throws ParseException, FileNotFoundException, MalformedURLException, DocumentException, IOException {
		logger.info("ReportController.dormancyReport()");
		try {
			logger.info("ReportController.dormancyReport()" + option);
			if ("1".equals(option)) {
				List<Dormancy> dormancyList = reportService.getDormancyLetterDetails();

				logger.info("portfolio list=" + dormancyList.size());
				String dir = null;//properties.getFileLocation() + File.separator + "dormancy";
				File file = new File(dir);
				if (!file.isDirectory())
					file.mkdirs();

				for (Dormancy dormancy : dormancyList) {
					// logger.info("Report generating for
					// "+dormancy.getClientName()+" "+dir);
					logger.info(dormancy);
					String fileName = file + File.separator + "InactiveAccount_" + dormancy.getAccountNumber() + ".pdf";
					reportService.generateDormancyReport(dormancy, fileName);
					// logger.info("Report generated for
					// "+dormancy.getClientName());
				}
				logger.info("Dormant Report generated successfully at "  + File.separator
						+ "portfolio");
				model.addAttribute("message", "Dormancy Report generated successfully at "
						 + File.separator + "dormancy");
			}
		} catch (Exception e) {
			logger.error("Report generation fails");
			logger.error(e);
			model.addAttribute("message", "Dormancy Report generation fails");
		}
		return "report";
	}
	@RequestMapping(value ="/searchReport", method=RequestMethod.POST)
	public String searchReport(SearchBean searchBean,Model model) throws ParseException {
		logger.info(searchBean);
		List<DataEntry> list= reportservice.searchTrip(searchBean);
		model.addAttribute("dataEntryList", list);
		if(list.isEmpty()) {
		model.addAttribute("message", "No Data Found!");
		}
		setValues(model);
		List<Vehicle> vlist=null;
		if(0==searchBean.getVendorType())
			vlist=reportservice.GetVehicles();
	
		else {
			vlist= vehicleService.getVehiclesByVehicleType(searchBean.getVehicleType(),searchBean.getVendorType());
		}
		model.addAttribute("vehicle", vlist);
		
		return "reportList";
	}
	
}
