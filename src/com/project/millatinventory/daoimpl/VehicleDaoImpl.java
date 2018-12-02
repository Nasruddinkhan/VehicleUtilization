package com.project.millatinventory.daoimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.dao.VehicleDao;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;

@Repository("VehicleDao")
public class VehicleDaoImpl implements VehicleDao {
	private static final Logger logger = LoggerFactory.getLogger(VehicleDaoImpl.class);
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void saveVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stubst
		logger.info("VehicleDaoImpl.saveVehicle()");
		Session session = sessionFactory.getCurrentSession();
		logger.info("" + session);
		session.saveOrUpdate(vehicle);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehicle() {
		String hqlC = "from Vehicle ";/* order by VEHICLE_ID desc */
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		/* createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE); */
		return createQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> getVehiclesByVehicleType(int vehicleType,Integer vendorId) {
		String hqlC = "from Vehicle where status =:status and vehiclesType.m_Id=:vehiclesType and vendorType.vendorId=:vendorId";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		createQuery.setParameter("vehiclesType", vehicleType);
		createQuery.setParameter("vendorId", vendorId);
		return createQuery.list();
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"update Vehicle set vehicles_Type=:vehiclesType,vendorType=:vendorType,vehicles_Number=:vehiclesNumber,model_Number=:modelNumber,issue_Date=:issueDate,STATUS=:status where vehicleId=:vehicleId");
		query.setParameter("vehiclesType", vehicle.getVehiclesType());
		query.setParameter("vendorType", vehicle.getVendorType());
		query.setParameter("vehiclesNumber", vehicle.getVehiclesNumber());
		query.setParameter("modelNumber", vehicle.getModelNumber());
		query.setParameter("issueDate", vehicle.getIssueDate());
		query.setParameter("status", vehicle.getStatus());
		query.setParameter("vehicleId", vehicle.getVehicleId());

		query.executeUpdate();
		return;
	}

	@Override
	public int deleteVehicleById(int vehicleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Vehicle  where vehicleId=:vehicleId");
		query.setParameter("vehicleId", vehicleId);
		return query.executeUpdate();
	}

	@Override
	public Vehicle getVehicleById(int vehicleId) {
		String hqlQ = "from Vehicle u where u.id=:vehicleId";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlQ);
		createQuery.setInteger("vehicleId", vehicleId);
		return (Vehicle) createQuery.uniqueResult();
	}

	@Override
	public Vehicle getVehicleCriteria(Vehicle vehicle) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);

		if (vehicle.getVehiclesNumber() != null) {
			criteria.add(Expression.eq("vehiclesNumber", vehicle.getVehiclesNumber()));
		}
		if (vehicle.getVehicleId() != 0) {
			criteria.add(Expression.ne("vehicleId", vehicle.getVehicleId()));
		}
		// criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));

		Object uniqueResult = criteria.uniqueResult();
		logger.info("" + uniqueResult);
		if (null != uniqueResult)
			return (Vehicle) uniqueResult;
		else {
			return null;
		}
	}
	/*
	 * @Override public DataEntry getDataEntryCriteria(DataEntry dataentry) {
	 * Criteria criteria = sessionFactory.getCurrentSession()
	 * .createCriteria(DataEntry.class);
	 * 
	 * if(dataentry.getVehicleType()!=null){
	 * criteria.add(Expression.eq("vehiclesNumber",dataentry.getVehicleType())); }
	 * if( dataentry.get!=0){
	 * criteria.add(Expression.ne("vehicleId",vehicle.getVehicleId())); }
	 * //criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));
	 * 
	 * Object uniqueResult = criteria.uniqueResult(); logger.info(""+uniqueResult);
	 * if(null != uniqueResult) return (Vehicle) uniqueResult; else{ return null; }
	 * }
	 */
	@Override
	public List<Moter> getVehicleTypeByVendor(Vendors vendors) {
		String hqlC = "select distinct m from Vehicle,Moter m where m_Id= vehicles_Type and vendor_Type=:vendorId";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("vendorId", vendors.getVendorId());
		//createQuery.setParameter("vehiclesType", vehicleType);
		return createQuery.list();
	
	}
	@Override
	public List<String> getExcavatorTypeByVendorAndVehicleType(int vendorId,int vehicleType) {
		String hqlC = "SELECT DISTINCT type FROM Vehicle where vehicles_type=:vehicleType and vendor_Type=:vendorId";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hqlC);
		createQuery.setParameter("vehicleType", vehicleType);
		createQuery.setParameter("vendorId", vendorId);
		//createQuery.setParameter("vehiclesType", vehicleType);
		List<String> list = new ArrayList<String>(); 
				
		
			list=	createQuery.list();
			System.out.println(list);
			System.out.println(list.size());
			System.out.println(list.get(0)==null);
		return list.get(0)!=null?list: Collections.EMPTY_LIST;
	
	}
}
