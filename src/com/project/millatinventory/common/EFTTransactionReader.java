package com.project.millatinventory.common;

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
import com.project.millatinventory.model.ExternalTransations;
import com.project.millatinventory.model.ExternalTransactionSummary;
import com.project.millatinventory.serviceimpl.InvalidDataException;

public class EFTTransactionReader {
	
	static final Logger logger= Logger.getLogger(EFTTransactionReader.class);
	public List<ExternalTransations> readExcelFile(ExternalTransactionSummary summary,
			InputStream inputStream) throws InvalidDataException {
		/*
		 * String excelFilePath = "C:\\Users\\khanshz\\FileUpload.xls";
		 * FileInputStream inputStream = new FileInputStream(new
		 * File(excelFilePath));
		 */
		logger.info("Start reading Excel"+summary.getPYM02FIL());

		
		List<ExternalTransations> fileDataList = new ArrayList<ExternalTransations>(50);
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
					ExternalTransations fileData = new ExternalTransations();
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
						String acbranch = df.formatCellValue(cell0);
						//System.out.println("empId="+empId);
						if ( acbranch.length() != 4) {
							/*throw new InvalidDataException(
									"Length of Emp Id should be 4 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (A) Account Branch must be 4 digits.");
									
						}
						fileData.setPYM03AB1(acbranch);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Emp Id for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (A) Account Branch is Invalid.");
					}
					//MOBILE NUMBER
					try {
						Cell cell1 = nextRow.getCell(1);
						String acNumber = df.formatCellValue(cell1);
						if ( acNumber.length() != 6) {
							/*throw new InvalidDataException(
									"Length of Mobile Number should be 8 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (B) Account Number must be 6 digits");
							
						} else if(!CommonUtil.isNumeric(acNumber)){
							/*throw new InvalidDataException(
									"Mobile Number should be Numeric for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (B) Account Number must be Numeric");
						}
						
						fileData.setPYM03AN1(acNumber);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Mobile No. for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (B) Account Number is Invalid.");
					}
					//COMPANY CODE
					try {
						Cell cell2 = nextRow.getCell(2);
						String acSuffix = df.formatCellValue(cell2);
						if ( acSuffix.length() != 3) {
							/*throw new InvalidDataException(
									"Length of Company Code should be 3 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									+(1+rowNum)+") Column No (C) Account suffix must be 3 digits ");
							
						}
						fileData.setPYM03AS1(acSuffix);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Company Code for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (C) Account suffix is Invalid.");
					}
					//SALARY 
					try {
						Cell cell3 = nextRow.getCell(3);
						BigDecimal transferAmount = new BigDecimal(cell3
								.getNumericCellValue());
						fileData.setPYM03TAMT(transferAmount);
					} catch (IllegalStateException e) {
						
						/*throw new InvalidDataException(
								"Invalid Salary for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (D) Transfer Amount is Invalid.");
						
					}
					//BANK ACCOUNT
					try {
						Cell cell4 = nextRow.getCell(4);
						String beneficiaryAccount = df.formatCellValue(cell4);
						if ( beneficiaryAccount.length() > 34) {
							/*throw new InvalidDataException(
									"Length of Bank Account should be less than 34 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (D) Length of Beneficiary Account should be less than 34 ");
							
						}
						else if(!CommonUtil.isAlphaNumeric(beneficiaryAccount)){
							/*throw new InvalidDataException(
									"Bank Account should be AlpahNumeric for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (E) Beneficiary Account should be AlpahNumeric ");
						}
						fileData.setPYM03OAN1(beneficiaryAccount);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Bank Account for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (E) Beneficiary Account is Invalid.");
						
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
						
						fileData.setPYM03BAD1(nameAddress);
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
						String purposeCode = df.formatCellValue(cell6);
						if (purposeCode.length()!=0 && purposeCode.length() !=2) {
							/*throw new InvalidDataException(
									"Length of Month should be 2 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (G) Payment Purpose code be 2 digits ");
						}
						
						fileData.setPYM03PYP(purposeCode);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Month for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (G) Payment Purpose code is Invalid.");
					}
					
					try {
						Cell cell7 = nextRow.getCell(7);
						String paymentDetail1 = df.formatCellValue(cell7);
						if (paymentDetail1.length()!=0 && paymentDetail1.length() > 35) {
							/*throw new InvalidDataException(
									"Length of Salary Details 1 should be less than 35 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (H) Length of Payment Details 1 should be less than 35 ");
							
						}
						
						fileData.setPYM03PD1(paymentDetail1);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Salary Details 1 for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (H) Payment Details 1 is Invalid.");
					}
					
					try {
						Cell cell8 = nextRow.getCell(8);
						String paymentDetail2 = df.formatCellValue(cell8);
						if (paymentDetail2.length()!=0 && paymentDetail2.length() > 35) {
							/*throw new InvalidDataException(
									"Length of Salary Details 2 should be less than 35 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (I) Length of Payment Details 2 should be less than 35 ");
						}
						
						fileData.setPYM03PD2(paymentDetail2);
						
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Salary Details 2 for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (I) Payment Details 2 is Invalid.");
					}
					
					try {
						Cell cell9 = nextRow.getCell(9);
						String swift = df.formatCellValue(cell9);
						
						if (swift.length()!=0 && swift.length() > 11) {
							/*throw new InvalidDataException(
									"Length of	 IFSC Code should be less than 11 for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (J) Length of Beneficiary Swift should be less than 11 ");
						}
						fileData.setPYM03SWT(swift);
						
					} catch (IllegalStateException e) {
					/*	throw new InvalidDataException(
								"Invalid IFSC Code for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (J) Beneficiary Swift is Invalid.");
					}

					try {
						Cell cell10 = nextRow.getCell(10);
						String currency = df.formatCellValue(cell10);
						if (!currency.isEmpty() && !currency.equalsIgnoreCase("QAR")) {
						/*	throw new InvalidDataException(
									"Currency should be INR for Row # "
											+ ++rowNum + " of Sheet "
											+ sheet.getSheetName());*/
							
							errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
									(1+rowNum)+") Column No (K) Currency must be QAR");
						}
						fileData.setPYM03PCCY(currency);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Currency for Row # " + ++rowNum
										+ " of Sheet " + sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (K) Currency is Invalid.");
					}
					
					try {
						Cell cell11 = nextRow.getCell(11);
						String chargeCode = df.formatCellValue(cell11);
						fileData.setPYM03CCODE(chargeCode);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Bonus Amount for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (L) Charge Code is Invalid.");
					}
					
					try {
						Cell cell12 = nextRow.getCell(12);
						BigDecimal chargeAmount = new BigDecimal(cell12
								.getNumericCellValue());
						fileData.setPYM03CAMT(chargeAmount);
					} catch (IllegalStateException e) {
						/*throw new InvalidDataException(
								"Invalid Deduction Amount for Row # "
										+ ++rowNum + " of Sheet "
										+ sheet.getSheetName());*/
						
						errorMessages.add("Sheet ("+sheet.getSheetName() +") Row No ("+ 
								(1+rowNum)+") Column No (M) Charge Amount is Invalid.");
					}
					fileData.setPYM03BID(summary.getPYM02BID());
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
