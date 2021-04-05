package com.usecase.component1.tweet_applcation.controller;

import java.util.Map;
import java.util.Scanner;

import com.usecase.component1.tweet_applcation.entity.TweetEntity;
import com.usecase.component1.tweet_applcation.entity.UserEntity;
import com.usecase.component1.tweet_applcation.service.TweetApplicationService;
import com.usecase.component1.tweet_applcation.service.UserService;


public class TweetApplicationController {

	public Integer userId;
	public String firstName;
	public String lastName;
	public String gender;
	public String dob;
	public String email;
	public String password;
	public Integer tweetId;
	public String tweet;

	Scanner scanner = new Scanner(System.in);
	UserService userService = new UserService();
	TweetApplicationService tweetService = new TweetApplicationService();

	public boolean addUser() {
		UserEntity user = new UserEntity();
		while (true) {
			System.out.println("Enter FirstName of the User : \t");
			firstName = scanner.nextLine();
			if (firstName.length() >= 3) {
				user.setFirstName(firstName);
				break;
			}
			System.out.println("The Enetered FirstName should have more than 3 Characters of length..");
		}
		while (true) {
			System.out.println("Enter LastName of the User : \t");
			lastName = scanner.nextLine();
			if (lastName.length() >= 3) {
				user.setLastName(lastName);
				break;
			}
			System.out.println("The Enetered lastName should have more than 3 Characters of length..");
		}
		while (true) {
			System.out.println("Enter Gender of the User : \t");
			gender = scanner.nextLine();
			if (gender.equals("male") || gender.equals("female")) {
				user.setGender(gender);
				break;
			}
			System.out.println("Please Eneter a Gender as \'Male\' or \'FeMale\'..");
		}
		while (true) {
			System.out.println("Enter Date Of Birth of the User : \t");
			dob = scanner.nextLine();
			if (dob.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
				user.setDob(dob);
				break;
			}
			System.out.println("Please Enter Your DOB as yyyy-mm-dd Format .. ");
		}
		while (true) {
			System.out.println("Enter EmailId of the User : \t");
			email = scanner.nextLine();
			if(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
				user.setEmail(email);
				break;
			}
			System.out.println("Please Give Valid EmailId..");
		}
		while (true) {
			System.out.println("Enter Your Password : \t");
			password = scanner.nextLine();
			if (password.length() >= 6) {
				user.setPassword(password);
				break;
			}
			System.out.println("The Password must be above 6 Characters of length..");
		}
		userService.addUser(user);
		return true;
	}

	public Map<String, Integer> userLogin() {
		while (true) {
			System.out.println("Enter Your Mail Id  : \t");
			email = scanner.nextLine();
			if(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
				break;
			}
			System.out.println("Please Enter Valid Email ID >>.");
		}
		while (true) {
			System.out.println("Enter Your Password : \t");
			password = scanner.nextLine();
			if (password.length() >= 6) {
				break;
			}
			System.out.println("Please ENter Valid Password to login ...\t");
		}
		return userService.login(email, password);
	}

	public Boolean UpdateUserDetails(int userId) {
		String newPassword, oldPassword;
		while (true) {
			System.out.println("Enter Your OldPassword : \t");
			oldPassword = scanner.nextLine();
			if (oldPassword.length() >= 6) {
				break;
			}
			System.out.println("Password Must be a length of 6 to 10..");
		}
		while (true) {
			System.out.println("Enter Your New Password : \t ");
			newPassword = scanner.nextLine();
			if (newPassword.length() >= 6) {
				break;
			}
			System.out.println("Password Must be a length of 6 to 10..");
		}
		userService.resetPassword(userId, oldPassword, newPassword);
		return true;
	}

	public boolean forgotPassword() {
		String newPassword;
		while (true) {
			System.out.println("Enter Your Email : \t");
			email = scanner.nextLine();
			if(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
				break;
			}
			System.out.println("Please enter valid Email ID \t");
		}
		while (true) {
			System.out.println("Enter Your Password to Update : \t");
			newPassword = scanner.nextLine();
			if (newPassword.length() >= 6) {
				break;
			}
			System.out.println("The Password must be in 6 to 10 Characters of length");
		}
		userService.forgotPassword(email, newPassword);
		return true;
	}

	public boolean getAllUsersList() {
		userService.getUsersList();
		return true;
	}

	public boolean logout(int userId) {
		return userService.loggedOut(userId);
	}

	public Boolean postTweet(int userId) {
		TweetEntity tweetEntity = new TweetEntity();
		while (true) {
			System.out.println("Please Type Your tweet to be post : \n \t");
			tweet = scanner.nextLine();
			if (tweet.length() > 0) {
				break;
			}
			System.out.println("Its not possible to be post an Empty Tweet..");
		}
		tweetEntity.setUserId(userId);
		tweetEntity.setTweet(tweet);
		tweetService.addTweet(tweetEntity);
		return true;
	}

	public boolean getAllMyTweets(int userId) {
		return tweetService.getTweetByUserId(userId);
	}

	public boolean getAllTweets() {
		return tweetService.getAllTweets();
	}

}
