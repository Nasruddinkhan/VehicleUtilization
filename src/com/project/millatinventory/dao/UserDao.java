package com.project.millatinventory.dao;

import java.util.List;

import com.project.millatinventory.model.Users;

public interface UserDao {
public void saveUser(Users user);
public int deleteUserById(int userId);
public Users getUserById(int userId);
public List<Users> getUsers();

Users getUserCriteria(Users user);
int updatePassword(Users user);

}
