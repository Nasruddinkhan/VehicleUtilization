package com.project.millatinventory.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.common.CommonUtil;
import com.project.millatinventory.dao.CommonDao;
import com.project.millatinventory.model.ApplicantTypeLookup;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Department;
import com.project.millatinventory.model.Expenses;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.CommonService;
@Service("CommonService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Map<Integer, String> getCities() {
		//TODO : Fetch from DB
				Map<Integer, String> map = new LinkedHashMap<Integer, String>();
				map.put(1,"Mumbai" );
				map.put(2,"Delhi" );
				return map;
	}

	@Override
	public Map<Integer, String> getStates() {
		//TODO : Fetch from DB
				Map<Integer, String> map = new LinkedHashMap<Integer, String>();
				map.put(1,"Maharashtra" );
				map.put(2,"Tamil Nadu" );
				return map;
	}
	@Override
	public List<Department> getDepartments() {
				
		return  commonDao.getDepartments();
				
	}
	@Override
	public List<ApplicantTypeLookup> getApplicantTypeLookup() {
				
		return  commonDao.getApplicantTypeLookup();
				
	}

	@Override
	public Map<String, String> getGenders() {
		//TODO : Fetch from DB
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("MALE","MALE" );
				map.put("FEMALE","FEMALE" );
				return map;
	}
	@Override
	public Map<String, String> getRoles() {
		//TODO : Fetch from DB
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("1","Admin" );
				map.put("2","Site Coordinator" );
				return map;
	}
	@Override
	public List<String> getHours() {
		//TODO : Fetch from DB 
		List<String> hours = new ArrayList<String>();
				
				for(int i=00; i<=24;i++) {
					hours.add(i+"");
				}
				return hours;
	}
	@Override
	public List<String> getMinutes() {
		//TODO : Fetch from DB 
		List<String> minutes = new ArrayList<String>();
				
				for(int i=00; i<=59;i++) {
					minutes.add(i+"");
				}
				return minutes;
	}
	@Override
	public List<Vehicle> getVehicleNum() {
			
				return commonDao.getVehicleNum();
}

	@Override
	public List<Sites> getSites() {
		
		return commonDao.getSites();
	}

	@Override
	public List<Moter> getmoter() {
		// TODO Auto-generated method stub
		return commonDao.getmoter();
	}
	
	@Override
	public List<Expenses> getExpenses() {
		// TODO Auto-generated method stub
		return commonDao.getExpenses();
	}

	@Override
	public Integer getDateEntryId() {
		return commonDao.getDateEntryId();
	}

	@Override
	public List<Vendors> getVendor() {
		// TODO Auto-generated method stub
		return commonDao.getVendor();
	}

	@Override
	public List<Vendors> getAllvendors() {
		// TODO Auto-generated method stub
		return commonDao.getAllvendors();
	}

	/*@Override
	public int MySQL_Backup(String DBName, String StorageLocation,String sqlDump, String userName, String Pass) {
		// TODO Auto-generated method stub
		return commonDao.MySQL_Backup(DBName, StorageLocation,sqlDump, userName, Pass);
	}

	@Override
	public int Restore_Mysql(String pathsqlconsole, String dbUserName, String dbPassword, String source) {
		// TODO Auto-generated method stub
		return commonDao.Restore_Mysql(pathsqlconsole, dbUserName, dbPassword, source);
	}*/

	
	public int db_Backup(String DBName, String StorageLocation, String sqlDump, String userName, String Pass){
		int x=0;
		try {
			x=CommonUtil.db_Backup(DBName, StorageLocation, sqlDump, userName, Pass);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
	public int db_Restore(String pathsqlconsole, String UserName, String Password, String source) {
		int y=0;
		try {
			y=CommonUtil.db_Restore(pathsqlconsole, UserName, Password, source);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return y;
	}

}

