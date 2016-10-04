package com.yash.vta.model;

import java.util.Date;

public class TimeSchedule {

	private int time_schedule_id;
	private int user_id;
	private Date from_date;
	private Date to_date;
	private String availability;

	public TimeSchedule() {

	}

	public TimeSchedule(int time_schedule_id, int user_id, Date from_date,
			Date to_date, String availability) {
		super();
		this.time_schedule_id = time_schedule_id;
		this.user_id = user_id;
		this.from_date = from_date;
		this.to_date = to_date;
		this.availability = availability;
	}

	public int getTime_schedule_id() {
		return time_schedule_id;
	}

	public void setTime_schedule_id(int time_schedule_id) {
		this.time_schedule_id = time_schedule_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	@Override
	public String toString() {
		return "TimeSchedules [from_date=" + from_date + ", to_date=" + to_date
				+ ", availability=" + availability + "]";
	}

}
