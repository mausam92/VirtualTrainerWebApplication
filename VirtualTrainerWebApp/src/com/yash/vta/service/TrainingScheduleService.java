package com.yash.vta.service;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.TrainingSchedule;

public interface TrainingScheduleService {

	public void addTrainingSchedule(TrainingSchedule trainingSchedule);
	public void removeTrainingSchedule(int time_schedule_id);
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule);
	public List<AvailableTraining> allTrainings();
	public Map<String,Integer> getTechnologies();
	
}
