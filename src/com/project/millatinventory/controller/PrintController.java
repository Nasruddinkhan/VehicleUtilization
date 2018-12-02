package com.project.millatinventory.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;
import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.common.CommonUtil;
import com.project.millatinventory.common.PDFService;
import com.project.millatinventory.common.Properties;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.PrintService;

@Controller
@RequestMapping(value = "/print")
public class PrintController {

	private static final Logger logger = LoggerFactory.getLogger(PrintController.class);
	@Autowired
	private PrintService printService;

	@Autowired
	private Properties properties;

	@RequestMapping(value = "/newApp", method = RequestMethod.GET)
	public String showPage(HttpServletRequest req, Model model) {
		logger.info("showPage!");
		//load data for status
		DetailInsert detailInsert = new DetailInsert();
		
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		
		detailInsert.setCreatedBy(usersFromSession.getLoginId());
		List<DetailInsert> applicants = printService.getApplicants(detailInsert);
		
		model.addAttribute("applicantList", applicants);
		return "printTab";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchApplicant(@RequestParam("id") String id, @RequestParam("type") String type,Model model,HttpServletRequest req)
			throws IllegalAccessException, InvocationTargetException {
		logger.info("searchApplicant!" + id);
		model.addAttribute("id",id);

		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		DetailLookup detailLookup = printService.getApplicantById(id);

		DetailInsert detailInsert = new DetailInsert();
		if (null != detailLookup) {
			logger.info("Record found:" + detailLookup.getApplicantID());
			BeanUtils.copyProperties(detailInsert, detailLookup);
			System.out.println(detailInsert.getSignature());
			String base64Encoded = byteToBase64(detailInsert.getSignature());
			System.out.println(base64Encoded);
			
			detailInsert.setSignatureFile(base64Encoded);
			detailInsert.setApplicantType(type);
			logger.info("detailInsert=" + detailInsert);
			model.addAttribute(detailInsert);
			
			model.addAttribute("accountTypeList", printService.getAccountTypeByAppId(detailInsert.getApplicantID()));
		}
		logger.info("returning to applicantSearch");
		return "applicantSearch";
	}

	@RequestMapping(value = "/viewApplicant/{id}", method = RequestMethod.GET)
	public String viewApplicant(@PathVariable("id") String id,Model model)
			throws IllegalAccessException, InvocationTargetException {
		logger.info("searchApplicant!" + id);
		
		model.addAttribute("modify","Y");
		DetailInsert detailInsert = printService.getSavedApplicantById(id);

		
		if (null != detailInsert) {
			logger.info("Record found:" + detailInsert.getApplicantID());
		
			String base64Encoded = byteToBase64(detailInsert.getSignature());
			System.out.println(base64Encoded);
			
			detailInsert.setSignatureFile(base64Encoded);
		
			logger.info("detailInsert=" + detailInsert);
			model.addAttribute(detailInsert);
			
			model.addAttribute("accountTypeList", printService.getAccountTypeByAppId(detailInsert.getApplicantID()));
		}
		logger.info("returning to applicantSearch");
		return "applicantSearch";
	}

	@RequestMapping(value = "/submitDetails", method = RequestMethod.POST)
	public ModelAndView submitDetails(
			@ModelAttribute("detailInsert") DetailInsert detailInsert, BindingResult result) throws IOException {
		logger.info("Print Controller.submitDetails()");
		ModelAndView mv = new ModelAndView("applicantConfirm");
		/*byte[] signature = null;
		signature = IOUtils.toByteArray(imgFile.getInputStream());
		detailInsert.setSignature(signature);*/

		/*if (null != signature) {
			String base64Encoded = byteToBase64(signature);
			detailInsert.setSignatureFile(base64Encoded);
		}*/
		/*
		 * System.out.println(signature);
		 * System.out.println(detailInsert.getSignatureFile());
		 */
		mv.addObject(detailInsert);
		logger.info("returning to applicantConfirm");
		return mv;
	}

	private String byteToBase64(byte[] signature) {
		logger.info("Signature found");
		byte[] encodeBase64 = Base64.encodeBase64(signature);
		String base64Encoded = "";
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Encoded;
	}

	@RequestMapping(value = "/confirmDetails", method = RequestMethod.POST)
	public ModelAndView confirmDetails(@ModelAttribute("detailInsert") DetailInsert detailInsert, BindingResult result,
			HttpServletRequest req)
			throws MalformedURLException, DocumentException, IOException {
		logger.info("Print Controller.confirmDetails()");

		
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		detailInsert.setUploaderId(usersFromSession.getLoginId());
		detailInsert.setUploadedDate(CommonUtil.getToday("dd-MMM-yyyy hh:mm:ss"));
		detailInsert.setCreatedDate(new Date());
		detailInsert.setCreatedBy(usersFromSession.getLoginId());
		detailInsert.setModifiedBy(usersFromSession.getLoginId());
		detailInsert.setModifiedDate(new Date());
		
		byte[] signature = base64ToByte(detailInsert.getSignatureFile());
		detailInsert.setSignature(signature);
		detailInsert.setStatus(ApplicationConstants.FILE_STATUS.INITIATED);
		printService.saveApplicantDetails(detailInsert);
		logger.info("Confirmed");
		logger.info("Generatingg Pdf....");
		GeneratePDF(detailInsert);
		logger.info("Pdf Generated");
		ModelAndView mv = new ModelAndView("applicantConfirm");
		mv.addObject("confirm", true);
		/*
		 * System.out.println(detailInsert.getSignatureFile());
		 * System.out.println(detailInsert.getSignature());
		 */
		mv.addObject(detailInsert);
		logger.info("returning for print");
		return mv;
	}

	private byte[] base64ToByte(String signatureFile) throws UnsupportedEncodingException {
		byte[] signature = Base64.decodeBase64(signatureFile.getBytes("UTF-8"));
		return signature;
	}

	@Autowired
	private PDFService pdfService;

	@RequestMapping(value = "/printForm", method = RequestMethod.POST)
	public void doDownload(@ModelAttribute("detailInsert") DetailInsert detailInsert, BindingResult result,
			HttpServletResponse response) throws IOException, DocumentException {
		logger.info("Print Controller.printForm()");

		byte[] signature = base64ToByte(detailInsert.getSignatureFile());
		detailInsert.setSignature(signature);
		String file = GeneratePDF(detailInsert);
		logger.info("Pdf generated at "+file);
		// final File file = new File("C:\\FirstPdf10.pdf");
		returnResponseToClient(detailInsert, response, file);
		logger.info("");
	}

	private void returnResponseToClient(DetailInsert detailInsert, HttpServletResponse response, String file)
			throws IOException, FileNotFoundException {
		InputStream inputStream=null;
		OutputStream outStream=null;
		try {

			final int BUFFER_SIZE = 4096;
			inputStream = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(file)));

			// set headers for the response
			String mimeType = "application/pdf";
			response.setContentType(mimeType);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=" + detailInsert.getApplicantID() + ".pdf");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} finally {
			inputStream.close();
			outStream.close();
		}
	}

	private String GeneratePDF(DetailInsert detailInsert) throws MalformedURLException, DocumentException, IOException {
		String dir = null;//properties.getFileLocation() + File.separator+"Applications";
		System.out.println(detailInsert.getSignatureFile());
		System.out.println(detailInsert.getSignature());
		File location = new File(dir);
		if (!location.isDirectory())
			location.mkdirs();
		String file = dir+ File.separator+detailInsert.getApplicantID() + ".pdf";
		logger.info("generating file at "+file);
		pdfService.generatePdf(detailInsert, file);
		return file;
	}

	@RequestMapping(value = "/getIBAN", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody String getIBAN(HttpServletRequest request, HttpServletResponse response) {
		logger.info("checkLoginId AJAX");

		logger.info(request.getParameter("accountType"));
		logger.info(request.getParameter("id"));
		String accountType = request.getParameter("accountType");

		String iban = printService.getIban(accountType);
		logger.info("iban " + iban);
		return (null != iban) ? iban : "";
	}

	
	@RequestMapping(value = "/getAllApplicantion", method = RequestMethod.GET)
	public ModelAndView getAllApplicantion(	HttpServletRequest req)
			throws UnsupportedEncodingException {
		logger.info("Print Controller.getAllApplicantion()");

		
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		DetailInsert details= new DetailInsert();
		details.setStatus(ApplicationConstants.FILE_STATUS.INITIATED);
		List<DetailInsert> appInitiated = printService.getApplicants(details);
		details.setStatus(ApplicationConstants.FILE_STATUS.APPROVED);
		List<DetailInsert> appVerified = printService.getApplicants(details);
		
		
		ModelAndView mv = new ModelAndView("printCheckerTab");
		mv.addObject("appInitiated", appInitiated);			
		mv.addObject("appVerified", appVerified);
		/*
		 * System.out.println(detailInsert.getSignatureFile());
		 * System.out.println(detailInsert.getSignature());
		 */
		
		logger.info("returning for print");
		return mv;
	}
	
	@RequestMapping(value = "/viewPendingApplicant/{id}", method = RequestMethod.GET)
	public String viewApplicantForApproval(@PathVariable("id") String id,Model model)
			throws IllegalAccessException, InvocationTargetException {
		logger.info("viewApplicantForApproval!" + id);
		
		
		DetailInsert detailInsert = printService.getSavedApplicantById(id);
		
		if (null != detailInsert) {
			logger.info("Record found:" + detailInsert.getApplicantID());
		
			String base64Encoded = byteToBase64(detailInsert.getSignature());
			System.out.println(base64Encoded);
			
			detailInsert.setSignatureFile(base64Encoded);
		
			logger.info("detailInsert=" + detailInsert);
			model.addAttribute(detailInsert);
		}
		logger.info("returning to applicantSearch");
		return "viewApplicant";
	}
	
	@RequestMapping(value = "/approveRejectApplication", method = RequestMethod.POST)
	public String aaproveRejectApplication(@RequestParam("id") String id,@RequestParam("action") String action,
			Model model,HttpServletRequest req)
			throws IllegalAccessException, InvocationTargetException {
		logger.info("aaproveRejectApplication!" + id+" status "+action);
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		DetailInsert detailInsert = new DetailInsert();
		detailInsert.setStatus(action);
		detailInsert.setApplicantID(id);
		detailInsert.setVerifiedDate(CommonUtil.getToday("dd-MMM-yyyy hh:mm:ss"));
		detailInsert.setVerifireId(usersFromSession.getLoginId());
		Integer updateStatus = printService.updateStatus(detailInsert);
		
		model.addAttribute("message","request "+action+" successfully" );
		logger.info("update status"+updateStatus);
		return "redirect:/print/getAllApplicantion.do";
	}

}
