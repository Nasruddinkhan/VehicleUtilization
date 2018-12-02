package com.project.millatinventory.service;




import java.util.List;

import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.model.Vehicle;

public interface DataEntryService {
public void saveDataEntry(DataEntry dataEntry);
public List<DataEntry> getEntries();
public Integer updateDataEntry(DataEntry dataEntry);
public int deleteDataEntryById(int dataEntryId);
public DataEntry getDataEntryById(int dataEntryId);

public List<Vehicle> getVehicleNumber(Vehicle vehicleType);
DataEntry getDataEntryCriteria(DataEntry user);
public void saveExpenses(DataEntryExpense dataEntryExpense);
List<DataEntryExpense> getExpenseByDataEntryId(Integer dataEntryId);
public int deleteExpenseByDataEntryId(Integer dataEntryId);
List<DataEntryExpense> getExpenses();
/*public DataEntry getDataEntrySiteCriteria(DataEntry dataentry);*/
public List<DataEntry> getDataEntryByVehicleIdCriteria(Vehicle vehicle);
}
