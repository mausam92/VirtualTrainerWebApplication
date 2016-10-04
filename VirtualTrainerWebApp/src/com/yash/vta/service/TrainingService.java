package com.yash.vta.service;

import java.util.List;

import com.yash.vta.model.Training;


public interface TrainingService {
	
	public void addTraining(Training training);
	public List<Training> showAllTrainings();
	public void deleteTraining(Training training);
	public void updateTraining(Training training);

}
