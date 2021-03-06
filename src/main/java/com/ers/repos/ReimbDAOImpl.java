package com.ers.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ers.models.Reimbursement;
import com.ers.utils.DbConnection;
import com.ers.utils.ReimbQueries;

public class ReimbDAOImpl implements ReimbDAO {
	
	private static final Logger log = LogManager.getLogger(ReimbDAOImpl.class); 


	@Override
	public boolean newStatus() {
		boolean statusAdded = false;
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.NEW_REIMB_STATUS;
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statusAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return statusAdded;
	}

	@Override
	public boolean newType(String type) {
		boolean typeAdded = false;
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.NEW_REIMB_TYPE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, type);
			preparedStatement.executeUpdate();
			typeAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return typeAdded;
	}

	@Override
	public boolean newReimb(Reimbursement reimbursement) {
		boolean reimbAdded = false;
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.NEW_REIMBURSEMENT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, reimbursement.getAmount());
			preparedStatement.setString(2, reimbursement.getDescription());
			preparedStatement.setInt(3, reimbursement.getUserId());
			preparedStatement.executeUpdate();
			reimbAdded = true;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return reimbAdded;
	}

	@Override
	public List<Reimbursement> allPending(int userId) {
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.GET_ALL_PENDING;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			List<Reimbursement> rlist = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
			while (resultSet.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setUserId(resultSet.getInt("user_id"));
				reimb.setAmount(resultSet.getDouble("amount"));
				reimb.setDescription(resultSet.getString("description"));
				reimb.setTypeId(resultSet.getInt("type_id"));
				reimb.setSubmitted(simpleDateFormat.format(resultSet.getTimestamp("submitted")));
				reimb.setStatusId(resultSet.getInt("status_id"));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allPendingById(int userId) {
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.GET_ALL_PENDING_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			List<Reimbursement> rlist = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
			while (resultSet.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setUserId(resultSet.getInt("user_id"));
				reimb.setAmount(resultSet.getDouble("amount"));
				reimb.setDescription(resultSet.getString("description"));
				reimb.setTypeId(resultSet.getInt("type_id"));
				reimb.setSubmitted(simpleDateFormat.format(resultSet.getTimestamp("submitted")));
				rlist.add(reimb);
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allReimb() {
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.GET_ALL_REIMB;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Reimbursement> rlist = new ArrayList<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
			while (result.next()) {
				if (result.getTimestamp("resolved") != null) {
					Reimbursement reimb = new Reimbursement(
							result.getDouble("amount"),
							simpleDateFormat.format(result.getTimestamp("submitted")),
							simpleDateFormat.format(result.getTimestamp("resolved")),
							result.getString("description"),
							result.getInt("user_id"),
							result.getInt("status_id"),
							result.getInt("type_id"));
					rlist.add(reimb);
				} else {
					Reimbursement reimb = new Reimbursement(
							result.getDouble("amount"),
							simpleDateFormat.format(result.getTimestamp("submitted")),
							"not resolved",
							result.getString("description"),
							result.getInt("user_id"),
							result.getInt("status_id"),
							result.getInt("type_id"));
					rlist.add(reimb);
				}
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<Reimbursement> allReimById(int userId) {
		try (Connection connection = DbConnection.getConnection()) {
			String sql = ReimbQueries.GET_ALL_REIMB_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			List<Reimbursement> rlist = new ArrayList<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");
			while (result.next()) {
				if (result.getTimestamp("resolved") != null) {
					Reimbursement reimb = new Reimbursement(
							result.getDouble("amount"),
							simpleDateFormat.format(result.getTimestamp("submitted")),
							simpleDateFormat.format(result.getTimestamp("resolved")),
							result.getString("description"),
							result.getInt("user_id"),
							result.getInt("status_id"),
							result.getInt("type_id"));
					rlist.add(reimb);
				}else {
					Reimbursement reimb = new Reimbursement(
							result.getDouble("amount"),
							simpleDateFormat.format(result.getTimestamp("submitted")),
							"not resolved",
							result.getString("description"),
							result.getInt("user_id"),
							result.getInt("status_id"),
							result.getInt("type_id"));
					rlist.add(reimb);
				}
			}
			return rlist;
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e);
			e.getMessage();
		}
		return null;
	}
}
//preparedStatement.setTimestamp(2, new java.sql.Timestamp(reimbursement.getSubmitted().getTime()));
//preparedStatement.setNull(3, Types.TIMESTAMP_WITH_TIMEZONE);
// Chris said that this would work too: preparedStatement.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()))
