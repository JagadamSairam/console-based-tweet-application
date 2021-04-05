package com.usecase.component1.tweet_applcation;

import java.util.Map;
import java.util.Scanner;

import com.usecase.component1.tweet_applcation.controller.TweetApplicationController;


public class App {

	public static boolean logStatus = false;
	public static int userId;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		TweetApplicationController tweetAppController = new TweetApplicationController();

		if (!logStatus) {
			System.out.println("1.Register\t2.Login\t3.Forgot PassWord\nEnter Your Choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				tweetAppController.addUser();
				main(args);
				break;
			case 2:
				Map<String, Integer> result = tweetAppController.userLogin();
				if (result.get("status") == 1) {
					logStatus = true;
					userId = result.get("userId");
				} else {
					System.out.println("Invalid Credentials ... \t");
				}
				main(args);
				break;
			case 3:
				tweetAppController.forgotPassword();
				main(args);
				break;
			default:
				System.out.println("Invalid Choice.. \n Please Enter Valid Option...");
				main(args);

			}

		} else {
			System.out.println("" + "1.Create a new Tweet\n" + "2.View My Tweets\n" + "3.View All Tweets\n"
					+ "4.List All the Users\n" + "5.Reset Your Password\n" + "6.Logout\n" + "^^^^^^^^^^^^^^^^^^^^^^^");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				tweetAppController.postTweet(userId);
				main(args);
				break;
			case 2:
				tweetAppController.getAllMyTweets(userId);
				main(args);
				break;
			case 3:
				tweetAppController.getAllTweets();
				main(args);
				break;
			case 4:
				tweetAppController.getAllUsersList();
				main(args);
				break;
			case 5:
				tweetAppController.UpdateUserDetails(userId);
				main(args);
				break;
			case 6:
				if (tweetAppController.logout(userId)) {
					logStatus = false;
				}
				;
				main(args);
				break;
			default:
				System.out.println("Invalid Choice.. \\n Please Enter Valid Option...");

			}
		}
	}

}
