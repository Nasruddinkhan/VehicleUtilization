package com.project.millatinventory.service;

import java.util.List;

import com.project.millatinventory.model.Vendors;

public interface VendorsService {

	public void saveVendor(Vendors vendors);
	public List<Vendors> getVendors();
	public int deleteVendorById(int vendorId);
	public void updateVendor(Vendors vendors);
	public Vendors getVendorById(int vendorId);
	public Vendors getVendorCriteria(Vendors vendor);

}
