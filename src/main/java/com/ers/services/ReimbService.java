package com.ers.services;

import java.util.List;

import com.ers.models.Reimbursement;
import com.ers.repos.ReimbDAO;
import com.ers.repos.ReimbDAOImpl;
import com.ers.repos.UserDAO;
import com.ers.repos.UserDAOImpl;

public class ReimbService {

	private ReimbDAO reimbDao = new ReimbDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();

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
		if (reimbursement != null && newStatus() && newType(reimbursement.getType())
				&& reimbDao.newReimb(reimbursement)) {
//			newStatus();
//			newType(reimbursement.getType());
//			reimbDao.newReimb(reimbursement);
			return true;
		}
		return false;
	}

	public List<Reimbursement> allPending() {
		List<Reimbursement> allPending = reimbDao.allPending();
		for (Reimbursement r : allPending) {
			r.setType(userDAO.getType(r.getTypeId()));
		}
		return allPending;
	}

	public List<Reimbursement> allPendingById(int userId) {
		if (userId > 0) {
			return reimbDao.allPendingById(userId);
		}
		return null;
	}

	public List<Reimbursement> allReimbursements() {
		List<Reimbursement> allReimb = reimbDao.allReimb();
		for (Reimbursement r : allReimb) {
			r.setType(userDAO.getType(r.getTypeId()));
			String status = userDAO.getStatus(r.getStatusId());
			if (status != null) {
				r.setStatus(status);
			}else {
				r.setStatus("pending");
			}
		}
		return allReimb;
	}

	public List<Reimbursement> allReimbursementsById(int userId) {
		if (userId > 0) {
			List<Reimbursement> allUserReimb = reimbDao.allReimById(userId);
			for (Reimbursement r : allUserReimb) {
				r.setType(userDAO.getType(r.getTypeId()));
				String status = userDAO.getStatus(r.getStatusId());
				if (status != null) {
					r.setStatus(status);
				}else {
					r.setStatus("pending");
				}
			}
			return allUserReimb;
		}
		return null;
	}

}

//sdf.format(t.getDate()));