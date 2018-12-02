package com.project.millatinventory.daoimpl;



import java.util.List;

import javax.annotation.Resource;
import javax.mail.Session;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.dao.VendorDao;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

@Repository(value="VendorsDao")

public class VendorsDaoImpl implements VendorDao {
	private static final Logger logger = LoggerFactory.getLogger(VendorsDaoImpl.class);
	@Autowired
	  JdbcTemplate jdbcTemplate;


	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void saveVendor(Vendors Vendors) {

		//String sql = "insert into sites values(?,?,?,?,?)";
	 //   jdbcTemplate.update(sql, new Object[] { site.getSiteId(),site.getSiteName(),site.getSiteLocation(),site.getSiteManager(),site.getSiteSupervisor() });
		   org.hibernate.classic.Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(Vendors);
			}
	
	@Override
	public List<Vendors> getVendors() {
		String hqlC = "from Vendors ";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlC);
		List list = createQuery.list();
		return list;

	}

	@Override
	public int deleteVendorById(int vendorId) {
		org.hibernate.classic.Session session = sessionFactory.getCurrentSession();

		//Query query = session.createQuery("update Sites set STATUS=:status where siteId=:siteId");
		
		Query query = session.createQuery("delete from Vendors  where vendorId=:vendorId");
		
		//query.setParameter("status", ApplicationConstants.STATUS.DELETED);
		query.setParameter("vendorId", vendorId);

		return query.executeUpdate();
	}

	@Override
	public void updateVendor(Vendors vendorId) {
		org.hibernate.classic.Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Vendors set where vendorId=:vendorId");
		query.setParameter("vendorId", vendorId);
		return;
		
	}

	@Override
	public Vendors getVendorById(int vendorId) {
		String hqlQ = "from Vendors u where u.vendorId=:vendorId";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlQ);
		createQuery.setInteger("vendorId", vendorId);
		return (Vendors) createQuery.uniqueResult();
	}

	@Override
	public Vendors getVendorCriteria(Vendors vendor) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Vendors.class);
		
		if(vendor.getVendor_Name()!=null){
			criteria.add(Expression.eq("vendor_Name",vendor.getVendor_Name()));
		}
		if( vendor.getVendorId()!=0){
			criteria.add(Expression.ne("vendorId",vendor.getVendorId()));
		}
		//criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));
		
		Object uniqueResult = criteria.uniqueResult();
		logger.info(""+uniqueResult);
		if(null != uniqueResult)
			return (Vendors) uniqueResult;
		else{
		return null;
		}
	}
}


