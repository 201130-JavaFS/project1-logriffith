package com.ers.services;

import java.util.List;

import com.ers.models.Reimbursement;
import com.ers.repos.ReimbDAO;
import com.ers.repos.ReimbDAOImpl;

public class ReimbService {

	private ReimbDAO reimbDao = new ReimbDAOImpl();

	public boolean newStatus() {
		return reimbDao.newStatus();
	}

	public boolean newType(String type) {
		if (type.length() > 0) {
			type = type.trim().toLowerCase();
			if (type.equals("lodging") || type.equals("food") || type.equals("travel") || type.equals("other")) {
				reimbDao.newType(type);
				return true;
			}
		}
		return false;
	}

	public boolean newReimbursement(Reimbursement reimbursement) {
		if (reimbursement != null) {
			double amount = reimbursement.getAmount();
			int userId = reimbursement.getUserId();
			if(amount > 0 && userId > 0) {
				reimbDao.newReimb(reimbursement);
				return true;
			}
		}
		return false;
	}
	
	public List<Reimbursement> allPending(){
		return reimbDao.allPending();
	}

}

//sdf.format(t.getDate()));