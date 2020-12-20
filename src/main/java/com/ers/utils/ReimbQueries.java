package com.ers.utils;

public class ReimbQueries {

	
	public static final String GET_USER_INFO = "SELECT user_id, username, us_password, first_name, last_name, email, user_roles.us_role "
			+ "FROM users INNER JOIN user_roles ON users.role_id = user_roles.role_id WHERE username = ? AND us_password = ?";
	
	public static final String NEW_REIMB_STATUS = "INSERT INTO reimbursement_status (status) VALUES (NULL)";
	
	public static final String NEW_REIMB_TYPE = "INSERT INTO reimbursement_type (reimb_type) VALUES (?)";
	
	public static final String NEW_REIMBURSEMENT = "INSERT INTO reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id) "
			+ "VALUES (?, (SELECT now()), NULL, ?, ?, (SELECT max(status_id) FROM reimbursement_status) , (SELECT max(type_id) FROM reimbursement_type)),";
	
	public static final String GET_ALL_PENDING = "SELECT user_id,amount,description,type_id,submitted FROM reimbursements "
			+ "INNER JOIN reimbursement_status ON reimbursements.status_id = reimbursement_status.status_id "
			+ "WHERE status IS NULL";
	
	public static final String GET_ALL_PENDING_BY_ID = "SELECT user_id,amount,description,type_id,submitted FROM reimbursements "
			+ "INNER JOIN reimbursement_status ON reimbursements.status_id = reimbursement_status.status_id "
			+ "WHERE status IS NULL AND user_id = 2";
	
	public static final String GET_ALL_REIMB_BY_ID = "SELECT user_id,amount,description,type_id,submitted,resolved,reimbursement_status.status_id FROM reimbursements "
			+ "WHERE user_id = ?";
	
	public static final String GET_ALL_REIMB = "SELECT user_id,amount,description,type_id,submitted,resolved, status_id FROM reimbursements";

	public static final String GET_TYPE = "SELECT reimb_type FROM reimbursement_type WHERE type_id = ?";
	
	public static final String GET_STATUS = "SELECT status FROM reimbursement_status WHERE status_id = ?";
	
	public static final String UPDATE_REIMB_STATUS = "UPDATE reimbursement_status SET status = ? WHERE status_id = ?";
	
	public static final String UPDATE_RESOLVED_DATE = "UPDATE reimbursements SET resolved = (SELECT now()) WHERE status_id = ?";
}
