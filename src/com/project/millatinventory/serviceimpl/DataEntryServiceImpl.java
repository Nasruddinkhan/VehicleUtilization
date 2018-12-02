package com.project.millatinventory.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.DataEntryDao;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.service.DataEntryService;
@Service("DataEntryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class DataEntryServiceImpl implements DataEntryService{
	private static final Logger logger = LoggerFactory.getLogger(DataEntryServiceImpl.class);

	@Autowired
	private DataEntryDao dataEntryDao;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	@Override
	public void saveDataEntry(DataEntry dataEntry) {
		logger.info("CustomerServiceImpl.addNewCustomer() save ");
		
		//calculateAvg(dataEntry);
		
		//dataEntry.setExcavatorType();
		//dataEntry.getVendorType().getVendorId()
		//dataEntry.getVehicleType().getM_Id()
		//dataEntry.getVehicle().getVehiclesNumber()
		Vehicle excavatorType = dataEntry.getExcavatorType();
		if("".equals(excavatorType.getType())) {
				excavatorType.setType(null);
		}
		dataEntryDao.saveDataEntry(dataEntry);
		return ;
	}

	/*private void calculateAvg(DataEntry dataEntry) {
		if(!dataEntry.getDiesel().isEmpty() && !dataEntry.getDiesel().equals("0")) {
			DataEntry lastDieselTrip = dataEntryDao.getLastDieselTrip(dataEntry);
			Optional<DataEntry> lastDieselTripObj = Optional.ofNullable(lastDieselTrip);
			logger.info("lastDieselTrip="+lastDieselTripObj);
			//System.out.println(lastDieselTripObj);
			if(lastDieselTripObj.isPresent()){
				logger.info("Last Trip found="+lastDieselTripObj.get().getDataEntryId());
				Double avg=0.0;
				Double distanceFromLastDieselFill = dataEntryDao.getUsageFromLastDieselFill(lastDieselTripObj.get());
				double lastFillDiesel = Double.parseDouble(lastDieselTrip.getDiesel());
				avg=distanceFromLastDieselFill/lastFillDiesel;
				logger.info("Average "+avg);
				dataEntry.setAvg(avg);	
			}
		}
		logger.info("Data Ebtry="+dataEntry);
	}*/

	@Override
	public List<DataEntry> getEntries() {

		return dataEntryDao.getEntries();
	}

	@Override
	public Integer updateDataEntry(DataEntry user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteDataEntryById(int userId) {
		return dataEntryDao.deleteDataEntryById(userId);	

	}

	@Override
	public DataEntry getDataEntryById(int userId) {
		return dataEntryDao.getDataEntryById(userId);
	}

	@Override
	public DataEntry getDataEntryCriteria(DataEntry user) {
		return dataEntryDao.getDataEntryCriteria(user);
	}

	@Override
	public void saveExpenses(DataEntryExpense dataEntryExpense) {
		
		dataEntryDao.saveExpenses(dataEntryExpense);
		
	}
	@Override
	public List<DataEntryExpense> getExpenseByDataEntryId(Integer dataEntryID) {
		return dataEntryDao.getExpensesByDataEntryId(dataEntryID);
	}

	@Override
	public int deleteExpenseByDataEntryId(Integer dataEntryId) {
		// TODO Auto-generated method stub
		return dataEntryDao.deleteExpenseByDataEntryId(dataEntryId);
	}

	@Override
	public List<Vehicle> getVehicleNumber(Vehicle vehicleType) {
		// TODO Auto-generated method stub
		return dataEntryDao.getVehicleNumber(vehicleType);
	}

	@Override
	public List<DataEntryExpense> getExpenses() {
		// TODO Auto-generated method stub
		return dataEntryDao.getExpenses();
	}

	@Override
	public List<DataEntry> getDataEntryByVehicleIdCriteria(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return dataEntryDao.getDataEntryByVehicleIdCriteria(vehicle);
	}

	/*@Override
	public DataEntry getDataEntrySiteCriteria(DataEntry dataentry) {
		// TODO Auto-generated method stub
		return dataEntryDao.getDataEntrySiteCriteria(dataentry);
	}
*/

}

