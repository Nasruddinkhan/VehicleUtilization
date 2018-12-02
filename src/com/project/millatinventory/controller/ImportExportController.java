package com.project.millatinventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.ReqExclScorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.millatinventory.common.Properties;
import com.project.millatinventory.service.CommonService;
import java.io.*;

@Controller
@RequestMapping(value = "/importExport")
public class ImportExportController {
	private static final Logger logger = LoggerFactory.getLogger(ImportExportController.class);
	@Autowired
	private CommonService commonService;
	@Autowired
	Properties properties;

	@RequestMapping(value = "/exportPage", method = RequestMethod.GET)
	public String exportPage(Model model) {
		logger.info("ExportPage ");
		logger.info("Return to ExportPage");
		return "export";
	}

	@RequestMapping(value = "/Export", method = RequestMethod.POST)
	public String Export(HttpServletRequest req, Model model) throws IOException, InterruptedException {
		String userName = properties.getUserName();
		String pass = properties.getPass();
		String pathsqlfile = properties.getFilesqllocation();
		logger.info("pathsqlfile "+pathsqlfile);
		String storageLocation = properties.getStorageLocation();
		logger.info("storageLocation "+storageLocation);
		String DBName = properties.getResource().substring(28);
		logger.info("DBName "+DBName);
		int a = commonService.db_Backup(DBName, storageLocation, pathsqlfile, userName, pass);
		if (a == 0) {
			model.addAttribute("message", "File Navdurga.sql exported successfully at {"+storageLocation.substring(0, storageLocation.length()-1)+"} ");
		} else {
			model.addAttribute("message", "Resource Undefind");
		}
		return "export";
	}

	@RequestMapping(value = "/importPage", method = RequestMethod.GET)
	public String importPage(Model model) {
		logger.info("importPage ");
		logger.info("Return to importPage");
		model.addAttribute("Note", "");
		return "import";
	}

	@RequestMapping(value = "/Import", method = RequestMethod.POST)
	public String Import(HttpServletRequest req, Model model, @RequestParam("uploadfile") MultipartFile file)
			throws IOException, InterruptedException {
		String userName = properties.getUserName();
		String Pass = properties.getPass();
		String pathsqlconsole = properties.getFilesqllocation();
		logger.info("pathsqlconsole "+pathsqlconsole);
		String storageLocation = properties.getStorageLocation();
		logger.info("storageLocation "+storageLocation);
		// Save imported File @storageLocation
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
			new FileOutputStream(new File(storageLocation, file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();

			//String filename = req.getParameter("uploadfile");
			logger.info("getOriginalFilename "+file.getOriginalFilename());
			String source = storageLocation + file.getOriginalFilename();
			int b = commonService.db_Restore(pathsqlconsole, userName, Pass, source);
			if (b == 0) {
				model.addAttribute("message", "File import successfully!");
			} else {
				model.addAttribute("message", "file path is not correct!!!");
			}
		} else {
			model.addAttribute("message", "file path is not correct!!!");
		}

		return "import";
	}

}
