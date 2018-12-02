package com.project.millatinventory.daoimpl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.controller.PrintController;
import com.project.millatinventory.dao.PrintDao;
import com.project.millatinventory.model.AccountTable;
import com.project.millatinventory.model.ApplicantAccount;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;
import com.project.millatinventory.model.FileHeader;

@Repository("PrintDao")
public class PrintDaoImpl implements PrintDao {

	
	private static final Logger logger = LoggerFactory.getLogger(PrintController.class);
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;
	private List list;

	@Override
	public DetailLookup getApplicantById(String applicantID) {

		Session session = null;
		DetailLookup detailLookup = null;
		try {
			session = sessionFactory.openSession();
			detailLookup = (DetailLookup) session.get(DetailLookup.class,
					applicantID);
		} finally {
			session.close();
		}
		return detailLookup;
	}

	@Override
	public List<ApplicantAccount> getAccountTypeByAppId(String applicantID) {

		Session session = null;
		DetailLookup detailLookup = null;
		String hql="";
		Query query=null;
		try {
			hql="from ApplicantAccount where accountTypes.applicantId=:applicantId";
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			query.setParameter("applicantId", applicantID);
			list = query.list();
			
		} finally {
			session.close();
		}
		return list ;
	}
	
	@Override
	public void saveApplicantDetails(DetailInsert detailInsert) {
		Session session = null;
		logger.info("Saving Applicant");
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(detailInsert);
			tx.commit();
			System.out.println("Save");
			logger.info("Applicant Save");
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public List<DetailInsert> getApplicants(DetailInsert detailInsert) {
		Criteria criteria = null;
		Session session = null;
		List list = null;
		try {

			//logger.info("getApplicants");
			session = sessionFactory.openSession();

		/*	String sql = "from DetailInsert ";
			if (null != fileHeader.getFileName()
					&& !fileHeader.getFileName().isEmpty()) {
				sql += " where fileName=:fileName ";
			}

			if (null != detailInsert.getCreatedBy()
					&& !detailInsert.getCreatedBy().isEmpty()) {
					sql += " where createdBy=:createdBy ";
				
			}
			if (null != fileHeader.getFileId() && 0 != fileHeader.getFileId()) {
				if (sql.contains("where")) {
					sql += " and fileId=:fileId  ";
				} else {
					sql += " where fileId=:fileId  ";
				}
			}
			sql += " order by createdDate desc";
			*/
			criteria = session.createCriteria(DetailInsert.class);
			if (null != detailInsert.getStatus()
					&& !detailInsert.getStatus().isEmpty()) {
				criteria.add(Restrictions.eq("status", detailInsert.getStatus()));
			}
			if (null != detailInsert.getCreatedBy()
					&& !detailInsert.getCreatedBy().isEmpty()) {
				criteria.add(Restrictions.eq("createdBy", detailInsert.getCreatedBy()));
				
			}
			/*if (null != fileHeader.getFileId() && 0 != fileHeader.getFileId()) {
				query.setParameter("fileId", fileHeader.getFileId());
			}*/
			//logger.info("HQL=" + query);
			list = criteria.list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public String getIban(String accountType) {
		Session session = null;
		String iban="";
		String hql="";
		Query query=null;
		try {
			hql="select iBAN from AccountTable where accountType=:accountType";
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			query.setParameter("accountType", accountType);
			iban = (String) query.uniqueResult();
			
		} finally {
			session.close();
		}
		return iban ;}

	@Override
	public DetailInsert getSavedApplicantById(String id) {
		Session session = null;
		DetailInsert detailInsert = null;
		try {
			session = sessionFactory.openSession();
			detailInsert = (DetailInsert) session.get(DetailInsert.class,
					id);
		} finally {
			session.close();
		}
		return detailInsert;
	}
	
	@Override
	public Integer updateStatus(DetailInsert detailInsert) {
		Session session = null;
		Query query = null;
		int count = 0;
		String hql = "";
		try {
			logger.info(" updateStatus to " + detailInsert.getStatus());
			session = sessionFactory.openSession();
			hql = " update DetailInsert set status=:status ";
			
			if (null != detailInsert && null != detailInsert.getVerifiedDate()) {
				hql += " , verifiedDate=:verifiedDate ";
			}
			if (null != detailInsert && null != detailInsert.getVerifireId()
					&& !detailInsert.getVerifireId().isEmpty()) {
				hql += " , verifireId=:verifireId ";
			}
			
			hql += " where applicantID=:applicantID ";

			query = session.createQuery(hql);

			query.setParameter("status", detailInsert.getStatus());
			if (null != detailInsert && null != detailInsert.getVerifiedDate()) {
				query.setParameter("verifiedDate", detailInsert.getVerifiedDate());
			}
			if (null != detailInsert && null != detailInsert.getVerifireId()
					&& !detailInsert.getVerifireId().isEmpty()) {
				query.setParameter("verifireId", detailInsert.getVerifireId());
			}
			query.setParameter("applicantID", detailInsert.getApplicantID());

			logger.info("update Query" + query);
			count = query.executeUpdate();
		} finally {
			session.close();
		}
		return count;
	}
}
