package com.project.millatinventory.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.VehicleDao;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.VehicleService;
@Service("VehicleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class VehicleServiceImpl implements VehicleService {
	private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	
	@Autowired
VehicleDao vehicleDao;
	@Override
	public void saveVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		logger.info("serviseImpl");
		vehicleDao.saveVehicle(vehicle);
	}
	@Override
	public List<Vehicle> getVehicle() {
		// TODO Auto-generated method stub
		return vehicleDao.getVehicle();
	}
	@Override
	public List<Vehicle> getVehiclesByVehicleType(int vehicleType,Integer vendorId) {
		// TODO Auto-generated method stub
		return vehicleDao.getVehiclesByVehicleType(vehicleType,vendorId);
	}
	
	@Override
	public void updateVehicle(Vehicle vehicle) {
		vehicleDao.updateVehicle(vehicle);
		return ;
	}
	@Override
	public int deleteVehicleById(int vehicleId) {
		
		return vehicleDao.deleteVehicleById(vehicleId);
	}
	@Override
	public Vehicle getVehicleById(int vehicleId) {
		
		return vehicleDao.getVehicleById(vehicleId);
	}
	@Override
	public Vehicle getVehicleCriteria(Vehicle vehicle) {
		return vehicleDao.getVehicleCriteria(vehicle);
	}
	@Override
	public List<Moter> getVehicleTypeByVendor(Vendors vendor) {
		return vehicleDao.getVehicleTypeByVendor(vendor);
	}
	@Override
	public List<String> getExcavatorTypeByVendorAndVehicleType(int vendorId, int vehicleType) {
		// TODO Auto-generated method stub
		return vehicleDao.getExcavatorTypeByVendorAndVehicleType(vendorId,vehicleType);
	}

}
