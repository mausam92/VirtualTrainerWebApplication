package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.yash.vta.dao.UserDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User user) {
		try {
			Connection connection = MyConnection.getConnection();
			String sql = "Insert into Users (user_id,user_name,user_status,user_mobile,user_mail,user_manager,role_id,user_manager_mail)values (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setString(2, user.getUser_name());
			pst.setString(3, user.getUser_status());
			pst.setString(4, user.getUser_mobile());
			pst.setString(5, user.getUser_mail());
			pst.setString(6, user.getUser_manager());
			pst.setInt(7, user.getRole_id());
			pst.setString(8, user.getUser_manager_mail());
			
		    int count = pst.executeUpdate();
		    System.out.println("Count = "+count);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean checkExistingUser(User user) {
		boolean userExists = false;

		try {

			Connection connection = MyConnection.getConnection();

			String sql = "select * from users";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			 
			while(rs.next()) {
				String userMailCounter = rs.getString("user_mail");
				if (userMailCounter.equals(user.getUser_mail())) {
					userExists = true;
				}
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		return userExists;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection connection = MyConnection.getConnection();
			String sql = "select * from users";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_status(rs.getString("user_status"));
				user.setUser_mobile(rs.getString("user_mobile"));
				user.setUser_mail(rs.getString("user_mobile"));
				user.setUser_manager(rs.getString("user_manager"));
				list.add(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public User deleteUser(int user_id) {
		try {
			Connection connection = MyConnection.getConnection();
			PreparedStatement ps = null;
			String sql = "DELETE FROM users WHERE user_id=?";
			ps = connection.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("User deleted successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public User getUser(User user){
		
		try{
			Connection connection = MyConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE user_mail=?");
			pst.setString(1, user.getUser_mail());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				
				User usernew = new User();
				usernew.setUser_id(rs.getInt("user_id"));
				usernew.setUser_name(rs.getString("user_name"));
				usernew.setUser_status(rs.getString("user_status"));
				usernew.setUser_mobile(rs.getString("user_mobile"));
				usernew.setUser_mail(rs.getString("user_mail"));
				usernew.setUser_manager(rs.getString("user_manager"));
				usernew.setRole_id(rs.getInt("role_id"));
				/*usernew.setUser_designation(rs.getString("user_designation"));*/
				usernew.setUser_manager_mail(rs.getString("user_manager_mail"));
				
				return usernew;
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Integer> getAllUserName() {
		
		Connection connection = MyConnection.getConnection();
		Map<String, Integer> userNames =  new TreeMap<String, Integer>();
		
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select user_name,User_id from users");
			while(rs.next()){
				userNames.put(rs.getString("user_name"),rs.getInt("user_id"));
				
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return null;
	}
}
