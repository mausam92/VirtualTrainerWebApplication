package com.yash.vta.model;

import com.yash.vta.model.User;

public class AvailabilityBean {

	private User user;
	private TimeSchedule schedules;
	
	public AvailabilityBean() {
	
	}
	
	public AvailabilityBean(User user, TimeSchedule schedules) {
		super();
		this.user = user;
		this.setSchedules(schedules);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public TimeSchedule getSchedules() {
		return schedules;
	}
	public void setSchedules(TimeSchedule schedules) {
		this.schedules = schedules;
	}
	
}
