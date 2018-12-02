package com.project.millatinventory.dao;

import java.util.List;

import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Vehicle;

public interface DataEntryDao {
public void saveDataEntry(DataEntry dataEntry);
public int deleteDataEntryById(int dataEntryId);
public DataEntry getDataEntryById(int dataEntryId);
public List<DataEntry> getEntries();
public List<Vehicle> getVehicleNumber(Vehicle vehicleType);
DataEntry getDataEntryCriteria(DataEntry user);
DataEntry getLastDieselTrip(DataEntry dataEntry);
public Double getUsageFromLastDieselFill(DataEntry d);
public void saveExpenses(DataEntryExpense dataEntryExpense);
List<DataEntryExpense> getExpensesByDataEntryId(Integer dataEntryId);
public int deleteExpenseByDataEntryId(Integer dataEntryId);
List<DataEntryExpense> getExpenses();
/*public DataEntry getDataEntrySiteCriteria(DataEntry dataEntry);*/
public List<DataEntry> getDataEntryByVehicleIdCriteria(Vehicle vehicle);
}
