package com.project.millatinventory.dao;

import java.util.List;

import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;

public interface SitesDao {
	
	public void saveSites(Sites site);
	public List<Sites> getSites();
	public void updateSite(Sites site);
	public int deleteSiteById(int siteId);
	public Sites getSiteById(int siteId);

	
}

