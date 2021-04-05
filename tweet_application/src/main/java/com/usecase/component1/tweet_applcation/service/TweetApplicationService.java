package com.usecase.component1.tweet_applcation.service;

import java.sql.Date;
import java.sql.ResultSet;

import com.usecase.component1.tweet_applcation.dao.TweetDao;
import com.usecase.component1.tweet_applcation.entity.TweetEntity;


public class TweetApplicationService {

	TweetDao tweetDao = new TweetDao();

	public boolean addTweet(TweetEntity tweet) {
		Date date = new Date(System.currentTimeMillis());
		tweet.setCreatedDate(date);
		tweetDao.newTweet(tweet);
		return true;
	}

	public boolean getAllTweets() {
		ResultSet resultSet = tweetDao.getAllTweetPosts();
		try {
			if (resultSet.next()) {
				System.out.println(resultSet.getString("tweet")+"\t"+resultSet.getDate("createdDate"));
			} else {
				System.out.println("No Tweets are Found to show..");
			}
			while (resultSet.next()) {
				System.out.println(resultSet.getString("tweet")+"\t"+resultSet.getDate("createdDate"));
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \\n Can You Please Try Again After SomeTime \\n"+e);
		}
		return true;

	}

	public boolean getTweetByUserId(int userId) {
		ResultSet resultSet = tweetDao.getTweetByUserId(userId);
		try {
			if (resultSet.next()) {
				System.out.println(resultSet.getString("tweet")+" "+resultSet.getDate("createdDate"));
			} else {
				System.out.println("No Tweets are found by this UserId");
			}
			while (resultSet.next()) {
				System.out.println(resultSet.getString("tweet")+" "+resultSet.getDate("createdDate"));
			}
		} catch (Exception e) {
			System.out.println("Error Occured... \n Can You Please Try Again After SomeTime \n");
		}
		return true;
	}

}
