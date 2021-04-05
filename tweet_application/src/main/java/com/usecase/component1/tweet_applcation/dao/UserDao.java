package com.usecase.component1.tweet_applcation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.usecase.component1.tweet_applcation.entity.UserEntity;


public class UserDao {

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	List<UserEntity> usersList = new ArrayList<>();

	public UserDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/tweetapp?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");

			String usersTable = " CREATE TABLE IF NOT EXISTS USERS(id INTEGER AUTO_INCREMENT PRIMARY KEY,firstName VARCHAR(20), lastName VARCHAR(20),gender VARCHAR(20),"
					+ "dob VARCHAR(20), email VARCHAR(30), password VARCHAR(20), status BOOLEAN)";
			Statement statement2 = connection.createStatement();
			statement2.execute(usersTable);
		} catch (Exception e) {
			System.out.println("***\t Users Table was not created in the DataBase \t***\n"+e);

		}

	}

	public boolean addUser(UserEntity user) {
		try {
			String query = " insert into users(firstName, lastName, gender, dob, email, password, status) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement2 = connection.prepareStatement(query);
			preparedStatement2.setString(1, user.getFirstName());
			preparedStatement2.setString(2, user.getLastName());
			preparedStatement2.setString(3, user.getGender());
			preparedStatement2.setString(4, user.getDob());
			preparedStatement2.setString(5, user.getEmail());
			preparedStatement2.setString(6, user.getPassword());
			preparedStatement2.setBoolean(7, false);
			preparedStatement2.executeUpdate();
		} catch (Exception e) {
			System.out.println("Oops..! User was not registered succesfully You can try after some time.. \n"+e);
		}
		return true;
	}

	public Map<String, Integer> userLogin(String email, String password) {

		Map<String, Integer> result = new HashMap<>();
		result.put("status", -1);
		try {
			statement = connection.createStatement();
			String query = "SELECT id,email,password FROM users";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				if (email.equals(resultSet.getString("email")) && password.equals(resultSet.getString("password"))) {
					result.put("userId", resultSet.getInt("id"));
					result.put("status", 1);
					String query2 = "UPDATE users SET status = ? WHERE id = ?";
					preparedStatement = connection.prepareStatement(query2);
					preparedStatement.setBoolean(1, true);
					preparedStatement.setInt(2, resultSet.getInt("id"));
					preparedStatement.executeUpdate();
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println("Credentials are wrong, can you try again please **** \n ");
		}
		return result;
	}

	public void changePassword(String email, String newpassword) {
		try {
			String sql = "UPDATE users SET password = ? WHERE email = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newpassword);
			preparedStatement.setString(2, email);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("Password Changed Sucessfully..... \n");
			} else {
				System.out.println("Please Enter a valid EmailId");
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \n Can You Please Try Again After SomeTime \n"+e);
		}
	}

	public Boolean resetPassword(int userId, String oldPassword, String newPassword) {
		try {
			String sql = "UPDATE users SET password = ? WHERE id = ? AND password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, oldPassword);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("Password Changed Sucessfully..... \n");
			} else {
				System.out.println("Invalid Credentials .. \n");
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \n Can You Please Try Again After SomeTime \n");
		}
		return true;
	}

	public ResultSet getAllUsersList() {
		try {
			statement = connection.createStatement();
			String query = " SELECT firstName, lastName FROM users";
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception e) {
			System.out.println("Error Occured... \\n Can You Please Try Again After SomeTime \\n");
			return null;
		}
	}

	public Boolean loggedOut(int userId) {
		try {
			preparedStatement = connection.prepareStatement("UPDATE users SET status = ? WHERE id = ?");
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, userId);
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("Logged Out Successfully.... \n");
				return true;
			} else {
				System.out.println("Invalid User.... \n");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \\n Can You Please Try Again After SomeTime \\n");
			return false;
		}
	}
}
