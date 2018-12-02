package com.project.millatinventory.serviceimpl;

import javax.servlet.http.HttpServletResponse; 

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.project.millatinventory.controller.Writer;
import com.project.millatinventory.reporting.Layouter;

@Service("downloadService")
@Transactional
public class DownloadService {
    private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);
    public void downloadXLS(HttpServletResponse response,String viewType) throws ClassNotFoundException {
        logger.info("Downloading Excel Template");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Offices");
         int startRowIndex = 0;
         int startColIndex = 0;
         Layouter.buildOfficeTemplate(worksheet, startRowIndex, startColIndex);
         String fileName = viewType+".xlsx";
         response.setHeader("Content-Disposition", "inline; filename=" + fileName);
         response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
         Writer.write(response, worksheet);
    }
} 


