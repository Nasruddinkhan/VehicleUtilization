/*package com.project.millatinventory.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.service.OfficeBo;

@Service("saveContents")
@Transactional
public class SaveContents {
    private static final Logger logger = LoggerFactory.getLogger(SaveContents.class);
     @Resource(name="sessionFactory")
     private SessionFactory sessionFactory;
     @Resource(name="officeBo")
     private OfficeBo officeBo;
    public void saveOfficeContents(MultipartFile file)throws IOException{
        try{
        
            String lowerCaseFileName = file.getOriginalFilename().toLowerCase();
            if (lowerCaseFileName.endsWith(".xlsx")) {
                //offices = new XSSFWorkbook(file.getInputStream());
            } else {
                //offices = new HSSFWorkbook(file.getInputStream());
            }
        	System.out.println(" begin try");
            HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
            System.out.println("line no 37");
            int numberOfSheets = offices.getNumberOfSheets();
            System.out.println("line no 39");
            for(int i=0 ;i<= numberOfSheets;i++){
            HSSFSheet worksheet = offices.getSheetAt(i);
            System.out.println("38"+worksheet);
            HSSFRow entry;
            Integer noOfEntries=1;
            //getLastRowNum and getPhysicalNumberOfRows showing false values sometimes.
            while(worksheet.getRow(noOfEntries)!=null){
                noOfEntries++;
            }
            System.out.println("45"+noOfEntries);
            logger.info(noOfEntries.toString());
            for(int rowIndex=1;rowIndex<noOfEntries;rowIndex++){
                entry=worksheet.getRow(rowIndex);
                long emp_id=((Double)entry.getCell(0).getNumericCellValue()).intValue();
                Office parent=officeBo.getOfficeById(((Double)entry.getCell(1).getNumericCellValue()).intValue());
                Long id=parent.getId();
                String mobile =entry.getCell(2).getStringCellValue();
                Integer companny_code=((Double)entry.getCell(3).getNumericCellValue()).intValue();
                double salary=((Double)entry.getCell(4).getNumericCellValue()).intValue();
                String bank_account_details=entry.getCell(5).getStringCellValue();
                String Emp_name_address=entry.getCell(6).getStringCellValue();
                Integer month=((Double)entry.getCell(7).getNumericCellValue()).intValue();
                String salary_details1=entry.getCell(8).getStringCellValue();
                String ifsc_code=entry.getCell(9).getStringCellValue();
                String currency=entry.getCell(10).getStringCellValue();
                double  bonus_amount=((Double)entry.getCell(11).getNumericCellValue()).intValue();
                double deduction_amount=((Double)entry.getCell(12).getNumericCellValue()).intValue();
              
//                Date date = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(openingDate);
                logger.info("Row Contents:"+id+" "+emp_id+" "+mobile+" "+companny_code+" "+salary+" "+ bank_account_details+" "+Emp_name_address+" "+month+" "+salary_details1+" "+ifsc_code+" "+currency+" "+bonus_amount+" "+deduction_amount);
                Office office=new Office();
                office.setId((long)id);
                office.setEmp_id((long)emp_id);
                office.setMobile_No(mobile);
                office.setCompanny_code((Integer)companny_code);
                office.setSalary(salary);
                office.setBank_account_details(bank_account_details);
                office.setEmp_address(Emp_name_address);
                office.setMonth((Integer)month);
                office.setSalary_details1(salary_details1);
                office.setIfsc_code(ifsc_code);
                office.setCurrency(currency);
                office.setBonus_amount(bonus_amount);
                office.setDeduction_amount(deduction_amount);
               // office.setExternalId((long)externalId);
                //office.setName(name);
               // office.setOpeningDate(openingDate);
               // String parentHierarchy=parent.getHierarchy();
                //Pre save to generate id for use in hierarchy
                officeBo.save(office);
                //office.setHierarchy(parentHierarchy+office.getId()+".");
               // officeBo.save(office);
                System.out.println("88"+rowIndex);
            }
            }
        }catch(Exception e){
            logger.info(e.getMessage()+" "+e.getCause());
            throw new MultipartException("Constraints Violated");
        }
    }
	public List<FileHeader> getFileDetails() {
		officeBo.getFileDetails();
		return null;
	}
} 

*/