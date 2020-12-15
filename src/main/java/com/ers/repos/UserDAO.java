package com.ers.repos;

import com.ers.models.User;

public interface UserDAO {

	public User getUser(String username, String password);
	
}

