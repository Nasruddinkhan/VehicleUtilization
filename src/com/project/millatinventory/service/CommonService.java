package com.project.millatinventory.service;




import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.project.millatinventory.model.ApplicantTypeLookup;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Expenses;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

public interface CommonService {
public Map<Integer, String> getCities();
public Map<Integer, String> getStates();
public List getDepartments();
public Map<String, String> getGenders();
public List<Sites> getSites();
public List<Moter> getmoter();
public List<Vendors> getVendor();
public List<Vendors> getAllvendors();
public Map getRoles();
public List<ApplicantTypeLookup> getApplicantTypeLookup();
public List<String> getHours() ;
public List<String> getMinutes();
public List<Vehicle> getVehicleNum();
List<Expenses> getExpenses();
Integer getDateEntryId();
public int db_Backup(String DBName, String StorageLocation, String sqlDump, String userName, String Pass);
public int db_Restore(String pathsqlconsole, String UserName, String Password, String source);
}
