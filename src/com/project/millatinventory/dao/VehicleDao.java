package com.project.millatinventory.dao;

import java.util.List;

import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

public interface VehicleDao {

	public void saveVehicle(Vehicle vehicle);
	public List<Vehicle> getVehicle();
	public void updateVehicle(Vehicle vehicle);
	public int deleteVehicleById(int vehicleId);
	public Vehicle getVehicleById(int vehicleId);
	public Vehicle getVehicleCriteria(Vehicle vehicle);
	/*public DataEntry getDataEntryCriteria(DataEntry dataentry);*/
	List<Vehicle> getVehiclesByVehicleType(int vehicleType, Integer vendorId);
	List<Moter> getVehicleTypeByVendor(Vendors vendors);
	List<String> getExcavatorTypeByVendorAndVehicleType(int vendorId, int vehicleType);
}
