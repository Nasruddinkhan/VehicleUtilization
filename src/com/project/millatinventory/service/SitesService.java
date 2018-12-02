package com.project.millatinventory.service;

import java.util.List;

import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;

public interface SitesService {
	
	
		public void saveSites(Sites site);
		public List<Sites> getSites();
		public Integer updateSite(Sites site);
		public int deleteSiteById(int siteId);
		public Sites getSiteById(int siteId);

}
