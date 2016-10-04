package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yash.vta.dao.TrainingDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.Training;

public class TrainingDaoImpl implements TrainingDao {

	private Connection con = null;

	public TrainingDaoImpl() {

	}

	public void addTraining(Training training) {
		int id = 0;
		try {
			
			con = MyConnection.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from trainings");
            if(!rs.next()){
                  id = 1;
                  
            } else{
                  rs.last();
                  
                  id = rs.getInt("training_id") + 1;  
            }

			
			con = MyConnection.getConnection();
			System.out.println(con);

			PreparedStatement pst = con
					.prepareStatement("insert into trainings(technology_name,training_id) values(?,?)");
			pst.setString(1, training.getTechnology_name());
			pst.setInt(2, id);
			int succsess = pst.executeUpdate();
			System.out.println(succsess);
			if (succsess == 1) {

				con.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Training> showAllTrainings() {
		List<Training> trainings = new ArrayList<Training>();
		try {
			con = MyConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TRAININGS");
			Training training = null;
			while (rs.next()) {
				training = new Training();
				training.setTraining_id(rs.getInt("training_id"));
				training.setTechnology_name(rs.getString("technology_name"));
				trainings.add(training);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return trainings;
	}

	@Override
	public void deleteTraining(Training training) {
		try {
			con = MyConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("Delete from TRAININGS where training_id =?");
			pst.setInt(1, training.getTraining_id());
			int succsess = pst.executeUpdate();
			if(succsess == 1){
				System.out.println("-------deleted-----------");
				con.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void updateTraining(Training training) {
		try{
		con = MyConnection.getConnection();
		PreparedStatement pst = con.prepareStatement("UPDATE TRAININGS  SET technology_name = ? WHERE TRAINING_ID = ?");
		 pst.setString(1, training.getTechnology_name());
		 pst.setInt(2, training.getTraining_id());
		 int succsess=pst.executeUpdate();
		 if(succsess == 1){
			 System.out.println("------ updated-------");
			 con.close();
		 }
		}catch(SQLException ex){
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
				
			}
			ex.printStackTrace();
		}
	}

}
