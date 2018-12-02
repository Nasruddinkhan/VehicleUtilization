package com.project.millatinventory.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.dao.UserDao;
import com.project.millatinventory.model.Users;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	//Changes for oracle connection
	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void saveUser(Users customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		return;
	}

	@Override
	public int deleteUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("update Users set STATUS=:status where id=:userId");
		query.setParameter("status", ApplicationConstants.STATUS.DELETED);
		query.setParameter("userId", userId);

		return query.executeUpdate();

	}

	@Override
	public Users getUserById(int userId) {
		String hqlQ = "from Users u where u.id=:userId";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlQ);
		createQuery.setInteger("userId", userId);
		return (Users) createQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> getUsers() {
		String hqlC = "from Users c where c.status=:status";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlC);
		createQuery.setParameter("status", ApplicationConstants.STATUS.ACTIVE);
		return createQuery.list();
	}
	@Override
	public Users getUserCriteria(Users user) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Users.class);
		
		if(user.getLoginId()!=null){
			criteria.add(Expression.eq("loginId",user.getLoginId()));
		}
		if(user.getId()!=null && user.getId()!=0){
			criteria.add(Expression.ne("id",user.getId()));
		}
		criteria.add(Expression.eq("status",ApplicationConstants.STATUS.ACTIVE));
		
		Object uniqueResult = criteria.uniqueResult();
		System.out.println(uniqueResult);
		if(null != uniqueResult)
			return (Users) uniqueResult;
		else{
		return null;
		}
	}
	@Override
	public int updatePassword(Users user) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("update Users set password =:password where loginId=:loginId");
		query.setParameter("password", user.getNewPassword());
		query.setParameter("loginId", user.getLoginId());

		return query.executeUpdate();

	}
}
