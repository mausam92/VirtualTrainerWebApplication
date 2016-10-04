package com.yash.vta.service;

import java.util.List;

import com.yash.vta.model.AvailabilityBean;
import com.yash.vta.model.TimeSchedule;


public interface TimeScheduleService {
	
	public void addTimeSchedule(TimeSchedule timeschedules);

	public void blockTrainer(int id);
	
	public void unBlockTrainer(int id);

	public List<AvailabilityBean> blockedTrainers();

	public List<AvailabilityBean> availableTrainer();
	
	public List<AvailabilityBean> viewIndividualTimeSchedule();
	
	public void removeTimeSchedule(int time_schedule_id);
}
