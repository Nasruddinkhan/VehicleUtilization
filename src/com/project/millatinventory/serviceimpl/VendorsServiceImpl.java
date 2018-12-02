package com.project.millatinventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.VendorDao;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.VendorsService;
@Repository(value="VendorsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class VendorsServiceImpl implements VendorsService{
  @Autowired
   private VendorDao vendorDao;
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  
  public void saveVendor(Vendors vendors) {
		
	  vendorDao.saveVendor(vendors);
	  System.out.println("VendorsServiceImpl.saveVendor()+vendorSirvice");
		
	}
    @Override
     public List<Vendors> getVendors() {
	
	return  vendorDao.getVendors();
}
	@Override
	public int deleteVendorById(int vendorId) {
		
		return vendorDao.deleteVendorById(vendorId);
	}
	@Override
	public void updateVendor(Vendors vendors) {
		
		
	}
	@Override
	public Vendors getVendorById(int vendorId) {
		
		return vendorDao.getVendorById(vendorId);
	}
	@Override
	public Vendors getVendorCriteria(Vendors vendor) {
		
		return vendorDao.getVendorCriteria(vendor);
	}
 
   
}


