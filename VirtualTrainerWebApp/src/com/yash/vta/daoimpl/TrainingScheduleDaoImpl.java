package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.TrainingScheduleDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.Training;
import com.yash.vta.model.TrainingSchedule;

public class TrainingScheduleDaoImpl implements TrainingScheduleDao {
Map<String, Integer> mapTrainings = new HashMap<String, Integer>();
	
	@Override
	public void addTrainingSchedule(TrainingSchedule trainingSchedule) {
		Connection connection = MyConnection.getConnection();
		
		int num = 0;

		try {

			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from training_schedules");
			if (!rs.next()) {
				num = 1;

			} else {
				rs.last();
				num = rs.getInt("training_schedule_id") + 1;
			}
			String sql ="Insert into training_schedules(training_schedule_id,from_date,to_date,created_by,created_date,modified_by,modified_date,training_id,user_id)values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setDate(2, (Date) trainingSchedule.getFrom_date());
			pst.setDate(3,(Date) trainingSchedule.getTo_date());
			pst.setString(4, trainingSchedule.getCreated_by());
			pst.setDate(5, (Date) trainingSchedule.getCreated_date());
			pst.setString(6, trainingSchedule.getModified_by());
			pst.setDate(7, (Date) trainingSchedule.getModified_date());
			pst.setInt(8,trainingSchedule.getTraining_id());
			pst.setInt(9, trainingSchedule.getUser_id());
			
			int count = pst.executeUpdate();
	}catch(SQLException se){
		se.printStackTrace();
	}
	}
	
	@Override
	public void removeTrainingSchedule(int training_schedule_id) {
		
		PreparedStatement pst = null;

		String sql = "DELETE FROM training_schedules WHERE training_schedule_id=?";
		try{
			Connection connection = MyConnection.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, training_schedule_id);
			pst.executeUpdate();
			System.out.println("1 record deleted");
			
		}catch(SQLException se){
			se.printStackTrace();
		}
	}

	@Override
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule) {
		Connection connection = MyConnection.getConnection();
		PreparedStatement pst = null;
		try{
			 pst = connection.prepareStatement
				    ("UPDATE training_schedules SET from_date = ?, to_date = ?,modified_by = ?,modified_date = ?,user_id = ? WHERE training_schedule_id = ?");
			
				pst.setDate(1,(Date) trainingSchedule.getFrom_date());
				pst.setDate(2, (Date) trainingSchedule.getTo_date());
				pst.setString(3,trainingSchedule.getModified_by());
				pst.setDate(4, (Date) trainingSchedule.getModified_date());
				pst.setInt(5, trainingSchedule.getUser_id());
				pst.setInt(6, trainingSchedule.getTraining_schedule_id());
				int count = pst.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}

	@Override
	public List<AvailableTraining> allTrainings() {
		Statement stmt = null;
		ResultSet rs = null;

		List<AvailableTraining> list = new ArrayList<AvailableTraining>();
		
		try {
			
			Connection connection = MyConnection.getConnection();
			String sql = "SELECT tr.technology_name,trs.training_schedule_id,trs.from_date,trs.to_date FROM training_schedules trs, trainings tr WHERE tr.training_id=trs.training_id";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
			
				Training training = new Training();
				training.setTechnology_name(rs.getString("technology_name"));
				
				TrainingSchedule trainingSchedule = new TrainingSchedule();
				trainingSchedule.setTraining_schedule_id(rs.getInt("training_schedule_id"));
				trainingSchedule.setFrom_date(rs.getDate("from_date"));
				trainingSchedule.setTo_date(rs.getDate("to_date"));
			
				AvailableTraining availableTrainingBean = new AvailableTraining(training, trainingSchedule);
				list.add(availableTrainingBean);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<String, Integer> getTechnologies() {
		Statement stmt = null;
		ResultSet rs = null;
		try{
		Connection connection = MyConnection.getConnection();
		String sql = "SELECT * from trainings";
		
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()){	
			mapTrainings.put(rs.getString("technology_name"),rs.getInt("training_id"));
		}
		}catch(SQLException se){
			se.printStackTrace();
		}
		return mapTrainings;
	}

	

}
