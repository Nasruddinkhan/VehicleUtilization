package com.project.millatinventory.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.util.Calendar;
import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.common.EFTTransactionReader;
import com.project.millatinventory.dao.ExternalFundTransferDao;
import com.project.millatinventory.model.ExternalTransactionSearch;
import com.project.millatinventory.model.ExternalTransactionSummary;
import com.project.millatinventory.model.ExternalTransations;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.ExternalFundTransferService;

@Service("ExternalFundTransferService")
//@Transactional(value="mySQLTransactionManager")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class ExternalFundTransferServiceImpl implements ExternalFundTransferService{

	@Autowired
	ExternalFundTransferDao externalFundTransferDao;


final static Logger logger = Logger.getLogger(ExternalFundTransferServiceImpl.class);
//public void setofficeDao(OfficeDao officeDao) {
//this.officeDao = officeDao;
//}
//public void save(Office office){
//officeDao.save(office);
//}
//public Office getOfficeById(long id){
//return officeDao.getOfficeById(id);
//}
//public Office getOfficeByName(String name){
//return officeDao.getOfficeByName(name);
//}

@Override
public List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSummary summary) {
	
	return externalFundTransferDao.getFileDetails(summary);
}
/*
@Override
public List<FileData> getFileData(Integer fileId) {
	// TODO Auto-generated method stub
	return externalFundTransferDao.getFileData(fileId);
}*/

@Override
public void saveContents(MultipartFile file,Users user) throws InvalidDataException{
	InputStream inputStream =null; 
	try{
        String fileName = file.getOriginalFilename();
        String maxBatchId = getMaxBatchId();
        logger.info("maxBatchId"+maxBatchId);
        ExternalTransactionSummary summary= new ExternalTransactionSummary();
        summary.setPYM02BID(maxBatchId);
        summary.setPYM02FIL(fileName); 
        summary.setPYM02USRU(user.getLoginId());
        summary.setPYM02STS(ApplicationConstants.EFT_TXN_STATUS.INITIATED);

         EFTTransactionReader eftreader= new EFTTransactionReader();   
         logger.info("4");
         inputStream = file.getInputStream();
         logger.info("5");
         List<ExternalTransations> fileDataList = eftreader.readExcelFile(summary, inputStream);
         logger.info("6");
		 System.out.println(fileDataList.size());

		 
         Calendar instance = Calendar.getInstance();
         instance.setTime(new java.util.Date());
         summary.setPYM02DATU(new Date(instance.getTimeInMillis()));
         
         summary.setPYM02TOT(fileDataList.size());
         summary.setPYM02TAMT(getTotalAmount(fileDataList));
         summary.setFileData(fileDataList);
         System.out.println("Save1");
         logger.info("7");
         externalFundTransferDao.saveFileHeader(summary);
         logger.info("8");
        
     }
	 catch(InvalidDataException e){
         throw e;
     }
	 catch(Exception e){
         throw new MultipartException("Error occurred while uploading Excel="+e.getMessage());
     }
	 finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

private BigDecimal getTotalAmount(List<ExternalTransations> fileDataList) {
	BigDecimal tot= new BigDecimal(0);
	for(ExternalTransations et:fileDataList){
		tot=tot.add(et.getPYM03TAMT());
	}
	System.out.println(tot);
	return tot;
}

@Override
public List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSearch eftSearch) throws ParseException {
	List fileDetails = externalFundTransferDao.getFileDetails(eftSearch);
	return fileDetails;
}

/**
 * covernt Object List to FileHeader List
 * @param list
 * @return
 * @throws ParseException
 */
private List<ExternalTransactionSummary> FileHeadersList(List list) throws ParseException
{
 logger.info("Converting Objects to FileHeader"+list.size());
 SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
 List<ExternalTransactionSummary> fileHeaders = new ArrayList<ExternalTransactionSummary>(list.size());
//System.out.println("232"+list.size());
 ExternalTransactionSummary fileHeader= null;
    Iterator iter = list.iterator();	      
    while (iter.hasNext())
    {   
    	fileHeader= new ExternalTransactionSummary();
    	Object[] obj = (Object[]) iter.next();
      
        fileHeader.setPYM02FIL((String)obj[1]);
        fileHeader.setPYM02USRU((String)obj[2]);
        
        //System.out.println(obj[3].toString());
        String string = obj[3].toString();
        logger.info(string);
        //System.out.println(sdf1);
    	java.util.Date date = sdfdate.parse(string);
    	logger.info(date);
    	fileHeader.setPYM02DATU(date);
        
        fileHeader.setPYM02TOT((Integer)obj[4]);
        fileHeader.setPYM02STS((String)obj[5]);
        fileHeader.setPYM02BID((String)obj[6]);
        
        fileHeaders.add(fileHeader);
        
    }
    logger.info("Converting Objects to FileHeader"+fileHeaders.size());
    return fileHeaders;
}/*

@Override
public FileData getFileDataById(Long fileDataId) {
	// TODO Auto-generated method stub
	return uploadDao.getFileDataById(fileDataId);
}

@Override
public Integer updateFileRecord(FileData fileData) {

	return uploadDao.updateFileRecord(fileData);
}
*/
@Override
public Integer updateStatus(String[] selectedIds, String status,ExternalTransactionSummary fileHeader) {
	return externalFundTransferDao.updateStatus(selectedIds,status,fileHeader);
	
}    
@Override
public List<ExternalTransactionSummary> getFileDetailsByStatus(String... status) {

	return externalFundTransferDao.getFileDetailsByStatus(status);
}

@Override
public String getMaxBatchId() {
	logger.info("line201=" );
	String batchId = externalFundTransferDao.getMaxBatchId();
//	logger.info("line203="+batchId+"new Batxc id" );
	//changes to handle "0" on 9th Sep
	batchId=(batchId!=null && !batchId.equals("0"))?batchId.substring(1):"1000000000"; 
//	logger.info("line205=" +batchId+"waseem");
	long parseLong = Long.parseLong(batchId);
	logger.info("line207=" );
	parseLong++;
	logger.info("line209=" );
//	System.out.println("207");
	return "P"+parseLong; 
}

@Override
public List<ExternalTransations> getFileTransactions(String batchId) {
	return externalFundTransferDao.getFileTransactions(batchId);
}

}