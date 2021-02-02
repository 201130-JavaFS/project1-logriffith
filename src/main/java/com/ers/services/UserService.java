package com.ers.services;

import com.ers.models.User;
import com.ers.repos.ReimbDAO;
import com.ers.repos.ReimbDAOImpl;
import com.ers.repos.UserDAO;
import com.ers.repos.UserDAOImpl;
import com.ers.utils.Encryption;

public class UserService {

	// public static Logger log = Logger.getLogger(LoginService.class);
	private UserDAO userDAO = new UserDAOImpl();
	private ReimbDAO reimbDAO = new ReimbDAOImpl();

	public User login(String username, String password) {
		User user = null;
		if (username != null && password != null && userDAO.getUser(username, Encryption.encrypt(password)) != null) {
			String storedPassword = userDAO.getUser(username, Encryption.encrypt(password)).getPassword();
			String storedUsername = userDAO.getUser(username, Encryption.encrypt(password)).getUsername();
			if (username.equals(storedUsername) && password.equals(Encryption.decrypt(storedPassword))) {
				// log.info("Login successful for user: " + username);
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

	public boolean changeStatus(int statusId, String newStatus) {
		if (statusId > 0 && newStatus.length() > 0) {
			newStatus = newStatus.trim().toLowerCase();
			if (newStatus.equals("approved") || newStatus.equals("denied")) {
				userDAO.updateStatus(statusId, newStatus);
				return true;
			}
		}
		return false;
	}

	public boolean timeResolved(int statusId) {
		if (statusId > 0) {
			return userDAO.updateResolvedTime(statusId);
		}
		return false;
	}

	public boolean resolve(int userId, int statusId, String newStatus) {
		newStatus = newStatus.trim().toLowerCase();
		if (statusId > 0 && (newStatus.equals("approved") || newStatus.equals("denied"))) {
			userDAO.updateStatus(statusId, newStatus);
			userDAO.updateResolvedTime(statusId);
			return true;

		}
		return false;
	}
}
