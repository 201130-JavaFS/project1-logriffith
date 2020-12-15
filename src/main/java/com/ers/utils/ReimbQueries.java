package com.ers.utils;

public class ReimbQueries {

	//Login
	public static final String GET_USER_INFO = "SELECT user_id, username, us_password, first_name, last_name, email, user_roles.us_role "
			+ "FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id WHERE username = ? AND us_password = ?";
	
}
