package com.ers.services;

import org.apache.log4j.Logger;

import com.ers.models.User;
import com.ers.repos.UserDAO;
import com.ers.repos.UserDAOImpl;
import com.ers.utils.Encryption;

public class LoginService {

	public static Logger log = Logger.getLogger(LoginService.class);
	private UserDAO userDAO = new UserDAOImpl();

	public User login(String username, String password) {
		User user = null;
		if (username != null && password != null && userDAO.getUser(username, Encryption.encrypt(password)) != null) {
			String storedPassword = userDAO.getUser(username, Encryption.encrypt(password)).getPassword();
			String storedUsername = userDAO.getUser(username, Encryption.encrypt(password)).getUsername();
			if (username.equals(storedUsername) && password.equals(Encryption.decrypt(storedPassword))) {
				log.info("Login successful for user: " + username);
				user = userDAO.getUser(username, Encryption.encrypt(password));
			}

		}
		return user;
	}
	
	public static void main(String[] args) {
		LoginService l = new LoginService();
		User user = l.login("smallville", "kansasboy");
		System.out.println(user);
	}

}
