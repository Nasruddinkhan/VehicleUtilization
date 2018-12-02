package com.project.millatinventory.daoimpl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.controller.LoginController;
import com.project.millatinventory.dao.LoginDAO;
import com.project.millatinventory.model.Users;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
	

	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	protected SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory
			.getLogger(LoginDAOImpl.class);


	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public Users checkLogin(Users user) {
		
		Session session = sessionFactory.openSession();
		// Query using Hibernate Query Language
		String SQL_QUERY = "from Users where upper(loginId)=:loginId and password=:password and status=:status";
		//		String SQL_QUERY = "from Users where upper(loginId)=:loginId and status=:status";
		//System.out.println("34");
		Query query = session.createQuery(SQL_QUERY);
		//System.out.println("36");
		query.setParameter("loginId", user.getLoginId().toUpperCase().trim());
		query.setParameter("password", user.getPassword());
		query.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		Users uniqueResult =null;
		try {
			uniqueResult = (Users) query.uniqueResult();
		//	System.out.println("43");
		} catch (Exception e) {
			logger.info("Error="+e);
			uniqueResult = new Users();
		} finally {
			session.close();
		}

		return uniqueResult;
	}
}