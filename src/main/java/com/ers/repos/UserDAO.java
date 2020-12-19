package com.ers.repos;

import com.ers.models.Reimbursement;
import com.ers.models.User;

public interface UserDAO {

	public User getUser(String username, String password);
	public boolean newStatus(String status);
	public boolean newType(String type);
	public boolean newReimbursement(Reimbursement reimbursement);
	
}

