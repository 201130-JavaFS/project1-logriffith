package com.ers.services;

import com.ers.repos.ReimbDAO;
import com.ers.repos.ReimbDAOImpl;

public class ReimbService {
	
	private ReimbDAO reimbDao = new ReimbDAOImpl();
	
	public String newStatus(String status) {
		status = status.trim().toLowerCase();
		if(status.equals("approved") || status.equals("denied")) {
			reimbDao.newStatus(status);
			return status;
		}
		return null;
		
	}
	
}
