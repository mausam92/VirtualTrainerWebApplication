package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yash.vta.dao.TimeSchedulesDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.AvailabilityBean;
import com.yash.vta.model.TimeSchedule;
import com.yash.vta.model.User;

public class TimeSchedulesImpl implements TimeSchedulesDao {

	@Override
	public void addTimeSchedule(TimeSchedule timeschedules) {
		Connection connection = MyConnection.getConnection();

		int num = 0;

		try {

			Statement stmt = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from time_schedules");
			if (!rs.next()) {
				num = 1;

			} else {
				rs.last();
				num = rs.getInt("time_schedule_id") + 1;
			}

			String sql = "Insert into time_schedules(time_schedule_id,user_id,from_date,to_date,availability)values(?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setInt(2, timeschedules.getUser_id());
			pst.setDate(3, (Date) timeschedules.getFrom_date());
			pst.setDate(4, (Date) timeschedules.getTo_date());
			pst.setString(5, timeschedules.getAvailability());
			int count = pst.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void blockTrainer(int id) {
		PreparedStatement pst = null;
		System.out.println(id);
		try {
			Connection connection = MyConnection.getConnection();
			String sql = "UPDATE time_schedules SET availability='not available' where user_id=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			int count = pst.executeUpdate();
			System.out.println(count);
			if (count == 1) {
				System.out.println("------Updated--------");
				System.out.println("blocked");
				connection.close();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void unBlockTrainer(int id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection connection = MyConnection.getConnection();
			String sql = "UPDATE time_schedules SET availability='available' where user_id=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			int count = pst.executeUpdate();
			System.out.println(count);
			if (count == 1) {
				System.out.println("-------Updated---------");
				connection.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println(" Un blocked");
	}

	@Override
	public List<AvailabilityBean> availableTrainer() {
		Statement stmt = null;
		ResultSet rs = null;

		List<AvailabilityBean> list = new ArrayList<AvailabilityBean>();

		try {

			Connection connection = MyConnection.getConnection();
			String sql = "SELECT users.user_id,user_name,from_date,to_date,availability FROM users,time_schedules WHERE availability='available'And from_date>=current_date AND users.user_id=time_schedules.user_id";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt("user_id"));
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getDate("from_date"));
				System.out.println(rs.getDate("to_date"));

				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));

				TimeSchedule timeschedule = new TimeSchedule();
				timeschedule.setFrom_date(rs.getDate("from_date"));
				timeschedule.setTo_date(rs.getDate("to_date"));
				timeschedule.setAvailability(rs.getString("availability"));

				AvailabilityBean bean = new AvailabilityBean(user, timeschedule);
				list.add(bean);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AvailabilityBean> blockedTrainers() {
		Statement stmt = null;
		ResultSet rs = null;

		List<AvailabilityBean> listBlockedTrainers = new ArrayList<AvailabilityBean>();

		try {

			Connection connection = MyConnection.getConnection();
			String sql = "SELECT users.user_id,user_name,from_date,to_date,availability FROM users,time_schedules WHERE availability='not available' AND users.user_id=time_schedules.user_id";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt("user_id"));
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getDate("from_date"));
				System.out.println(rs.getDate("to_date"));

				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				TimeSchedule timeschedule = new TimeSchedule();
				timeschedule.setFrom_date(rs.getDate("from_date"));
				timeschedule.setTo_date(rs.getDate("to_date"));
				timeschedule.setAvailability(rs.getString("availability"));
				AvailabilityBean bean = new AvailabilityBean(user, timeschedule);
				listBlockedTrainers.add(bean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listBlockedTrainers;
	}

	@Override
	public List<AvailabilityBean> viewIndividualTimeSchedule(int user_id) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		List<AvailabilityBean> listTrainerSchedule = new ArrayList<AvailabilityBean>();

		try {

			Connection connection = MyConnection.getConnection();

			pst = connection
					.prepareStatement("SELECT * FROM time_schedules WHERE time_schedules.user_id = ?");
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while (rs.next()) {

				System.out.println(rs.getInt("time_schedule_id"));

				System.out.println(rs.getDate("from_date"));
				System.out.println(rs.getDate("to_date"));

				User user = new User();
				user.setUser_id(rs.getInt("user_id"));

				TimeSchedule timeschedule = new TimeSchedule();
				timeschedule.setTime_schedule_id(rs.getInt("time_schedule_id"));
				timeschedule.setFrom_date(rs.getDate("from_date"));
				timeschedule.setTo_date(rs.getDate("to_date"));

				AvailabilityBean bean = new AvailabilityBean(user, timeschedule);
				listTrainerSchedule.add(bean);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listTrainerSchedule;
	}

	@Override
	public void removeTimeSchedule(int time_schedule_id) {
		PreparedStatement pst = null;
		System.out.println("----------------" + time_schedule_id);

		String sql = "DELETE FROM time_schedules WHERE time_schedule_id=?";
		try {
			Connection connection = MyConnection.getConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, time_schedule_id);
			int count = pst.executeUpdate();
			if (count == 1) {
				System.out.println("1 record deleted");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
