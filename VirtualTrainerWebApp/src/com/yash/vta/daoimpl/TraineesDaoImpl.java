package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.TraineesDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.Batch;
import com.yash.vta.model.Trainee;
import com.yash.vta.model.TraineeModel;
import com.yash.vta.model.User;

public class TraineesDaoImpl implements TraineesDao {

	private Connection con = null;

	public TraineesDaoImpl() {

	}

	public void addTrainees(Trainee trainees) {
		int no = 0;
		try {
			System.out.println("Add trainee called dao");
			con = MyConnection.getConnection();
			Statement stmt = con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmt.executeQuery("select * from trainees");
			if (!rs.next()) {
				no = 1;

			} else {
				rs.last();
				no = rs.getInt("trainee_id") + 1;
			}

			String sql = "insert into trainees(trainee_id,batch_id,user_id) values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, no);
			pst.setInt(2, trainees.getBatch_id());
			pst.setInt(3, trainees.getUser_id());
			int success = pst.executeUpdate();
			if (success == 1) {

				con.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<TraineeModel> showAllTrainees() {
		List<TraineeModel> trainees = new ArrayList<TraineeModel>();
		try {
			con = MyConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT t.trainee_id,t.batch_id,b.batch_name,u.user_name,t.user_id FROM trainees t,batches b, users u WHERE(t.user_id = u.`user_id` AND t.`batch_id`=b.`batch_id`) order by trainee_id;");

			User user = null;
			Batch batch = null;
			Trainee trainee = null;
			TraineeModel model;
			while (rs.next()) {
				user = new User();
				batch = new Batch();
				trainee = new Trainee();
				trainee.setTrainee_id(rs.getInt("trainee_id"));
				trainee.setBatch_id(rs.getInt("batch_id"));
				trainee.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				batch.setBatch_name(rs.getString("batch_name"));
				model = new TraineeModel(trainee, batch, user);
				trainees.add(model);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return trainees;
	}

	@Override
	public void deleteTrainees(Trainee trainees) {
		try {
			con = MyConnection.getConnection();
			PreparedStatement pst = con
					.prepareStatement("Delete from TRAINEES where trainee_id =?");
			pst.setInt(1, trainees.getTrainee_id());

			int success = pst.executeUpdate();
			if (success == 1) {
				System.out.println("-------deleted-----------");
				con.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void updateTrainees(Trainee trainees) {
		try {
			con = MyConnection.getConnection();
			PreparedStatement pst = con
					.prepareStatement("UPDATE TRAINEES  SET Batch_id = ?, User_id = ? WHERE TRAINEE_ID = ?");
			pst.setInt(1, trainees.getBatch_id());
			pst.setInt(2, trainees.getUser_id());
			pst.setInt(3, trainees.getTrainee_id());
			int success = pst.executeUpdate();
			if (success == 1) {
				System.out.println("------ updated-------");
				con.close();
			}
		} catch (SQLException ex) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	@Override
	public Map<String, Integer> getAllBatches() {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		try {
			con = MyConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BATCHES");
			Batch batch = null;
			while (rs.next()) {
				batch = new Batch();
				batch.setBatch_name(rs.getString("batch_name"));
				batch.setBatch_id(rs.getInt("batch_id"));

				hm.put(batch.getBatch_name(), batch.getBatch_id());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hm;
	}

	@Override
	public Map<String, Integer> getAllUserName() {

		Map<String, Integer> users = new HashMap<String, Integer>();
		try {
			con = MyConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			while (rs.next()) {
				users.put(rs.getString("user_name"), rs.getInt("user_id"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return users;
	}

}
