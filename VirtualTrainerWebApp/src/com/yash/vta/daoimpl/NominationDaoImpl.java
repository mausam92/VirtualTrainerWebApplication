package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yash.vta.dao.NominationDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.Nomination;
import com.yash.vta.model.Training;
import com.yash.vta.model.TrainingSchedule;

public class NominationDaoImpl implements NominationDao {

	public void addNomination(Nomination nomination) {
		int number = 0;

		try {
			java.sql.Connection connection = MyConnection.getConnection();
			Statement statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			ResultSet resultSet = statement
					.executeQuery("select * from nominations,training_schedules");
			if (!resultSet.next()) {
				number = 1;

			} else {
				resultSet.last();
				number = resultSet.getInt("nomination_id") + 1;

			}

			String sql = "insert into nominations(nomination_id,user_id,training_schedule_id,created_date,nomination_status) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setInt(2, nomination.getUser_id());
			preparedStatement.setInt(3, nomination.getTraining_schedule_id());
			java.sql.Date sqlDate = new java.sql.Date(
					new java.util.Date().getTime());

			nomination.setCreated_date(sqlDate);
			preparedStatement.setDate(4,
					(java.sql.Date) nomination.getCreated_date());
			preparedStatement.setString(5, nomination.getNomination_status());

			int count = preparedStatement.executeUpdate();
			if (count == 1) {
				System.out.println(count);
				System.out.println("added");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public List<Nomination> getAllNominations() {
		List<Nomination> listNominations = new ArrayList<Nomination>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Nomination nominations = null;
		try {

			java.sql.Connection connection = MyConnection.getConnection();
			String sql = "select * from nominations";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				nominations = new Nomination();

				nominations.setNomination_id(resultSet.getInt("nomination_id"));
				nominations.setUser_id(resultSet.getInt("user_id"));
				nominations.setTraining_schedule_id(resultSet
						.getInt("training_schedule_id"));
				nominations.setCreated_date(resultSet.getDate("created_date"));
				nominations.setNomination_status(resultSet
						.getString("nomination_status"));
				listNominations.add(nominations);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return listNominations;

	}

	@Override
	public List<AvailableTraining> getAllTrainingSchedules() {
		Statement statement = null;
		ResultSet resultSet = null;
		List<AvailableTraining> listAvailableTraining = new ArrayList<AvailableTraining>();
		try {

			Connection connection = MyConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT technology_name, training_schedule_id, from_date, to_date FROM training_schedules trs, trainings tr WHERE trs.training_id=tr.training_id AND from_date>=CURRENT_DATE");
			while (resultSet.next()) {
				Training tr = new Training();
				TrainingSchedule trs = new TrainingSchedule();
				tr.setTechnology_name(resultSet.getString("technology_name"));
				trs.setTraining_schedule_id(resultSet.getInt("training_schedule_id"));
				trs.setFrom_date(resultSet.getDate("from_date"));
				trs.setTo_date(resultSet.getDate("to_date"));
				AvailableTraining availableTrainingBean = new AvailableTraining(tr, trs);
				listAvailableTraining.add(availableTrainingBean);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listAvailableTraining;
	}

	public boolean checkNominee(int userId, int ScheduleId) {
		Connection connection = MyConnection.getConnection();
		boolean nominee = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Nominations where user_id =? and training_schedule_id=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, ScheduleId);
	       ResultSet rs = preparedStatement.executeQuery();
	       if(rs.next()){
	    	   nominee = true;
	       }
	        

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return nominee;
	}


}
