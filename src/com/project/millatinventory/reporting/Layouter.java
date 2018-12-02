package com.project.millatinventory.reporting; 

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font; 

public class Layouter { 

    public static void buildOfficeTemplate(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
          worksheet.setColumnWidth(0, 2000);
          worksheet.setColumnWidth(1, 7000);
          worksheet.setColumnWidth(2, 7000);
          worksheet.setColumnWidth(3, 7000);
          worksheet.setColumnWidth(4, 7000);
          worksheet.setColumnWidth(5, 7000);
          worksheet.setColumnWidth(6, 7000);
          worksheet.setColumnWidth(7, 7000);
          worksheet.setColumnWidth(8, 7000);
          worksheet.setColumnWidth(9, 7000);
          worksheet.setColumnWidth(10, 7000);
          worksheet.setColumnWidth(11, 7000);
          worksheet.setColumnWidth(12, 7000);
          Font font = worksheet.getWorkbook().createFont();
          font.setBoldweight(Font.BOLDWEIGHT_BOLD);
          HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
          headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
          headerCellStyle.setWrapText(true);
          headerCellStyle.setFont(font);
          headerCellStyle.setBorderRight(CellStyle.BORDER_THICK);
          headerCellStyle.setBorderBottom(CellStyle.BORDER_THICK);
          HSSFRow rowHeader = worksheet.createRow((short) startRowIndex);
          rowHeader.setHeight((short) 500);
          HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
          cell1.setCellValue("Emp id");
          cell1.setCellStyle(headerCellStyle);
          HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
          cell2.setCellValue("Mobile No");
          cell2.setCellStyle(headerCellStyle);
          HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
          cell3.setCellValue("company code");
          cell3.setCellStyle(headerCellStyle);
          HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
          cell4.setCellValue("salary");
          cell4.setCellStyle(headerCellStyle);
          HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
          cell5.setCellValue("bank account details");
          cell5.setCellStyle(headerCellStyle);
          HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
          cell6.setCellValue("Emp Name & Address");
          cell6.setCellStyle(headerCellStyle);
          HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
          cell7.setCellValue("Month");
          cell7.setCellStyle(headerCellStyle);
          HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
          cell8.setCellValue("salary Details1");
          cell8.setCellStyle(headerCellStyle);
          HSSFCell cell9 = rowHeader.createCell(startColIndex+8);
          cell9.setCellValue("ifsc Code");
          cell9.setCellStyle(headerCellStyle);
          HSSFCell cell10 = rowHeader.createCell(startColIndex+9);
          cell10.setCellValue("currency");
          cell10.setCellStyle(headerCellStyle);
          HSSFCell cell11 = rowHeader.createCell(startColIndex+10);
          cell11.setCellValue("Bonus amount");
          cell11.setCellStyle(headerCellStyle);
          HSSFCell cell12 = rowHeader.createCell(startColIndex+11);
          cell12.setCellValue("Deduction Amount");
          cell12.setCellStyle(headerCellStyle);
    }
}

