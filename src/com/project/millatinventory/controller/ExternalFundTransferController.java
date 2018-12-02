package com.project.millatinventory.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.util.Calendar;
import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.common.TransactionStatus;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.ExternalTransactionSearch;
import com.project.millatinventory.model.ExternalTransactionSummary;
import com.project.millatinventory.model.ExternalTransations;
import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.ExternalFundTransferService;
import com.project.millatinventory.serviceimpl.InvalidDataException;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/eft")
public class ExternalFundTransferController {
	private static final Logger logger = LoggerFactory
			.getLogger(ExternalFundTransferController.class);

	@Autowired
	private ExternalFundTransferService externalFundTransferService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(@ModelAttribute("eftSearch") ExternalTransactionSearch eftSearch,
			HttpServletRequest req, Model model) {
		logger.info("Home ");
		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		ExternalTransactionSummary summary = new ExternalTransactionSummary();
		summary.setPYM02USRU(usersFromSession.getLoginId());
		
		List<ExternalTransactionSummary> fileheaders = externalFundTransferService.getFileDetails(summary);
		model.addAttribute("fileList", fileheaders);
		model.addAttribute("purposeCodeList", getPurposeCode());
		model.addAttribute("mode", "UPLOAD");
		logger.info("return to FileTab");
		return "EFTTab";
	}
	

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void home(@RequestParam("entity") String viewType,
			HttpServletResponse response, Model model)
			throws ClassNotFoundException {
		logger.info("The client wants to view {}.", viewType);
		// downloadService.downloadXLS(response,viewType);
	}

	/*@RequestMapping(value = "/action", method = RequestMethod.GET)
	public String action(@RequestParam("action") String action,
			HttpServletResponse response, Model model)
			throws ClassNotFoundException {
		logger.info("The client wants to view {}.", action);
		if ("1".equals(action))
			return "fileUpload";
		else {
			return "redirect:/upload/fileUploadHistory.do";
		}
	}*/

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file,
			Model model, HttpServletRequest req) throws IOException {
		try {
			Users usersFromSession = (Users) req.getSession().getAttribute(
					"User");

			logger.info("Loading File...");
			if (!file.isEmpty()) {
				ValidateUpload.validateOfficeData(file);
				externalFundTransferService.saveContents(file, usersFromSession);
				logger.info("Upload successful!");
				// System.out.println("Upload successful!");
			}
			ExternalTransactionSummary fileHeader = new ExternalTransactionSummary();
			fileHeader.setPYM02USRU(usersFromSession.getLoginId());
			List<ExternalTransactionSummary> fileheaders = externalFundTransferService
					.getFileDetails(fileHeader);
			logger.info("fetching...");
			model.addAttribute("fileList", fileheaders);

			model.addAttribute("successMessage", "File uploaded successfully");
			logger.info("Upload successful!");
		} catch (InvalidDataException e) {

			model.addAttribute("Messages", e.getList());
			logger.error("Upload failed!" + e);
		} catch (Exception e) {
			model.addAttribute("Message", e);
			logger.error("Upload failed!" + e);

		}
		model.addAttribute("eftSearch", new ExternalTransactionSearch());
		return "EFTTab";
	}

