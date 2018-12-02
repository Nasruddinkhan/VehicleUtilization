package com.project.millatinventory.daoimpl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.dao.UploadDao;
import com.project.millatinventory.model.FileData;
import com.project.millatinventory.model.FileHeader;
import com.project.millatinventory.model.FileSearch;

@Repository("UploadDao")
public class UploadDaoImpl implements UploadDao {

	final static Logger logger = Logger.getLogger(UploadDaoImpl.class);
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<FileHeader> getFileDetails(FileHeader fileHeader) {
		Query query = null;
		Session session = null;
		List list = null;
		try {

			logger.info("getFileDetails");
			session = sessionFactory.openSession();

			String sql = "from FileHeader ";
			if (null != fileHeader.getFileName()
					&& !fileHeader.getFileName().isEmpty()) {
				sql += " where fileName=:fileName ";
			}

			if (null != fileHeader.getCreatedBy()
					&& !fileHeader.getCreatedBy().isEmpty()) {
				if (sql.contains("where")) {
					sql += " and createdBy=:createdBy ";
				} else {
					sql += " where createdBy=:createdBy ";
				}
			}
			if (null != fileHeader.getFileId() && 0 != fileHeader.getFileId()) {
				if (sql.contains("where")) {
					sql += " and fileId=:fileId  ";
				} else {
					sql += " where fileId=:fileId  ";
				}
			}
			sql += " order by fileId desc";
			query = session.createQuery(sql);
			if (null != fileHeader.getFileName()
					&& !fileHeader.getFileName().isEmpty()) {
				query.setParameter("fileName", fileHeader.getFileName());
			}
			if (null != fileHeader.getCreatedBy()
					&& !fileHeader.getCreatedBy().isEmpty()) {
				query.setParameter("createdBy", fileHeader.getCreatedBy());
			}
			if (null != fileHeader.getFileId() && 0 != fileHeader.getFileId()) {
				query.setParameter("fileId", fileHeader.getFileId());
			}
			logger.info("HQL=" + query);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List getFileDetails(FileSearch fileSearch) throws ParseException {

		Session session = null;
		Criteria criteria = null;
		// Query query = null;
		List list = null;
		try {
			session = sessionFactory.openSession();
			logger.info("SESSSION=" + session);
			criteria = session.createCriteria(FileHeader.class, "file");

			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("fileId"));
			projList.add(Projections.property("fileName"));
			projList.add(Projections.property("createdBy"));
			projList.add(Projections.property("uploadDate"));
			projList.add(Projections.property("recordCount"));
			projList.add(Projections.property("status"));
			projList.add(Projections.property("batchId"));
			criteria.setProjection(Projections.distinct(projList));

			Criterion criteriaEmpId = null;
			Criterion criteriaMobileNo = null;
			Criterion criteriaMonth = null;
			Criterion criteriaUploadDate = null;

			boolean aliasAdded = false;
			if (fileSearch.getEmpId() != null
					&& !fileSearch.getEmpId().isEmpty()) {
				logger.info("EMPID=" + fileSearch.getEmpId());
				criteria.createAlias("file.FileData", "fileData");
				aliasAdded = true;
				// criteria.add(Restrictions.eq("fileData.empId",fileSearch.getEmpId()));

				criteriaEmpId = Restrictions.eq("fileData.empId",
						fileSearch.getEmpId());
			}
			if (fileSearch.getMobileNumber() != null
					&& !fileSearch.getMobileNumber().isEmpty()) {
				logger.info("Mobile No.=" + fileSearch.getEmpId());
				if (!aliasAdded) {
					criteria.createAlias("file.FileData", "fileData");
					aliasAdded = true;
				}
				/*
				 * criteria.add(Restrictions.eq("fileData.mobileNo",fileSearch.
				 * getMobileNumber()));
				 */
				criteriaMobileNo = Restrictions.eq("fileData.mobileNo",
						fileSearch.getMobileNumber());

			}
			if (fileSearch.getMonth() != null
					&& !fileSearch.getMonth().equals("0")
					&& !fileSearch.getMonth().isEmpty()) {
				logger.info("Month=" + fileSearch.getMonth());
				if (!aliasAdded) {
					criteria.createAlias("file.FileData", "fileData");
					aliasAdded = true;
				}
				/*
				 * criteria.add(Restrictions.eq("fileData.month",
				 * fileSearch.getMonth()));
				 */
				criteriaMonth = Restrictions.eq("fileData.month",
						fileSearch.getMonth());
			}
			if (fileSearch.getFromDate() != null
					&& !fileSearch.getFromDate().isEmpty()
					&& fileSearch.getToDate() != null
					&& !fileSearch.getToDate().isEmpty()) {
				logger.info("From Date=" + fileSearch.getFromDate());
				logger.info("To Date=" + fileSearch.getToDate());

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

				java.util.Date fromDate = sdf1.parse(fileSearch.getFromDate());
				java.util.Date toDate = sdf1.parse(fileSearch.getToDate());

				Calendar cal = Calendar.getInstance();
				cal.setTime(toDate);
				cal.add(Calendar.DATE, 1);
				toDate = cal.getTime();
				logger.info("From Date=" + fromDate);
				logger.info("To Date=" + toDate);
				/*
				 * criteria.add(Restrictions.between("uploadDate", fromDate,
				 * toDate));
				 */

				criteriaUploadDate = Restrictions.between("uploadDate",
						fromDate, toDate);
			}

			Disjunction disjunction = Restrictions.disjunction();
			if (null != criteriaEmpId)
				disjunction.add(criteriaEmpId);
			if (null != criteriaMobileNo)
				disjunction.add(criteriaMobileNo);
			if (null != criteriaMonth)
				disjunction.add(criteriaMonth);
			if (null != criteriaUploadDate)
				disjunction.add(criteriaUploadDate);

			criteria.add(disjunction);

			criteria.add(Restrictions.eq("createdBy", fileSearch.getCreatedBy()));
			criteria.addOrder(Order.desc("fileId"));

			list = criteria.list();

		} finally {
			session.close();
		}
		logger.info(list);
		return list;
	}

	@Override
	public List<FileData> getFileData(Integer fileId) {
		Session session = null;
		Query query = null;
		List list = null;
		try {

			session = sessionFactory.openSession();

			String sql = " from FileData f where f.fileHeader.fileId=:fileId order by id";
			query = session.createQuery(sql);
			query.setParameter("fileId", fileId);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void saveFileHeader(FileHeader fileHeader) {
		System.out.println(fileHeader);
		Session session = null;
		try{
		//Changes for oracle connection
		session = sessionFactory.openSession();
		//getting transaction object from session object
		Transaction transaction = session.beginTransaction();

		session.save(fileHeader);
		//session.fl
		System.out.println("Inserted Successfully");
		transaction.commit();
		//session.close();
		
		
		} finally {
			session.close();
		}
		
	}

	/*
	 * public Session currentSession(){ return
	 * sessionFactory.getCurrentSession(); } public void save(Office office){
	 * currentSession().save(office); currentSession().flush(); }
	 * 
	 * public Office getOfficeById(long id){ return
	 * (Office)currentSession().get(Office.class, id); } public Office
	 * getOfficeByName(String name){ Query
	 * query=currentSession().createQuery("from my_office where name = '"
	 * +name+"'"); List<Office> list = query.list(); return (Office)list.get(0);
	 * }
	 */

	/*public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date fromDate = sdf1.parse("2017-05-14 13:02:07.0");
		System.out.println(fromDate);
	}
*/
	@Override
	public FileData getFileDataById(Long fileDataId) {
		Session session = null;
		Query query = null;
		FileData fileData = null;
		try {
			session = sessionFactory.openSession();

			String sql = " from FileData f where f.id=:id order by id";
			query = session.createQuery(sql);
			query.setParameter("id", fileDataId);
			fileData = (FileData) query.uniqueResult();
		} finally {
			session.close();
		}
		return fileData;
	}

	@Override
	public Integer updateFileRecord(FileData fileData) {
		Session session = null;
		Query query = null;
		int count = 0;
		try {
			logger.info("Start updateFileRecord");
			session = sessionFactory.openSession();
			query = session
					.createQuery("update FileData set empId=:empid, mobileNo=:mobileNo, companyCode=:companyCode ,"
							+ " bankAccount=:bankAccount, nameAddress=:nameAddress, month=:month ,salaryDetail1=:salaryDetail1,"
							+ " salaryDetail2=:salaryDetail2, ifscCode=:ifscCode , currency=:currency,bonusAmount=:bonusAmount ,"
							+ " deductionAmount=:deductionAmount where id=:id  ");
			query.setParameter("empid", fileData.getEmpId());
			query.setParameter("mobileNo", fileData.getMobileNo());
			query.setParameter("companyCode", fileData.getCompanyCode());
			query.setParameter("bankAccount", fileData.getBankAccount());
			query.setParameter("nameAddress", fileData.getNameAddress());
			query.setParameter("month", fileData.getMonth());
			query.setParameter("salaryDetail1", fileData.getSalaryDetail1());
			query.setParameter("salaryDetail2", fileData.getSalaryDetail2());
			query.setParameter("ifscCode", fileData.getIfscCode());
			query.setParameter("currency", fileData.getCurrency());
			query.setParameter("bonusAmount", fileData.getBonusAmount());
			query.setParameter("deductionAmount", fileData.getDeductionAmount());
			query.setParameter("id", fileData.getId());
			logger.info("update Query" + query);
			count = query.executeUpdate();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public Integer updateStatus(Integer[] selectedIds, String status,
			FileHeader fileHeader) {
		Session session = null;
		Query query = null;
		int count = 0;
		String hql = "";
		try {
			logger.info(selectedIds + " updateStatus to " + status);
			session = sessionFactory.openSession();
			hql = " update FileHeader set status=:status ";
			if (null != fileHeader && null != fileHeader.getComments()
					&& !fileHeader.getComments().isEmpty()) {
				hql += " , comments=:comments ";
			}
			if (null != fileHeader && null != fileHeader.getCheckerDate()) {
				hql += " , checkerDate=:checkerDate ";
			}
			if (null != fileHeader && null != fileHeader.getCheckerId()
					&& !fileHeader.getCheckerId().isEmpty()) {
				hql += " , checkerId=:checkerId ";
			}
			if (null != fileHeader && null != fileHeader.getCheckerTime()) {
				hql += " , checkerTime=:checkerTime ";
			}
			hql += " where id in (:ids)";

			query = session.createQuery(hql);

			query.setParameter("status", status);
			if (null != fileHeader && null != fileHeader.getComments()
					&& !fileHeader.getComments().isEmpty()) {
				query.setParameter("comments", fileHeader.getComments());
			}
			if (null != fileHeader && null != fileHeader.getCheckerDate()) {
				query.setParameter("checkerDate", fileHeader.getCheckerDate());
			}
			if (null != fileHeader && null != fileHeader.getCheckerId()
					&& !fileHeader.getCheckerId().isEmpty()) {
				query.setParameter("checkerId", fileHeader.getCheckerId());
			}
			if (null != fileHeader && null != fileHeader.getCheckerTime()) {
				query.setParameter("checkerTime" ,fileHeader.getCheckerTime());
			}
			query.setParameterList("ids", selectedIds);

			logger.info("update Query" + query);
			count = query.executeUpdate();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public List<FileHeader> getFileDetailsByStatus(String... status) {
		Query query = null;
		Session session = null;
		List list = null;
		try {

			logger.info("getFileDetails");
			session = sessionFactory.openSession();

			String sql = "from FileHeader where status in(:status) order by fileId desc";
			query = session.createQuery(sql);

			query.setParameterList("status", status);

			logger.info("HQL=" + query);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String getMaxBatchId() {

		Session session = null;
		String maxBatchId = null;
		try {

			logger.info("getFileDetails");
			session = sessionFactory.openSession();
			System.out.println(session);
			// String sql = "SELECT IFNULL(MAX(batchId),0) FROM FILE_HEADER ";
			Criteria criteria = session.createCriteria(FileHeader.class)
					.setProjection(Projections.max("batchId"));
			maxBatchId = (String) criteria.uniqueResult();

			// logger.info("HQL=" + sql);
			// maxBatchId = query.uniqueResult().toString();
			logger.info("BatchId =" + maxBatchId);
		} finally {
			session.close();
		}
		return maxBatchId;
	}
}
