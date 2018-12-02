package com.project.millatinventory.service;




import java.util.List;

import com.project.millatinventory.model.Users;
import com.project.millatinventory.model.Vehicle;

public interface UserService {
public void saveUser(Users user);
public List<Users> getUsers();
public Integer updateUser(Users user);
public int deleteUserById(int userId);
public Users getUserById(int userId);
public Users getUserCriteria(Users user);
int updatePassword(Users user);



}
