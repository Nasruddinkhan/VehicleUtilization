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
import java.util.Map;

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
import com.project.millatinventory.model.Cheque;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.ChequeService;
import com.project.millatinventory.service.PrintService;

@Controller
@RequestMapping(value = "/cheque")
public class ChequeController {

	private static final Logger logger = LoggerFactory.getLogger(ChequeController.class);
	@Autowired
	private Properties properties;
	
		@Autowired
	private ChequeService chequeService;
	/*	@Autowired
	private PrintService printService;*/

	

	@RequestMapping(value = "/showChequePrint", method = RequestMethod.GET)
	public String showPage(HttpServletRequest req, Model model) {
		logger.info("showChequePrint!");
		//load data for status
		
		
		Users usersFromSession = (Users) req.getSession().getAttribute(
				"User");
		
		//detailInsert.setCreatedBy(usersFromSession.getLoginId());
		//List<DetailInsert> applicants = printService.getApplicants(detailInsert);
		
		//model.addAttribute("applicantList", applicants);
		return "showChequePrint";
	}
	/*
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
		byte[] signature = null;
		signature = IOUtils.toByteArray(imgFile.getInputStream());
		detailInsert.setSignature(signature);

		if (null != signature) {
			String base64Encoded = byteToBase64(signature);
			detailInsert.setSignatureFile(base64Encoded);
		}
		
		 * System.out.println(signature);
		 * System.out.println(detailInsert.getSignatureFile());
		 
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
		
		 * System.out.println(detailInsert.getSignatureFile());
		 * System.out.println(detailInsert.getSignature());
		 
		mv.addObject(detailInsert);
		logger.info("returning for print");
		return mv;
	}

	private byte[] base64ToByte(String signatureFile) throws UnsupportedEncodingException {
		byte[] signature = Base64.decodeBase64(signatureFile.getBytes("UTF-8"));
		return signature;
	}
*/
	@Autowired
	private PDFService pdfService;
	private Cheque chequeByNumber;

	@RequestMapping(value = "/print", method = RequestMethod.POST)
	public String print(@RequestParam("chequeNumber") String chequeNumber ,HttpServletResponse response,Map model) throws IOException, DocumentException {
		logger.info("Cheque Controller.print()");
System.out.println(chequeNumber);
	
Cheque cheque = chequeService.getChequeByNumber(chequeNumber);
if(cheque!=null){
	logger.info("Cheque found");
		String file = GeneratePDF(cheque);
		logger.info("Pdf generated at "+file);
		// final File file = new File("C:\\FirstPdf10.pdf");
		returnResponseToClient( response, new File(file));		
		return null;
}else{
	logger.info("No Cheque found");
	model.put("noCheque", "No cheque present for number "+chequeNumber);
	return "showChequePrint";
}
}

	private void returnResponseToClient(HttpServletResponse response, File file)
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
			String headerValue = String.format("attachment; filename=" + file.getName());
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

	private String GeneratePDF(Cheque cheque) throws MalformedURLException, DocumentException, IOException {
		String dir = null;//properties.getFileLocation() + File.separator+"CHEQUES";
		
		File location = new File(dir);
		if (!location.isDirectory())
			location.mkdirs();
		String file = dir+ File.separator+cheque.getCheque_No() + ".pdf";
		String template = dir+ File.separator+"CHEQUE_TEMPLATE" + ".pdf";
		logger.info("generating file at "+file);
		pdfService.printCheque(cheque,dir);
		return file;
	}

}
