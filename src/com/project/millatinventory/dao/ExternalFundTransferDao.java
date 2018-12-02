package com.project.millatinventory.dao;

import java.text.ParseException;
import java.util.List;

import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;
import com.project.millatinventory.model.ExternalTransactionSearch;
import com.project.millatinventory.model.ExternalTransactionSummary;
import com.project.millatinventory.model.ExternalTransations;


public interface ExternalFundTransferDao {

	//List<FileHeader> getFileDetails(Integer fileId);
   /* void save(Office office);
    Office getOfficeById(long id);
    Office getOfficeByName(String name);*/

	//List<FileData> getFileData(Integer fileId);

	void saveFileHeader(ExternalTransactionSummary fileHeader);

	List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSearch fileSearch) throws ParseException;

	List<ExternalTransactionSummary> getFileDetails(ExternalTransactionSummary fileHeader);

	//FileData getFileDataById(Long fileDataId);

	//Integer updateFileRecord( fileData);



	List<ExternalTransactionSummary> getFileDetailsByStatus(String... status);


	Integer updateStatus(String[] selectedIds, String status,
			ExternalTransactionSummary fileHeader);

	String getMaxBatchId();

	List<ExternalTransations> getFileTransactions(String batchId);

}

