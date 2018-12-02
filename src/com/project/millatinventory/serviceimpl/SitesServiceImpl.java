package com.project.millatinventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.SitesDao;
import com.project.millatinventory.dao.UserDao;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.SitesService;

@Service("SitesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class SitesServiceImpl implements SitesService {

	
	@Autowired
	private SitesDao siteDao;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	
	@Override
	public void saveSites(Sites site) {
		System.out.println("SiteServiceImpl.addNewSite() save ");
		siteDao.saveSites(site);
				
	}

	@Override
	public List<Sites> getSites() {
		return siteDao.getSites();
	}

	@Override
	public Integer updateSite(Sites site) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteSiteById(int siteId) {
		return siteDao.deleteSiteById(siteId);
	}

	@Override
	public Sites getSiteById(int siteId) {
		
		return siteDao.getSiteById(siteId);
	}

}
