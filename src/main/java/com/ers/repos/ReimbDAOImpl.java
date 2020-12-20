package com.ers.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Reimbursement;
import com.ers.utils.DbConnection;
import com.ers.utils.ReimbQueries;

public class ReimbDAOImpl implements ReimbDAO {

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
	public boolean newReimb(Reimbursement reimbursement) {
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

	@Override
	public List<Reimbursement> allPending() {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_ALL_PENDING;
			Statement statement = connection.createStatement();
			List<Reimbursement> rlist = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setUserId(resultSet.getInt("user_id"));
				reimb.setAmount(resultSet.getDouble("amount"));
				reimb.setDescription(resultSet.getString("description"));
				reimb.setTypeId(resultSet.getInt("type_id"));
				reimb.setSubmitted(resultSet.getTimestamp("submitted"));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allPendingById(int userId) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_ALL_PENDING_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			List<Reimbursement> rlist = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			while(resultSet.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setUserId(resultSet.getInt("user_id"));
				reimb.setAmount(resultSet.getDouble("amount"));
				reimb.setDescription(resultSet.getString("description"));
				reimb.setTypeId(resultSet.getInt("type_id"));
				reimb.setSubmitted(resultSet.getTimestamp("submitted"));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allReimb() {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_ALL_REIMB_BY_ID;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Reimbursement> rlist = new ArrayList<>();
			while(result.next()) {
				Reimbursement reimb = new Reimbursement(
						result.getDouble("amount"),
						result.getTimestamp("submitted"),
						result.getTimestamp("resolved"),
						result.getString("description"),
						result.getInt("user_id"),
						result.getInt("status_id"),
						result.getInt("type_id"));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allReimById(int userId) {
		try(Connection connection = DbConnection.getConnection()){
			String sql = ReimbQueries.GET_ALL_REIMB_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			List<Reimbursement> rlist = new ArrayList<>();
			while(result.next()) {
				Reimbursement reimb = new Reimbursement(
						result.getDouble("amount"),
						result.getTimestamp("submitted"),
						result.getTimestamp("resolved"),
						result.getString("description"),
						result.getInt("user_id"),
						result.getInt("status_id"),
						result.getInt("type_id"));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
