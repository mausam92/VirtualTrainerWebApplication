package com.yash.vta.dao;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.TrainingSchedule;

public interface TrainingScheduleDao {
	public void addTrainingSchedule(TrainingSchedule trainingSchedule);
	public void removeTrainingSchedule(int id);
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule);
	public List<AvailableTraining> allTrainings();
	public Map<String,Integer> getTechnologies();
	
}
