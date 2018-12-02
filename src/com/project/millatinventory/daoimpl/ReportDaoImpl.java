package com.project.millatinventory.daoimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.controller.SearchBean;
import com.project.millatinventory.dao.ReportDao;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Deal;
import com.project.millatinventory.model.Dormancy;
import com.project.millatinventory.model.Portfolio;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

@Repository("Portfolio,Dormancy")
public class ReportDaoImpl implements ReportDao {
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	Logger logger = Logger.getLogger(StudentDaoImpl.class);

	@Override
	public Deal getDealbyRef(int refNo) {
		logger.info("ReportDaoImpl.getDeal");
		String hqlC="from Deal where refNo=:refNo";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("refNo", refNo);
		Deal deal = (Deal) createQuery.uniqueResult();
		logger.info("Deal="+deal);
		return deal;
	
	}
	@Override
	public List<Portfolio> getPortfolioDetails() {
		logger.info("ReportDaoImpl.getPortfolioDetails");
		String hqlC="from Portfolio ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		List<Portfolio> list = createQuery.list();
		logger.info("list="+list.size());
		return list;
	
	}
	
	@Override
	public List<Dormancy> getDormancyLetterDetails() {
		logger.info("ReportDaoImpl.getDormancyLetterDetails");
		String hqlC="from Dormancy ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		List<Dormancy> list = createQuery.list();
		logger.info("list="+list.size());
		return list;
	
	}

	@Override
	public List<List<Map<Object, Object>>> getYearlyData() {
		return CanvasjsChartData.getYearlyData();
	}
	@Override
	public List<List<Map<Object, Object>>> getMonthlyData() {
		return CanvasjsChartData.getMonthlyData();
	}
	@Override
	public List<DataEntry> GetDataEntry() {
		String hqlC="from DataEntry ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		List<DataEntry> list = createQuery.list();
		logger.info("list="+list.size());
		return list;
	}
	
	@Override
	public List<Vendors> GetVendors() {
		String hqlC="from Vendors v";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		return createQuery.list();
	}
	@Override
	public List<Sites> GetSite() {
		String hqlC="from Sites s";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		return createQuery.list();
	}
	@Override
	public List<Vehicle> GetVehicles() {
		String hqlC="from Vehicle v";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		return createQuery.list();
	}

	@Override
	public List<DataEntry> searchTrip(SearchBean searchbean) throws ParseException {
		//String hqlC="from DataEntry ";
		Criteria criteria= sessionFactory.getCurrentSession().createCriteria(DataEntry.class,"de");
	
		if(!searchbean.getEntryFromDate().equals("") && !searchbean.getEntryToDate().equals("")) {
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date fromDate = sdf1.parse(searchbean.getEntryFromDate());
			java.util.Date toDate = sdf1.parse(searchbean.getEntryToDate());
			logger.info("From Date=" + fromDate);
			logger.info("To Date=" + toDate);
					
			criteria.add(Restrictions.between("entryDate",fromDate,toDate));
	}
		if(searchbean.getVendorType()!=0) {
			
			
	//		Criteria c=  criteria.createAlias("de.vehicle", "veh");
			criteria.add(Restrictions.eq("vendorType.vendorId", searchbean.getVendorType()));
			
	      }
	
		
		/*if(searchbean.getVendorType()!=0) {
			criteria.add(Restrictions.eq("VendorType.vendorId", searchbean.getVendorType()));
	}*/
		
		
		if(searchbean.getSiteId()!=0) {
			criteria.add(Restrictions.eq("site.siteId", searchbean.getSiteId()));
	      }
	
		
	/*	if(searchbean.getSiteId()!=0) {
			criteria.add(Restrictions.eq("site.siteId", searchbean.getSiteId()));
	}*/
		if(searchbean.getVehicleType()!=0) {
			criteria.add(Restrictions.eq("vehicleType.m_Id", searchbean.getVehicleType()));
	}
		if(searchbean.getVehicleNumber()!=0) {
				criteria.add(Restrictions.eq("vehicle.vehicleId", searchbean.getVehicleNumber()));
		}
		System.out.println(criteria);
		List list = criteria.list();
		return list;
	}
}
