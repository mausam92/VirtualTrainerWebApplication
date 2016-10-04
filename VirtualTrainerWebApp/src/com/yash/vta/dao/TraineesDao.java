package com.yash.vta.dao;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.Trainee;
import com.yash.vta.model.TraineeModel;

public interface TraineesDao {

	public void addTrainees(Trainee trainees);

	public List<TraineeModel> showAllTrainees();

	public Map<String, Integer> getAllBatches();

	public Map<String, Integer> getAllUserName();

	public void deleteTrainees(Trainee trainees);

	public void updateTrainees(Trainee trainees);

}
