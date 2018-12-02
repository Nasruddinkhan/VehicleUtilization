package com.project.millatinventory.common;



import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExample
{
   public static void main(String[] args)throws Exception 
   {
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("cellstyle");
      XSSFRow row = spreadsheet.createRow((short) 1);
      XSSFCell cell = (XSSFCell) row.createCell(1);
     
      
     
      //MEARGING CELLS 
      //this statement for merging cells
      spreadsheet.addMergedRegion(new CellRangeAddress(
      1, //first row (0-based)
      1, //last row (0-based)
      1, //first column (0-based)
      4 //last column (0-based)
      ));
      //CELL Alignment
     
      
     
     
      // Top Left alignment 
      
      
      
      XSSFCellStyle style1 = workbook.createCellStyle();
      style1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
      style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
      cell.setCellValue("Infotech,Bangabre Post : 284402,Karnataka-india");
      cell.setCellStyle(style1);
      row = spreadsheet.createRow(0); 
      cell = (XSSFCell) row.createCell(3);
      row.setHeight((short) 800);
      // Center Align Cell Contents 
      
      
      XSSFCellStyle style2 = workbook.createCellStyle();
      style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
      style2.setVerticalAlignment( 
      XSSFCellStyle.VERTICAL_CENTER);
      cell.setCellValue("Center Aligned"); 
      cell.setCellStyle(style2);
      row = spreadsheet.createRow(7); 
      cell = (XSSFCell) row.createCell(2);
      row.setHeight((short) 800);
      // Bottom Right alignment 
      XSSFCellStyle style3 = workbook.createCellStyle();
      style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
      style3.setVerticalAlignment( 
      XSSFCellStyle.VERTICAL_BOTTOM);
      cell.setCellValue("Bottom Right");
      cell.setCellStyle(style3);
      row = spreadsheet.createRow(8);
      cell = (XSSFCell) row.createCell(3);
      // Justified Alignment 
      
      XSSFCellStyle style4 = workbook.createCellStyle();
      style4.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
      style4.setVerticalAlignment(
      XSSFCellStyle.VERTICAL_JUSTIFY);
      cell.setCellValue("Contents are Justified in Alignment"); 
      cell.setCellStyle(style4);
      //CELL BORDER
      row = spreadsheet.createRow((short) 10);
      row.setHeight((short) 800);
      cell = (XSSFCell) row.createCell((short) 6);
      cell.setCellValue("BORDER");
      
      
      XSSFCellStyle style5 = workbook.createCellStyle();
      style5.setBorderBottom(XSSFCellStyle.BORDER_THICK);
      style5.setBottomBorderColor(
      IndexedColors.BLUE.getIndex());
      style5.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE);
      style5.setLeftBorderColor( 
      IndexedColors.GREEN.getIndex());
      style5.setBorderRight(XSSFCellStyle.BORDER_HAIR);
      style5.setRightBorderColor( 
      IndexedColors.RED.getIndex());
      style5.setBorderTop(XSSFCellStyle.BIG_SPOTS);
      style5.setTopBorderColor( 
      IndexedColors.CORAL.getIndex());
      cell.setCellStyle(style5);
      //Fill Colors
      //background color
      row = spreadsheet.createRow((short) 10 );
      cell = (XSSFCell) row.createCell((short) 1);
      
      
      XSSFCellStyle style6 = workbook.createCellStyle();
      style6.setFillBackgroundColor(
      HSSFColor.LEMON_CHIFFON.index );
      style6.setFillPattern(XSSFCellStyle.LESS_DOTS);
      style6.setAlignment(XSSFCellStyle.ALIGN_FILL);
      spreadsheet.setColumnWidth(1,8000);
      cell.setCellValue("FILL BACKGROUNG/FILL PATTERN");
      cell.setCellStyle(style6);
      //Foreground color
      row = spreadsheet.createRow((short) 12);
      cell = (XSSFCell) row.createCell((short) 1);
      XSSFCellStyle style7=workbook.createCellStyle();
      style7.setFillForegroundColor(HSSFColor.BLUE.index);
      style7.setFillPattern( XSSFCellStyle.LESS_DOTS);
      style7.setAlignment(XSSFCellStyle.ALIGN_FILL);
      cell.setCellValue("FILL FOREGROUND/FILL PATTERN");
      cell.setCellStyle(style7);
      FileOutputStream out = new FileOutputStream(
      new File("C:\\generatedFile\\cellstyle.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("cellstyle.xlsx written successfully");
   }
}
