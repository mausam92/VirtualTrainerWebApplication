package com.yash.vta.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.vta.dao.TrainingDao;
import com.yash.vta.daoimpl.TrainingDaoImpl;
import com.yash.vta.model.Training;
import com.yash.vta.service.TrainingService;


public class TrainingServiceImpl implements TrainingService {
	private List<Training> trainings = null;


    public TrainingServiceImpl() {
    	trainings = new ArrayList<Training>();
    	
    }

	public void addTraining(Training training) {
	 TrainingDao trainingDao = new TrainingDaoImpl();
		
		trainingDao.addTraining(training);
		
	}


	@Override
	public List<Training> showAllTrainings() {
		TrainingDao trainingDao = new TrainingDaoImpl();
		trainings = trainingDao.showAllTrainings();
		
		return trainings;
	}

	@Override
	public void deleteTraining(Training training) {
	 TrainingDao trainingDao = new TrainingDaoImpl();
		trainingDao.deleteTraining(training);
		
	}
	public void updateTraining(Training training){
		TrainingDao trainingDao = new TrainingDaoImpl();
		trainingDao.updateTraining(training);
	}
}
