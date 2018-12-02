package com.project.millatinventory.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
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
import com.project.millatinventory.dao.UploadDao;
import com.project.millatinventory.model.ExcelReader;
import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.UploadService;

@Service("UploadService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class UploadServiceImpl implements UploadService{
@Autowired
UploadDao uploadDao;


final static Logger logger = Logger.getLogger(UploadServiceImpl.class);
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
public List<FileHeader> getFileDetails(FileHeader fileHeader) {
	
	return uploadDao.getFileDetails(fileHeader);
}

@Override
public List<FileData> getFileData(Integer fileId) {
	// TODO Auto-generated method stub
	return uploadDao.getFileData(fileId);
}

@Override
public void saveContents(MultipartFile file,Users user) throws InvalidDataException{
	InputStream inputStream =null; 
	try{	        
        String fileName = file.getOriginalFilename();
        String maxBatchId = getMaxBatchId();
        logger.info("maxBatchId"+maxBatchId);
        FileHeader fileHeader= new FileHeader();
       // fileHeader.setFileId(1);
        fileHeader.setBatchId(maxBatchId);
        fileHeader.setCreatedBy(user.getLoginId());
        fileHeader.setStatus(ApplicationConstants.FILE_STATUS.INITIATED);
        fileHeader.setFileName(fileName); 
         ExcelReader excelReader= new ExcelReader();         
         inputStream = file.getInputStream();
         List<FileData> fileDataList = excelReader.readExcelFile(fileHeader, inputStream);
		 System.out.println(fileDataList.size());

         //fileHeader.setFileName(fileName);
         Calendar instance = Calendar.getInstance();
         instance.setTime(new java.util.Date());
         fileHeader.setUploadDate(new Date(instance.getTimeInMillis()));
         fileHeader.setUploadTime(new Date(instance.getTimeInMillis()));
         fileHeader.setRecordCount(fileDataList.size());
         fileHeader.setFileData(fileDataList);
         uploadDao.saveFileHeader(fileHeader);
        
     }
	 catch(InvalidDataException e){
		 
         throw e;
     }
	 catch(Exception e){
		 e.printStackTrace();
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

@Override
public List<FileHeader> getFileDetails(FileSearch fileSearch) throws ParseException {
	List fileDetails = uploadDao.getFileDetails(fileSearch);
	return FileHeadersList(fileDetails);
}

/**
 * covernt Object List to FileHeader List
 * @param list
 * @return
 * @throws ParseException
 */
private List<FileHeader> FileHeadersList(List list) throws ParseException
{
 logger.info("Converting Objects to FileHeader"+list.size());
 SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
 List<FileHeader> fileHeaders = new ArrayList<FileHeader>(list.size());
//System.out.println("232"+list.size());
 FileHeader fileHeader= null;
    Iterator iter = list.iterator();	      
    while (iter.hasNext())
    {   
    	fileHeader= new FileHeader();
    	Object[] obj = (Object[]) iter.next();
        fileHeader.setFileId((Integer)obj[0]);
        fileHeader.setFileName((String)obj[1]);
        fileHeader.setCreatedBy((String)obj[2]);
        
        //System.out.println(obj[3].toString());
        String string = obj[3].toString();
        logger.info(string);
        //System.out.println(sdf1);
    	java.util.Date date = sdfdate.parse(string);
    	logger.info(date);
    	fileHeader.setUploadDate(date);
        
        fileHeader.setRecordCount((Integer)obj[4]);
        fileHeader.setStatus((String)obj[5]);
        fileHeader.setBatchId((String)obj[6]);
        
        fileHeaders.add(fileHeader);
        
    }
    logger.info("Converting Objects to FileHeader"+fileHeaders.size());
    return fileHeaders;
}

@Override
public FileData getFileDataById(Long fileDataId) {
	// TODO Auto-generated method stub
	return uploadDao.getFileDataById(fileDataId);
}

@Override
public Integer updateFileRecord(FileData fileData) {

	return uploadDao.updateFileRecord(fileData);
}

@Override
public Integer updateStatus(Integer[] selectedIds, String status,FileHeader fileHeader) {
	return uploadDao.updateStatus(selectedIds,status,fileHeader);
	
}    
@Override
public List<FileHeader> getFileDetailsByStatus(String... status) {

	return uploadDao.getFileDetailsByStatus(status);
}

@Override
public String getMaxBatchId() {
	String batchId = uploadDao.getMaxBatchId();
	batchId=batchId!=null?batchId.substring(1):"1000000000"; 
	long parseLong = Long.parseLong(batchId);
	parseLong++;
	
	return "P"+parseLong; 
}

}