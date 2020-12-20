package com.ers.utils;

public class ReimbQueries {

	
	public static final String GET_USER_INFO = "SELECT user_id, username, us_password, first_name, last_name, email, user_roles.us_role "
			+ "FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id WHERE username = ? AND us_password = ?";
	
	public static final String NEW_REIMB_STATUS = "INSERT INTO reimbursement_status (status) VALUES (?)";
	
	public static final String NEW_REIMB_TYPE = "INSERT INTO reimbursement_type (reimb_type) VALUES (?)";
	
	public static final String NEW_REIMBURSEMENT = "INSERT INTO reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id) "
			+ "VALUES (?, ?, ?, ?, ?, (SELECT max(status_id) FROM reimbursement_status) , (SELECT max(type_id) FROM reimbursement_type)),";
	
	public static final String GET_ALL_PENDING = "SELECT user_id,amount,description,type_id,submitted FROM reimbursements "
			+ "INNER JOIN reimbursement_status ON reimbursements.status_id = reimbursement_status.status_id "
			+ "WHERE status IS NULL";
	
	public static final String GET_REIMB_BY_ID = "SELECT user_id,amount,description,type_id,submitted,resolved,reimbursement_status.status_id FROM reimbursements "
			+ "INNER JOIN reimbursement_status ON reimbursements.status_id = reimbursement_status.status_id WHERE user_id = ?";
}
