package com.project.millatinventory.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.dao.SitesDao;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;

@Repository("SitesDao")
public class SitesDaoImpl implements SitesDao {
	
	@Autowired
	  JdbcTemplate jdbcTemplate;


	@Resource(name = "mySQLSessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void saveSites(Sites site) {

		//String sql = "insert into sites values(?,?,?,?,?)";
	 //   jdbcTemplate.update(sql, new Object[] { site.getSiteId(),site.getSiteName(),site.getSiteLocation(),site.getSiteManager(),site.getSiteSupervisor() });
		Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(site);
			}

	@Override
	public List<Sites> getSites() {
		String hqlC = "from Sites ";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlC);
		List list = createQuery.list();
		return list;
	}

	@Override
	public void updateSite(Sites siteId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Sites set where siteId=:siteId");
		query.setParameter("siteId", siteId);
		return;
		
			}

	@Override
	public int deleteSiteById(int siteId) {
		Session session = sessionFactory.getCurrentSession();

		//Query query = session.createQuery("update Sites set STATUS=:status where siteId=:siteId");
		
		Query query = session.createQuery("delete from Sites  where siteId=:siteId");
		
		//query.setParameter("status", ApplicationConstants.STATUS.DELETED);
		query.setParameter("siteId", siteId);

		return query.executeUpdate();
	}

	@Override
	public Sites getSiteById(int siteId) {
		String hqlQ = "from Sites u where u.id=:siteId";
		Query createQuery = sessionFactory.getCurrentSession()
				.createQuery(hqlQ);
		createQuery.setInteger("siteId", siteId);
		return (Sites) createQuery.uniqueResult();
	}

}
