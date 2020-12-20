package com.ers.repos;

import com.ers.models.User;

public interface UserDAO {

	public User getUser(String username, String password);
	public String getType(int typeId);
	public String getStatus(int statusId);
	public boolean updateStatus(int statusId, String newStatus);
	public boolean updateResolvedTime(int statusId);
}

