package com.project.millatinventory.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.serviceimpl.InvalidDataException;

public interface UploadService {
	//List<FileHeader> getFileDetails(Integer fileId);
	List<FileData> getFileData(Integer fileId);
	
	
	void saveContents(MultipartFile file, Users user)
			throws InvalidDataException;
	List<FileHeader> getFileDetails(FileSearch fileSearch) throws ParseException;
	List<FileHeader> getFileDetails(FileHeader fileHeader);



	FileData getFileDataById(Long fileDataId);



	Integer updateFileRecord(FileData fileData);




	List<FileHeader> getFileDetailsByStatus(String... status);



	Integer updateStatus(Integer[] selectedIds, String status,
			FileHeader fileHeader);

	String getMaxBatchId();
}
