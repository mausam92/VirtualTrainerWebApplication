package com.yash.vta.dao;

import java.util.List;

import com.yash.vta.model.Training;

public interface TrainingDao{

	public void addTraining(Training training);
	public List<Training> showAllTrainings();
	public void deleteTraining(Training training);
	public void updateTraining(Training training);
}