/*	@RequestMapping(value = "/fileUploadHistory", method = RequestMethod.POST)
	public ModelAndView 
UploadHistory() {
		System.out.println("CustomerControllert.fileUploadHistory()");
		FileHeader fileHeader = new FileHeader();
		// fileHeader.setCreatedBy(usersFromSession.getCreatedBy());
		List<FileHeader> fileheaders = uploadService.getFileDetails(fileHeader);
		ModelAndView m = new ModelAndView("fileList");
		m.addObject("fileList", fileheaders);
		return m;
	}

	@RequestMapping(value = "/fileUploadHistory", method = RequestMethod.GET)
	public ModelAndView fileUploadHistory1() {
		System.out.println("CustomerControllert.fileUploadHistory()");
		FileHeader fileHeader = new FileHeader();
		List<FileHeader> fileheaders = uploadService.getFileDetails(fileHeader);
		ModelAndView m = new ModelAndView("fileTab");
		m.addObject("fileList", fileheaders);
		return m;
	}*/

	@RequestMapping(value = "/fileUploadData/{batchId}", method = RequestMethod.GET)
	public ModelAndView fileUploadData(@PathVariable("batchId") String batchId) {
		logger.info("Loading file data for batch id=" + batchId);
		System.out.println("CustomerControllert.fileUploadHistory()");
		List<ExternalTransations> fileData = externalFundTransferService.getFileTransactions(batchId);
		ModelAndView m = new ModelAndView("EFTTransferDetails");
		m.addObject("fileData", fileData);
		logger.info("returning to page fileData");
		return m;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(
			@ModelAttribute("eftSearch") ExternalTransactionSearch eftSearch,
			HttpServletRequest req, Model model) throws ParseException {
		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		logger.info("Searching files..");
		List<ExternalTransactionSummary> fileheaders;

		eftSearch.setCreatedBy(usersFromSession.getLoginId());
		fileheaders = externalFundTransferService.getFileDetails(eftSearch);	
		model.addAttribute("fileList", fileheaders);
		model.addAttribute("purposeCodeList", getPurposeCode());
		model.addAttribute("mode", "SEARCH");
		logger.info("Search done");
		
		return "EFTTab";
	}

	private Map getPurposeCode() {
		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("01", "01");
		map.put("02", "02");
		map.put("03", "03");
		map.put("04", "04");
		map.put("05", "05");
		map.put("06", "06");
		map.put("07", "07");
		map.put("08", "08");
		map.put("09", "09");
		map.put("10", "10");
		map.put("11", "11");
		map.put("12", "12");
		return map;
	}

/*	@RequestMapping(value = "/fileDataUpdate/{fileDataId}", method = RequestMethod.GET)
	public ModelAndView fileDataUpdatePage(
			@PathVariable("fileDataId") Long fileDataId) {
		logger.info("Loading file data for id=" + fileDataId);
		
		FileData fileData =  externalFundTransferService.getFileDataById(fileDataId);
		ModelAndView m = new ModelAndView("fileDataUpdate");
		m.addObject("fileData", fileData);
		m.addObject("months", getMonths());
		m.addObject("currencies", getCurrencies());
		logger.info("returning to page fileDataUpdate");
		return m;
	}*/

	/*@RequestMapping(value = "/updateFileRecord", method = RequestMethod.POST)
	public String updateFileRecord(
			@ModelAttribute("fileData") FileData fileData,
			BindingResult result, Model model) {
		logger.info("updateFileRecord!" + fileData.getId());
		
		Integer count = uploadService.updateFileRecord(fileData);
		if (count > 0)
			model.addAttribute("message", "Recored updated successfully");
		else
			model.addAttribute("message", "Recored count updated successfully");

		logger.info("updated Record!" + fileData.getId());
		return "redirect:/upload/fileUploadData/"
				+ fileData.getFileHeader().getFileId() + ".do";
	}
*/
	@RequestMapping(value = "/submitFile", method = RequestMethod.POST)
	public String submitFile(
			@ModelAttribute("eftSearch") ExternalTransactionSearch eftSearch,
			HttpServletRequest req, Model model) {
		logger.info("submitFile for approval");

		int count = 0;
		String[] selectedIds = req.getParameterValues("batchIds");
		if (null != selectedIds) {
	/*	String[] intarray = new String[selectedIds.length];
		int i = 0;
		for (String str : selectedIds) {
			intarray[i] = Integer.parseInt(str);// Exception in this line
			i++;
		}*/
		logger.info("Selected Ids=" + selectedIds);
		count = externalFundTransferService.updateStatus(selectedIds,
				ApplicationConstants.EFT_TXN_STATUS.INITIATED,null);
		if (count > 0){
			model.addAttribute("message", count
					+ " Recored(s) submitted successfully");
		}
		else{	model.addAttribute("message",  " Recored(s) could not submitted successfully");
		}
			/*
		 * else model.addAttribute("message",
		 * "Recored count updated successfully");
		 */
		ExternalTransactionSummary fileHeader = new ExternalTransactionSummary();
		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		fileHeader.setPYM02USRU(usersFromSession.getLoginId());
		
		List<ExternalTransactionSummary> fileheaders = externalFundTransferService.getFileDetails(fileHeader);
		model.addAttribute("fileList", fileheaders);
		model.addAttribute("purposeCodeList", getPurposeCode());
		model.addAttribute("mode", "SEARCH");
	}
		logger.info("File submitted for approval");
		return "EFTTab";
	}

	private Map getCurrencies() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("INR", "INR");
		return map;
	}

	@RequestMapping(value = "/approveRejectFile", method = RequestMethod.POST)
	public String approveRejectFile(			
			HttpServletRequest req, Model model) {
		logger.info("submitFile for approval");

		int count = 0;
		String action = req.getParameter("action");
		String comments = req.getParameter("comments");
		comments =null != comments ? comments:"";
		
		logger.info("Action="+action);
		String[] selectedIds = req.getParameterValues("batchIds");
		if (null != selectedIds) {
			/*Integer[] intarray = new Integer[selectedIds.length];
			int i = 0;
			for (String str : selectedIds) {
				intarray[i] = Integer.parseInt(str);// Exception in this line
				i++;
			}*/
			logger.info("Selected Ids=" + selectedIds);
			ExternalTransactionSummary fileHeader= new ExternalTransactionSummary();
			Users usersFromSession = (Users) req.getSession().getAttribute(
					"User");			
			 Calendar instance = Calendar.getInstance();
	         instance.setTime(new java.util.Date());
	         fileHeader.setPYM02DATP(new Date(instance.getTimeInMillis()));	         		
			fileHeader.setPYM02USRP(usersFromSession.getLoginId());
			fileHeader.setPYM02REM(comments);
			count = externalFundTransferService.updateStatus(selectedIds,action,fileHeader);
			
			if (count > 0) {
				model.addAttribute("message", count
						+ " Record "+getStatusName(action)+" successfully");
			}
		}else{
			model.addAttribute("message", count
					+ " Record "+getStatusName(action)+" successfully");
		}
		logger.info("File submitted for approval");
		return "redirect:/eft/getAllTransaction.do";
	}
	private String getStatusName(String action) {
		TransactionStatus[] values = TransactionStatus.values();
		for (TransactionStatus transactionStatus : values) {
		if((transactionStatus.toString().equals(action)))	
			return (transactionStatus.getStatus());
		}
		return null;
	}

	@RequestMapping(value = "/checkFileName", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody String checkFileName(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkFileName AJAX");
		String fileName = request.getParameter("fileName");
		logger.info(fileName);		
		
		ExternalTransactionSummary fileHeader = new ExternalTransactionSummary();
		fileHeader.setPYM02FIL(fileName);
		List<ExternalTransactionSummary> fileDetails = externalFundTransferService.getFileDetails(fileHeader);
		logger.info("File List  "+fileDetails.size());
		if (null != fileDetails && fileDetails.size()>0)
			return "File Name is already exist";
		else
			return "";
	}	
	@RequestMapping(value = "/getAllTransaction", method = RequestMethod.GET)
	public ModelAndView getAllTransaction(	HttpServletRequest req)
			throws UnsupportedEncodingException {
		logger.info("EFT Controller.getAllTransaction()");

		
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		ExternalTransactionSummary summary= new ExternalTransactionSummary();
		summary.setPYM02STS(ApplicationConstants.EFT_TXN_STATUS.INITIATED);
		List<ExternalTransactionSummary> eftInitiated = externalFundTransferService.getFileDetails(summary);
		summary.setPYM02STS(ApplicationConstants.EFT_TXN_STATUS.APPROVED);
		List<ExternalTransactionSummary> eftVerified = externalFundTransferService.getFileDetails(summary);
		
		
		ModelAndView mv = new ModelAndView("EFTCheckerTab");
		mv.addObject("eftInitiated", eftInitiated);			
		mv.addObject("eftVerified", eftVerified);
		/*
		 * System.out.println(detailInsert.getSignatureFile());
		 * System.out.println(detailInsert.getSignature());
		 */
		
		logger.info("returning for EFT Approval");
		return mv;
	}
	/*
	 * @RequestMapping(value = "/pendingApproval", method = RequestMethod.GET)
	 * public String pendingApproval(Model model,HttpServletRequest req) {
	 * logger.info("pendingApproval "); Users usersFromSession = (Users)
	 * req.getSession().getAttribute("User"); FileHeader fileHeader = new
	 * FileHeader(); //fileHeader.setCreatedBy(usersFromSession.getLoginId());
	 * //System.out.println(fileHeader.getCreatedBy()); List<FileHeader>
	 * fileheaders = uploadService.getFileDetails(fileHeader);
	 * model.addAttribute("fileList", fileheaders);
	 * //model.addAttribute("months", getMonths()); //model.addAttribute("mode",
	 * "UPLOAD"); logger.info("return to FileTab"); return "fileTab"; }
	 */
	/*
	 * @InitBinder public void dataBinding(WebDataBinder binder) {
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 * dateFormat.setLenient(false); binder.registerCustomEditor(Date.class,
	 * "fromDate", new CustomDateEditor(dateFormat, true));
	 * binder.registerCustomEditor(Date.class, "ToDate", new
	 * CustomDateEditor(dateFormat, true)); }
	 */
	
	
}