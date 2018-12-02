package com.project.millatinventory.dao;


import java.util.List;

import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

	public interface VendorDao {
	public void saveVendor(Vendors vendors);
    public List<Vendors> getVendors();
    public int deleteVendorById(int vendorId);
   
    public void updateVendor(Vendors vendors);
	public Vendors getVendorById(int vendorId);
	public Vendors getVendorCriteria(Vendors vendor);

}
