package com.project.millatinventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.millatinventory.dao.PrintDao;
import com.project.millatinventory.model.ApplicantAccount;
import com.project.millatinventory.model.DetailInsert;
import com.project.millatinventory.model.DetailLookup;
import com.project.millatinventory.service.PrintService;
@Service("PrintService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class PrintServiceImpl implements PrintService{
	@Autowired
	private PrintDao printDao;
	/*@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	@Override
	public void saveUser(Users user) {
		System.out.println("CustomerServiceImpl.addNewCustomer() save ");
		userDao.saveUser(user);
		return ;
	}

	@Override
	public List<Users> getUsers() {

		return userDao.getUsers();
	}

	@Override
	public Integer updateUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUserById(int userId) {
		return userDao.deleteUserById(userId);	
		 
	}

	@Override
	public Users getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public Users getUserCriteria(Users user) {
		return userDao.getUserCriteria(user);
	}

	@Override
	public int updatePassword(Users user) {
		// TODO Auto-generated method stub
		return userDao.updatePassword(user);
	}	*/

	@Override
	public DetailLookup getApplicantById(String id) {
		return printDao.getApplicantById(id);
	}

	@Override
	public void saveApplicantDetails(DetailInsert detailInsert) {
		printDao.saveApplicantDetails(detailInsert);
	}

	@Override
	public List<ApplicantAccount> getAccountTypeByAppId(String applicantID) {

		return printDao.getAccountTypeByAppId(applicantID);
	}

	@Override
	public String getIban(String accountType) {
		// TODO Auto-generated method stub
		 return printDao.getIban(accountType);
	}

	@Override
	public List<DetailInsert> getApplicants(DetailInsert detailInsert) {
		 return printDao.getApplicants(detailInsert);
	}

	@Override
	public DetailInsert getSavedApplicantById(String id) {
		 return printDao.getSavedApplicantById( id);
	}

	@Override
	public Integer updateStatus(DetailInsert detailInsert) {
		return printDao.updateStatus(detailInsert);
	}
	
}

