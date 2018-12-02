package com.project.millatinventory.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.project.millatinventory.controller.SearchBean;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Deal;
import com.project.millatinventory.model.Dormancy;
import com.project.millatinventory.model.Portfolio;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

public interface ReportDao {
	public List<Portfolio> getPortfolioDetails();
	public List<Dormancy> getDormancyLetterDetails();
	
	Deal getDealbyRef(int refNo);
	
	List<List<Map<Object, Object>>> getYearlyData();
	List<List<Map<Object, Object>>> getMonthlyData();
	List<DataEntry> GetDataEntry();
	List<Vendors> GetVendors();
	List<Sites> GetSite();
	List<Vehicle> GetVehicles();
	List<DataEntry> searchTrip(SearchBean searchbean) throws ParseException;
}
