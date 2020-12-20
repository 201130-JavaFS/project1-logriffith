package com.ers.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.ers.models.Reimbursement;
import com.ers.models.User;
import com.ers.utils.DbConnection;
import com.ers.utils.ReimbQueries;

public class UserDAOImpl implements UserDAO {
	
	//public static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public User getUser(String username, String password) {
		User user = null;
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_USER_INFO;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				//log.info("found user: " + username);		
				//log.trace("found user: " + username);//remove this later
				user = new User(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("us_password"),
						resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("us_role"));
			}else {
				//log.info("could not find user: " + username);
			}
		} catch (ClassNotFoundException | SQLException e) {
			//log.warn(e);
			e.printStackTrace();
		}
	
		return user;
	}

	@Override
	public boolean newStatus(String status) {
		boolean statusAdded = false;
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.NEW_REIMB_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.executeUpdate();
			statusAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return statusAdded;
	}

	@Override
	public boolean newType(String type) {
		boolean typeAdded = false;
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.NEW_REIMB_TYPE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			preparedStatement.executeUpdate();
			typeAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return typeAdded;
	}

	@Override
	public boolean newReimburse(Reimbursement reimbursement) {
		boolean reimbAdded = false;
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.NEW_REIMBURSEMENT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, reimbursement.getAmount());
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(reimbursement.getSubmitted().getTime()));
			preparedStatement.setNull(3, Types.TIMESTAMP_WITH_TIMEZONE);
			preparedStatement.setString(4, reimbursement.getDescription());
			preparedStatement.setInt(5, reimbursement.getUserId());
			preparedStatement.executeUpdate();
			reimbAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return reimbAdded;
	}

	

}
