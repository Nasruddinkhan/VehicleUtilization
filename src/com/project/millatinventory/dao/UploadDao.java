package com.project.millatinventory.dao;

import java.text.ParseException;
import java.util.List;

import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;


public interface UploadDao {

	//List<FileHeader> getFileDetails(Integer fileId);
   /* void save(Office office);
    Office getOfficeById(long id);
    Office getOfficeByName(String name);*/

	List<FileData> getFileData(Integer fileId);

	void saveFileHeader(FileHeader fileHeader);

	List<FileHeader> getFileDetails(FileSearch fileSearch) throws ParseException;

	List<FileHeader> getFileDetails(FileHeader fileHeader);

	FileData getFileDataById(Long fileDataId);

	Integer updateFileRecord(FileData fileData);



	List<FileHeader> getFileDetailsByStatus(String... status);


	Integer updateStatus(Integer[] selectedIds, String status,
			FileHeader fileHeader);

	String getMaxBatchId();
}

