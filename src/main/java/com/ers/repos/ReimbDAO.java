package com.ers.repos;

import java.util.List;

import com.ers.models.Reimbursement;

public interface ReimbDAO {

	public boolean newStatus(String status);
	public boolean newType(String type);
	public boolean newReimb(Reimbursement reimbursement);
	public List<Reimbursement> allPending();
	public List<Reimbursement> allPendingById(int userId);
	public List<Reimbursement> allReimb();
	public List<Reimbursement> allReimById(int userId);
	
}
