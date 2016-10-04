package com.yash.vta.service;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.Trainee;
import com.yash.vta.model.TraineeModel;

public interface TraineeService {
	
	public void addTrainees(Trainee trainees);
	
	public List<TraineeModel> showAllTrainees();
	
	public void deleteTrainees(Trainee trainees);
	
	public void updateTrainees(Trainee trainees);
	
	public  Map<String,Integer> getUsers();
	
	public  Map<String,Integer> getbatches();
	
	public Map<String, Integer> getAllUserName();
}

