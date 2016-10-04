package com.yash.vta.dao;

import java.util.List;

import com.yash.vta.model.AvailabilityBean;
import com.yash.vta.model.TimeSchedule;

public interface TimeSchedulesDao {
	
	public void addTimeSchedule(TimeSchedule timeschedules);

	//public List viewTimeSchedules();

	public void blockTrainer(int id);
	
	public void unBlockTrainer(int id);
	
	public List<AvailabilityBean> blockedTrainers();
	
	public List<AvailabilityBean> availableTrainer();
	
	public List<AvailabilityBean> viewIndividualTimeSchedule(int user_id);
	
	public void removeTimeSchedule(int time_schedule_id);
}

