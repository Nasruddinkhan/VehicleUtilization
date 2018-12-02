package com.project.millatinventory.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.dao.CommonDao;
import com.project.millatinventory.model.ApplicantTypeLookup;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Department;
import com.project.millatinventory.model.Expenses;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

@Repository("CommonDao")
public class CommonDaoImpl implements CommonDao {
	//Changes for oracle connection 
	@Autowired
	@Qualifier(value="mySQLSessionFactory")
	private SessionFactory sessionFactory;

	/*@Autowired
	@Qualifier(value="oracleSessionFactory")
	private SessionFactory sessionFactory1;
	*/
	@Override
	public List<Department> getDepartments() {		
		String hqlC="from Department c";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);		
		return createQuery.list();
	}
	
	@Override
	public List<ApplicantTypeLookup> getApplicantTypeLookup() {		
		String hqlC="from ApplicantTypeLookup c";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);		
		return createQuery.list();
	}
	@Override
	public List<Sites> getSites() {		
		String hqlC="from Sites s where s.status=:status order by siteName";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);	
		createQuery.setParameter("status", "ACTIVE");	
		return createQuery.list();
	}

	@Override
	public List<Moter> getmoter() {
		String hqlC="from Moter m order by m_Name";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);		
		return createQuery.list();
	}
	@Override
	public List<Expenses> getExpenses() {
		String hqlC="from Expenses m order by expenseName";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);		
		return createQuery.list();
	}

	@Override
	public List<Vehicle> getVehicleNum() {
		String hqlC="from Vehicle v where v.status=:status";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);	
		createQuery.setParameter("status", "ACTIVE");
		System.out.println(createQuery.list());
		return createQuery.list();
	}

	@Override
	public Integer getDateEntryId() {
		String hqlC="select max(E.dataEntryId)from DataEntry E";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);	
		System.out.println("79="+createQuery);
		return (Integer)createQuery.uniqueResult();
	}

	@Override
	public List<Vendors> getVendor() {
		String hqlC="from Vendors N where N.status=:status";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("status", "ACTIVE");
		return createQuery.list();
	}

	@Override
	public List<Vendors> getAllvendors() {
		String hqlC="from Vendors N";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		return createQuery.list();
	}

	

}
