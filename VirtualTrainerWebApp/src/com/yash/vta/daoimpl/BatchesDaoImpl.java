package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.BatchesDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.Batch;

public class BatchesDaoImpl implements BatchesDao {

	private Connection con = null;

	public BatchesDaoImpl() {

	}

	public void addBatches(Batch batches) {
		int no = 0;
		try {
			System.out.println("Add batch called dao");
			con = MyConnection.getConnection();
			Statement statement = con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			ResultSet resultSet = statement
					.executeQuery("select * from batches");
			if (!resultSet.next()) {
				no = 1;

			} else {
				resultSet.last();
				no = resultSet.getInt("batch_id") + 1;
			}

			String sql = "insert into batches(batch_id,batch_name,training_schedule_id)values(?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, no);
			preparedStatement.setString(2, batches.getBatch_name());
			preparedStatement.setInt(3, batches.getTraining_schedule_id());
			int success = preparedStatement.executeUpdate();
			if (success == 1) {
				System.out.println("success");
				System.out.println("added");
				con.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Batch> showAllBatches() {
		List<Batch> listBatches = new ArrayList<Batch>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			con = MyConnection.getConnection();
			String sql = "select * from batches";

			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Batch batches = new Batch();

				batches.setBatch_id(resultSet.getInt("batch_id"));
				batches.setBatch_name(resultSet.getString("batch_name"));
				batches.setTraining_schedule_id(resultSet
						.getInt("training_schedule_id"));

				System.out.println(resultSet.getInt("batch_id"));

				listBatches.add(batches);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listBatches;
	}

	@Override
	public Map<String, Integer> getTrainingScheduleIds() {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String sql = "SELECT technology_name, training_schedule_id, from_date, to_date FROM training_schedules trs, trainings tr WHERE trs.training_id = tr.training_id";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			/*
			 * BatchesBean bb = null; Training tr = new Training();
			 */

			/* TrainingSchedule trs = new TrainingSchedule(); */

			while (rs.next()) {
				/* bb = new BatchesBean(); */
				int id = rs.getInt("training_schedule_id");
				String techname = rs.getString("technology_name");

				Date date = new Date();
				date = rs.getDate("from_date");

				Date date1 = new Date();
				date1 = rs.getDate("to_date");

				String trainingSchedule = techname + " - " + date + " - "
						+ date1;
				hm.put(trainingSchedule, id);

				System.out.println(trainingSchedule);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hm;
	}

	@Override
	public void deleteBatches(Batch batches) {

	}

	@Override
	public void updateBatches(Batch batches) {

	}
}
