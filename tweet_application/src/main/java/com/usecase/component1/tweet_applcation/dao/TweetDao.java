package com.usecase.component1.tweet_applcation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.usecase.component1.tweet_applcation.entity.TweetEntity;


public class TweetDao {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	public TweetDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/tweetapp?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
	              ,"root","");

			String createTweetTable = "CREATE TABLE IF NOT EXISTS TWEETS (id iNTEGER AUTO_INCREMENT PRIMARY KEY, userId INTEGER, tweet VARCHAR(20), createdDate DATE)";
			Statement statement2 = connection.createStatement();
			statement2.execute(createTweetTable);

		} catch (Exception e) {
			System.out.println("***\tUser details was not Created in the Database\t***\n");
		}
	}

	public Boolean newTweet(TweetEntity tweets) {
		try {
			preparedStatement = connection
					.prepareStatement("insert into tweets(userId,tweet,createdDate) values(?,?,?)");
			preparedStatement.setInt(1, tweets.getUserId());
			preparedStatement.setString(2, tweets.getTweet());
			preparedStatement.setDate(3, tweets.getCreatedDate());
			preparedStatement.executeUpdate();
			System.out.println("\nTweet was Succesfully Posted ");
		} catch (Exception e) {
			System.out.println("\nOops..! Tweet was not Posted.");

		}
		return true;
	}

	public ResultSet getAllTweetPosts() {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM tweets";
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception e) {
			System.out.println("\nOops..! Page was Not Loaded, Please try Again after sometime"+e);
			return null;
		}
	}

	public ResultSet getTweetByUserId(int userId) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM tweets WHERE userId = "+userId;
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception e) {
			System.out.println("\nOops..! Page was Not Loaded, Please try Again after sometime"+e);
			return null;
		}
	}
}
