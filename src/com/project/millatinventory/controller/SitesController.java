package com.project.millatinventory.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.SitesService;

@Controller
@RequestMapping(value = "/site")
public class SitesController {

	 private static final Logger logger = LoggerFactory.getLogger(SitesController.class);
		@Autowired
		private SitesService siteService;

		@Autowired
		private CommonService commonService;
		
		
		@RequestMapping(value = "/showAddSites", method = RequestMethod.GET)
		public ModelAndView showAddSites(@ModelAttribute("sites") Sites site, BindingResult result) {
			logger.info("SitesController.showAddSites");
			ModelAndView mv = new ModelAndView("addSite");
			logger.info("Hi");
			logger.info("return to Add Sites page ");
			return mv;
		}
		
		private List getSites() {
			return commonService.getSites();
		}

		
		@RequestMapping(value = "/saveSite", method = RequestMethod.POST)
		public String saveSite(HttpServletRequest req, Model model,@ModelAttribute("sites") Sites site, BindingResult result) {
			logger.info("in saveSite Method()");
			logger.info("saveSite!");
			
			//System.out.println(imgFile.getOriginalFilename());
			//System.out.println(file.getOriginalFilename());
			/*byte[] imgBytes = null;
			byte[] fileBytes = null;
			try {
				imgBytes = IOUtils.toByteArray(imgFile.getInputStream());
				fileBytes = IOUtils.toByteArray(file.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			site.setImage(imgBytes);
			site.setFile(fileBytes);
			site.setFileName(file.getOriginalFilename());*/
			//System.out.println(site);
			Users usersFromSession = (Users) req.getSession().getAttribute("User");
			//System.out.println(user);

			if (site.getSiteId() < 0 || 0 == site.getSiteId()) {
				site.setCreatedBy(usersFromSession.getUserName());
				site.setCreatedDate(new Date());
				model.addAttribute("message", "Site added successfully");
				logger.info("ADD");
				logger.info("ADD");
			} else {
				site.setModifiedBy(usersFromSession.getUserName());
				site.setModifiedDate(new Date());
				model.addAttribute("message", "Site updated successfully");
				logger.info("Edit");
				logger.info("EDIT");
			}
			siteService.saveSites(site);
			return "forward:/site/sites.do";
		}
		
		@RequestMapping(value = "/sites", method = RequestMethod.GET)
		public String getAllSites(Model model) {
			
			logger.info("SitesController.getAllSites()");
			List<Sites> sites = siteService.getSites();
			model.addAttribute("siteList", sites);
			return "siteList";
		}
		
		@RequestMapping(value = "/sites", method = RequestMethod.POST)
		public String getSites(Model model) {
			
			logger.info("SiteList", model.asMap());
			logger.info("SitesController.getAllSites()");
			List<Sites> sites = siteService.getSites();
			model.addAttribute("siteList", sites);
			logger.info("show Sites list"+sites.size());
			return "siteList";
		}
		
		@RequestMapping(value = "/ShowUpdateSite/{siteId}", method = RequestMethod.GET)
		public ModelAndView ShowUpdateSite(@PathVariable("siteId") Integer siteId,
				ModelMap map) {
			logger.info("SitesController.ShowUpdateSite()");
			Sites siteById = siteService.getSiteById(siteId);
			ModelAndView mv = new ModelAndView("addSite");
			
			//mv.addObject("departmentList", getSites());
			mv.addObject(siteById);
			System.out.println(siteById);
			return mv;
		}
		
		@RequestMapping(value = "/deleteSite/{siteId}", method = RequestMethod.GET)
		public String deleteSite(Model model, @PathVariable("siteId") int siteId) {
			logger.info("deleteSite", siteId);
			logger.info("SiteController.deleteSite()" + siteId);
			try {
			int deleteSiteById = siteService.deleteSiteById(siteId);
			if (deleteSiteById == 0)
				model.addAttribute("message", "Site not deleted successfully");
			else
				model.addAttribute("message", "Site deleted successfully");
			}catch(ConstraintViolationException e) {
				model.addAttribute("Errormessage", "Site can not be deleted as it has associated User & Entries");
			}
			logger.info("return to site List ");
			
			return "forward:/site/sites.do";
		}
	
		
		@RequestMapping(value = "/viewSite/{siteId}", method = RequestMethod.GET)
		public ModelAndView viewSite(@PathVariable("siteId") Integer siteId,
				ModelMap map) {
			logger.info("viewSite"+siteId);
			logger.info("SiteController.viewSite()");
			Sites siteById = siteService.getSiteById(siteId);
			ModelAndView mv = new ModelAndView("viewSite");
			System.out.println("150");
			byte[] bytes = siteById.getImage();
			if (null != bytes) {
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				String base64Encoded = "";
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				siteById.setImageFile(base64Encoded);
			}

			mv.addObject(siteById);
			logger.info("Return viewSite");
			return mv;
		}
	
		
}
