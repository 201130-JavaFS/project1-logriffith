package com.ers.repos;

import com.ers.models.Reimbursement;
import com.ers.models.User;

public interface UserDAO {

	public User getUser(String username, String password);
	public boolean newStatus(String status);
	public boolean newType(String type);
	public boolean newReimburse(Reimbursement reimbursement);
	public List<Reimbursement> allPending();
	public List<Reimbursement> allPendingById(int userId);
}

