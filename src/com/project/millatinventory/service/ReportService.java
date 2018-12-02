package com.project.millatinventory.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.project.millatinventory.controller.SearchBean;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Deal;
import com.project.millatinventory.model.Dormancy;
import com.project.millatinventory.model.Portfolio;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;


public interface ReportService {
	
	public List<Portfolio> getPortfolioDetails();

	public void generatePortfolioReport(Portfolio portfolio, String file) throws FileNotFoundException, DocumentException, MalformedURLException, IOException;

	
	
	public List<Dormancy> getDormancyLetterDetails();

	public void generateDormancyReport(Dormancy dormancy, String file) throws FileNotFoundException, DocumentException, MalformedURLException, IOException;

	Deal getDealbyRef(int refNo);

	void generateDealReport(Deal deal, String file) throws DocumentException, MalformedURLException, IOException;

	public List<List<Map<Object, Object>>> getMonthlyData();
	public List<List<Map<Object, Object>>> getYearlyData();
	List<DataEntry> GetDataEntry();
	List<Sites> GetSite();
	List<Vehicle> GetVehicles();

	public List<DataEntry> searchTrip(SearchBean searchBean) throws ParseException;

}
