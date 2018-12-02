package com.project.millatinventory.daoimpl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.dao.ChequeDao;
import com.project.millatinventory.model.Cheque;

@Repository("ChequeDaoImpl")
public class ChequeDaoImpl implements ChequeDao {
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	

	@Override
	public Cheque getChequeByNumber(String chequeNumber) {
		String hqlQ = "from Cheque c where c.cheque_No=:cheque_No";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlQ);
		createQuery.setString("cheque_No", chequeNumber);
		return (Cheque) createQuery.uniqueResult();
	}
}
