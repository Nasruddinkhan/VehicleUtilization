package com.project.millatinventory.model;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.project.millatinventory.common.CommonUtil;
import com.project.millatinventory.filter.ExceptionHandler;
import com.project.millatinventory.serviceimpl.InvalidDataException;

public class ExcelReader {
	
	static final Logger logger= Logger.getLogger(ExcelReader.class);
	public List<FileData> readExcelFile(FileHeader fileHeader,
			InputStream inputStream) throws InvalidDataException {
		/*
		 * String excelFilePath = "C:\\Users\\khanshz\\FileUpload.xls";
		 * FileInputStream inputStream = new FileInputStream(new
		 * File(excelFilePath));
		 */
		logger.info("Start reading Excel"+fileHeader.getFileName());

		
		List<FileData> fileDataList = new ArrayList<FileData>(50);
		Workbook workbook = null;
		List<String> errorMessages= new ArrayList<String>();
		
		try {
			workbook = WorkbookFactory.create(inputStream);
			logger.info("Workbook created "+workbook);	
			
			int numberOfSheets = workbook.getNumberOfSheets();
			logger.info("Number of sheet"+numberOfSheets);
			DataFormatter df = new DataFormatter();
			Sheet sheet = null;
			Iterator<Row> iterator = null;
			Row nextRow = null;

			Cell cell0 = null;
			System.out.println("41" + numberOfSheets);
			
			for (int i = 0; i < numberOfSheets; i++) {
				System.out.println("43===" + i);
				sheet = workbook.getSheetAt(i);

				iterator = sheet.iterator();
				while (iterator.hasNext()) {
					FileData fileData = new FileData();
					nextRow = iterator.next();
					int rowNum = nextRow.getRowNum();
					if (rowNum == 0) {
						continue;
					}
					try {
						cell0 = nextRow.getCell(0);
						if (cell0 == null
								|| cell0.getCellType() == Cell.CELL_TYPE_BLANK)
							break;
						
						//EMP ID
						String empId = df.formatCellValue(cell0);
						//System.out.println("empId="+empId);
						if ( empId.length() != 4) {
							/*throw new InvalidDataException(
									"Length of Emp Id should be 4 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (A) Emp Id must be 4 digits.");
									
						}
						fileData.setEmpId(empId);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Emp Id for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (A) Emp Id is Invalid.");
					}
					//MOBILE NUMBER
					try {
						Cell cell1 = nextRow.getCell(1);
						String mobileNo = df.formatCellValue(cell1);
						if ( mobileNo.length() != 8) {
							/*throw new InvalidDataException(
									"Length of Mobile Number should be 8 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (B) Mobile Number must be 8 digits");
							
						} else if(!CommonUtil.isNumeric(mobileNo)){
							/*throw new InvalidDataException(
									"Mobile Number should be Numeric for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (B) Mobile Number must be Numeric");
						}
						
						fileData.setMobileNo(mobileNo);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Mobile No. for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (B) Mobile Number is Invalid.");
					}
					//COMPANY CODE
					try {
						Cell cell2 = nextRow.getCell(2);
						String companyCode = df.formatCellValue(cell2);
						if ( companyCode.length() != 3) {
							/*throw new InvalidDataException(
									"Length of Company Code should be 3 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									+(1+rowNum)+") Column No (C) Company Code must be 3 digits ");
							
						}
						fileData.setCompanyCode(companyCode);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Company Code for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (C) Company Code is Invalid.");
					}
					//SALARY 
					try {
						Cell cell3 = nextRow.getCell(3);
						BigDecimal salary = new BigDecimal(cell3
								.getNumericCellValue());
						fileData.setSalary(salary);
					} catch (IllegalStateException e) {
						
						/*throw new InvalidDataException(
								"Invalid Salary for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (D) Salary is Invalid.");
						
					}
					//BANK ACCOUNT
					try {
						Cell cell4 = nextRow.getCell(4);
						String bankAccount = df.formatCellValue(cell4);
						if ( bankAccount.length() > 34) {
							/*throw new InvalidDataException(
									"Length of Bank Account should be less than 34 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (D) Length of Bank Account should be less than 34 ");
							
						}
						else if(!CommonUtil.isAlphaNumeric(bankAccount)){
							/*throw new InvalidDataException(
									"Bank Account should be AlpahNumeric for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (E) Bank Account should be AlpahNumeric ");
						}
						fileData.setBankAccount(bankAccount);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Bank Account for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (E) Bank Account is Invalid.");
						
					}
					//NAME & ADDRESS
					try {
						Cell cell5 = nextRow.getCell(5);
						String nameAddress = df.formatCellValue(cell5);
						if ( nameAddress.length() > 75) {
							/*throw new InvalidDataException(
									"Length of Name & Address should be less than 75 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (F) Length of Name & Address should be less than 75 ");
						}
						
						fileData.setNameAddress(nameAddress);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Name & Address for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (F) Name & Addres is Invalid.");
						
					}
					try {
						Cell cell6 = nextRow.getCell(6);
						String month = df.formatCellValue(cell6);
						if (month.length()!=0 && month.length() !=2) {
							/*throw new InvalidDataException(
									"Length of Month should be 2 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (G) Month be 2 digits ");
						}
						
						fileData.setMonth(month);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Month for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (G) Month is Invalid.");
					}
					
					try {
						Cell cell7 = nextRow.getCell(7);
						String salaryDetail1 = df.formatCellValue(cell7);
						if (salaryDetail1.length()!=0 && salaryDetail1.length() > 35) {
							/*throw new InvalidDataException(
									"Length of Salary Details 1 should be less than 35 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (H) Length of Salary Details 1 should be less than 35 ");
							
						}
						
						fileData.setSalaryDetail1(salaryDetail1);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Salary Details 1 for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (H) Salary Details 1 is Invalid.");
					}
					
					try {
						Cell cell8 = nextRow.getCell(8);
						String salaryDetail2 = df.formatCellValue(cell8);
						if (salaryDetail2.length()!=0 && salaryDetail2.length() > 35) {
							/*throw new InvalidDataException(
									"Length of Salary Details 2 should be less than 35 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (I) Length of Salary Details 2 should be less than 35 ");
						}
						
						fileData.setSalaryDetail2(salaryDetail2);
						
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Salary Details 2 for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (I) Salary Details 2 is Invalid.");
					}
					
					try {
						Cell cell9 = nextRow.getCell(9);
						String ifscCode = df.formatCellValue(cell9);
						
						if (ifscCode.length()!=0 && ifscCode.length() > 11) {
							/*throw new InvalidDataException(
									"Length of	 IFSC Code should be less than 11 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (J) Length of IFSC Code should be less than 11 ");
						}
						fileData.setIfscCode(ifscCode);
						
					} catch (IllegalStateException e) {
					/*	throw new InvalidDataException(
								"Invalid IFSC Code for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (J) IFSC Code is Invalid.");
					}

					try {
						Cell cell10 = nextRow.getCell(10);
						String currency = df.formatCellValue(cell10);
						if (!currency.isEmpty() && !currency.equalsIgnoreCase("INR")) {
						/*	throw new InvalidDataException(
									"Currency should be INR for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (K) Currency must be INR");
						}
						fileData.setCurrency(currency);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Currency for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (K) Currency is Invalid.");
					}
					
					try {
						Cell cell11 = nextRow.getCell(11);
						BigDecimal bonusAmount = new BigDecimal(cell11
								.getNumericCellValue());
						fileData.setBonusAmount(bonusAmount);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Bonus Amount for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (L) Bonus Amount is Invalid.");
					}
					
					try {
						Cell cell12 = nextRow.getCell(12);
						BigDecimal deductAmount = new BigDecimal(cell12
								.getNumericCellValue());
						fileData.setDeductionAmount(deductAmount);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Deduction Amount for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (M) Deduction Amount is Invalid.");
					}
					fileData.setFileHeader(fileHeader);
					fileDataList.add(fileData);
				}
			}
			if(errorMessages.size()>0)
			{
				InvalidDataException invalidDataException = new InvalidDataException("Invalid File");
				invalidDataException.setList(errorMessages);
				throw invalidDataException;
			}
			
			
		} catch (IllegalStateException | InvalidFormatException | IOException e) {
			throw new InvalidDataException(
					"Invalid File");
		}
		return fileDataList;
	}
}
