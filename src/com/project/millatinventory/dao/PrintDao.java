package com.project.millatinventory.dao;

import java.util.List;

import com.project.millatinventory.model.ApplicantAccount;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;

public interface PrintDao {

public void saveApplicantDetails(DetailInsert detailInsert);
public DetailLookup getApplicantById(String id);
List<ApplicantAccount> getAccountTypeByAppId(String applicantID);

public String getIban(String accountType);
List<DetailInsert> getApplicants(DetailInsert detailInsert);
public DetailInsert getSavedApplicantById(String id);
Integer updateStatus(DetailInsert detailInsert);

}
