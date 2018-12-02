package com.project.millatinventory.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.dao.DataEntryDao;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Vehicle;
import com.sun.org.apache.bcel.internal.Constants;

@Repository("DataEntryDao")
public class DataEntryDaoImpl implements DataEntryDao {
	private static final Logger logger = LoggerFactory.getLogger(DataEntryDaoImpl.class);

	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void saveDataEntry(DataEntry dataEntry) {
		logger.info(""+dataEntry);
		sessionFactory.getCurrentSession().saveOrUpdate(dataEntry);
		return;
	}

	@Override
	public int deleteDataEntryById(int dataEntryId) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("Delete from DataEntry d where d.dataEntryId=:dataEntryId");
		/*query.setParameter("status", ApplicationConstants.STATUS.DELETED);*/
		query.setParameter("dataEntryId", dataEntryId);

		return query.executeUpdate();

	}

	@Override
	public DataEntry getDataEntryById(int userId) {
		String hqlQ = "from DataEntry u where u.id=:userId";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlQ);
		createQuery.setInteger("userId", userId);
		return (DataEntry) createQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataEntry> getEntries() {
		String hqlC = " from DataEntry c order by dataEntryId desc";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlC);
		//createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		return createQuery.list();
	}
	@Override
	public DataEntry getDataEntryCriteria(DataEntry user) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(DataEntry.class);
		
		if(user.getDataEntryId()!=null){
			criteria.add(Expression.eq("loginId",user.getDataEntryId()));
		}
		if(user.getDataEntryId()!=null && user.getDataEntryId()!=0){
			criteria.add(Expression.ne("id",user.getDataEntryId()));
		}
		//criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));
		
		Object uniqueResult = criteria.uniqueResult();
		logger.info(""+uniqueResult);
		if(null != uniqueResult)
			return (DataEntry) uniqueResult;
		else{
		return null;
		}
	}
	
	@Override
	public DataEntry getLastDieselTrip(DataEntry dataEntry) {
		
		org.hibernate.Session  session= sessionFactory.getCurrentSession();
				 Criteria criteria =  	session.createCriteria(DataEntry.class);	
		criteria.add(Expression.ne("diesel",""));
		criteria.add(Expression.ne("diesel","0"));
		criteria.add(Expression.eq("vehicle.vehicleId",dataEntry.getVehicle().getVehicleId()));		
		criteria.addOrder(Order.desc("dataEntryId"));
		criteria.setMaxResults(1);
		//criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));
		List<DataEntry> list = criteria.list();
		logger.info(""+list);
		System.out.println(list);
		session.flush();
		session.clear();
	  return  list.size()>0? list.get(0):null;
	  
	}

	@Override
	public Double getUsageFromLastDieselFill(DataEntry dataEntry) {
		org.hibernate.Session  session= sessionFactory.getCurrentSession();
		 Criteria criteria =  	session.createCriteria(DataEntry.class);	
		criteria.add(Restrictions.ge("dataEntryId",dataEntry.getDataEntryId()));
		criteria.add(Restrictions.eq("vehicle.vehicleId",dataEntry.getVehicle().getVehicleId()));
		
		if(ApplicationConstants.EXCAVATOR == dataEntry.getVehicleType().getM_Id()){
			criteria.setProjection(Projections.sum("difftime"));
		}
		else {
			criteria.setProjection(Projections.sum("distance"));
		}
		Object totalDistance =  criteria.uniqueResult();
		session.flush();
		session.clear();
		return Double.parseDouble(totalDistance.toString());	
	}

	@Override
	public void saveExpenses(DataEntryExpense expenseList) {
		System.out.println(expenseList);
		sessionFactory.getCurrentSession().saveOrUpdate(expenseList);
		
	}
	
	@Override
	public List<DataEntryExpense> getExpensesByDataEntryId(Integer dataEntryId) {
		String hqlC = " from DataEntryExpense d where dataEntryId = :dataEntryId ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("dataEntryId", dataEntryId);
		//createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		return createQuery.list();
	}

	@Override
	public int deleteExpenseByDataEntryId(Integer dataEntryId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Delete from DataEntryExpense d where d.dataEntryId=:dataEntryId");
		query.setParameter("dataEntryId", dataEntryId);
		return query.executeUpdate();	
	}

	@Override
	public List<Vehicle> getVehicleNumber(Vehicle vehicleType) {
		String hqlC = "select v.vehiclesNumber from Vehicle v where v.vehiclesType = :vehiclesType ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("vehiclesType", vehicleType);
		//createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		return createQuery.list();
	}

	@Override
	public List<DataEntryExpense> getExpenses() {
		String hqlC = "from DataEntryExpense ";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		//createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		return createQuery.list();
	}

	@Override
	public List<DataEntry> getDataEntryByVehicleIdCriteria(Vehicle vehicle) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataEntry.class);

		if (vehicle.getVehicleId() != 0) {
			criteria.add(Expression.eq("vehicle.vehicleId", vehicle.getVehicleId()));
		}
		List<DataEntry> uniqueResult = criteria.list();
		
			return  uniqueResult;
	
	}
/*	@Override
	public DataEntry getDataEntrySiteCriteria(DataEntry dataentry) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(DataEntry.class);
		
		if(dataentry.getSite()!=null){
			criteria.add(Expression.eq("siteid",Integer.parseInt(dataentry.getSite().toString())));
		}
		
		Object uniqueResult = criteria.uniqueResult();
		logger.info(""+uniqueResult);
		if(null != uniqueResult)
			return (DataEntry) uniqueResult;
		else{
		return null;
		}
	}*/
	}
