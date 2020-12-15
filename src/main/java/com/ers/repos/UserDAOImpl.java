package com.ers.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

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

}
