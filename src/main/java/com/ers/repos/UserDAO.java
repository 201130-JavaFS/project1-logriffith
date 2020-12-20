package com.ers.repos;

import java.util.List;

import com.ers.models.Reimbursement;
import com.ers.models.User;

public interface UserDAO {

	public User getUser(String username, String password);
	public String getType(int typeId);
	public String getStatus(int statusId);
	public boolean updateStatus(int statusId, String newStatus);
}

