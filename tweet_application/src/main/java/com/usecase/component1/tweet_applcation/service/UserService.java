package com.usecase.component1.tweet_applcation.service;

import java.sql.ResultSet;
import java.util.Map;

import com.usecase.component1.tweet_applcation.dao.UserDao;
import com.usecase.component1.tweet_applcation.entity.UserEntity;


public class UserService {

	UserDao userDao = new UserDao();

	public Boolean addUser(UserEntity user) {
		boolean registerStatus = userDao.addUser(user);
		if (registerStatus == true) {
			return registerStatus;
		}
		return false;
	}

	public Map<String, Integer> login(String email, String password) {
		return userDao.userLogin(email, password);
	}

	public boolean loggedOut(int userId) {
		return userDao.loggedOut(userId);
	}

	public void forgotPassword(String email, String newpassword) {
		userDao.changePassword(email, newpassword);
	}

	public boolean resetPassword(int userId, String oldPassword, String newPassword) {
		return userDao.resetPassword(userId, oldPassword, newPassword);
	}

	public boolean getUsersList() {
		ResultSet result = userDao.getAllUsersList();
		try {
			while (result.next()) {
				if (result.getString("firstName") == null && result.getString("lastName") == null) {
					System.out.println("Users List Was Empty....");
					return true;
				}
				System.out.println(result.getString("firstName") + " " + result.getString("lastName"));
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \n Can You Please Try Again After SomeTime \n");
		}
		return true;
	}
}
