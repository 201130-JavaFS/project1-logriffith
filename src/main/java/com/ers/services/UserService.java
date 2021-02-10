package com.ers.services;

import com.ers.models.User;
import com.ers.repos.UserDAO;
import com.ers.repos.UserDAOImpl;
import com.ers.utils.Encryption;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();

	public User login(String username, String password) {
		User user = null;
		if (username != null && password != null && userDAO.getUser(username, Encryption.encrypt(password)) != null) {
			String storedPassword = userDAO.getUser(username, Encryption.encrypt(password)).getPassword();
			String storedUsername = userDAO.getUser(username, Encryption.encrypt(password)).getUsername();
			if (username.equals(storedUsername) && password.equals(Encryption.decrypt(storedPassword))) {
				user = userDAO.getUser(username, Encryption.encrypt(password));
				user.setPassword(null);// probably shouldn't send this information back in the response
				user.setUsername(null);
				user.setEmail(null);
			}

		}
		return user;
	}

	public String getType(int typeId) {
		if (typeId > 0) {
			String type = userDAO.getType(typeId);
			return type;
		} else {
			return null;
		}
	}

	public String getStatus(int statusId) {
		if (statusId > 0) {
			return userDAO.getStatus(statusId);
		}
		return null;
	}

	public boolean resolve(int statusId, String newStatus) {
		newStatus = newStatus.trim().toLowerCase();
		if (statusId > 0 && (newStatus.equals("approved") || newStatus.equals("denied"))) {
			userDAO.updateStatus(statusId, newStatus);
			userDAO.updateResolvedTime(statusId);
			return true;

		}
		return false;
	}
}
