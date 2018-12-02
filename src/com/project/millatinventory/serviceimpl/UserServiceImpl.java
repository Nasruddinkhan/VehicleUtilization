package com.project.millatinventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;





import com.project.millatinventory.dao.UserDao;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.UserService;
@Service("UserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

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
	}	
	
}

