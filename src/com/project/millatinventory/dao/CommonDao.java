package com.project.millatinventory.dao;

import java.util.List;
import java.util.Map;

import com.project.millatinventory.model.ApplicantTypeLookup;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Department;
import com.project.millatinventory.model.Expenses;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

public interface CommonDao {

	List<Department> getDepartments();

	List<ApplicantTypeLookup> getApplicantTypeLookup();
	List<Sites> getSites();
	List<Moter> getmoter();
	List<Vendors> getVendor();
	List<Vendors> getAllvendors();
	List<Vehicle> getVehicleNum();
    Integer getDateEntryId();
	List<Expenses> getExpenses();
	
}
