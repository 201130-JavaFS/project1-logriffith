package com.ers.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

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
	public String getType(int typeId) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_TYPE; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, typeId);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				return result.getString("reimb_type");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getStatus(int statusId) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, statusId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString("status");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateStatus(int statusId, String newStatus) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.UPDATE_REIMB_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newStatus);
			preparedStatement.setInt(2, statusId);
			preparedStatement.executeQuery();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateResolvedTime(int statusId) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.UPDATE_RESOLVED_DATE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, statusId);
			preparedStatement.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
