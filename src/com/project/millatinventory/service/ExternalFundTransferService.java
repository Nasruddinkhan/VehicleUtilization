package com.project.millatinventory.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.ExternalTransactionSearch;
import com.project.millatinventory.model.ExternalTransactionSummary;
import com.project.millatinventory.model.ExternalTransations;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.serviceimpl.InvalidDataException;

public interface ExternalFundTransferService {
	//List<FileHeader> getFileDetails(Integer fileId);
	//List<FileData> getFileData(Integer fileId);
		
	void saveContents(MultipartFile file, Users user)
			throws InvalidDataException;
	List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSearch fileSearch) throws ParseException;
	List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSummary fileHeader);
	//FileData getFileDataById(Long fileDataId);
	//Integer updateFileRecord(FileData fileData);
	List<ExternalTransactionSummary> getFileDetailsByStatus(String... status);
	Integer updateStatus(String[] selectedIds, String status,
			ExternalTransactionSummary fileHeader);

	String getMaxBatchId();
	List<ExternalTransations> getFileTransactions(String batchId);
}
