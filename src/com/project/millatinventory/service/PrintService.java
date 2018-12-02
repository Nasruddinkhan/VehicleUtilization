package com.project.millatinventory.service;




import java.util.List;

import com.project.millatinventory.model.ApplicantAccount;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;

public interface PrintService {
	public DetailLookup getApplicantById(String id);
	public void saveApplicantDetails(DetailInsert detailInsert);
	List<ApplicantAccount> getAccountTypeByAppId(String applicantID);
	public String getIban(String accountType);

	List<DetailInsert> getApplicants(DetailInsert detailInsert);
	public DetailInsert getSavedApplicantById(String id);
	Integer updateStatus(DetailInsert detailInsert);

}
