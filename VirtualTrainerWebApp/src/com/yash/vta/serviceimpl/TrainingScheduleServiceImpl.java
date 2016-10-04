package com.yash.vta.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.TrainingScheduleDao;
import com.yash.vta.daoimpl.TrainingScheduleDaoImpl;
import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.TrainingSchedule;
import com.yash.vta.service.TrainingScheduleService;
import com.yash.vta.util.SessionUtils;

public class TrainingScheduleServiceImpl implements TrainingScheduleService {
	

	
	public void addTrainingSchedule(TrainingSchedule trainingSchedule){
		trainingSchedule.setUser_id(SessionUtils.getUserID());
		trainingSchedule.setCreated_by(SessionUtils.getUsername());
		trainingSchedule.setModified_by(SessionUtils.getUsername());
		trainingSchedule.setCreated_date(new java.sql.Date(new Date().getTime()));
		trainingSchedule.setModified_date(new java.sql.Date(new Date().getTime()));
		TrainingScheduleDao dao = new TrainingScheduleDaoImpl(); 
		
		dao.addTrainingSchedule(trainingSchedule);
	
	}

	public void removeTrainingSchedule(int training_schedule_id){
		TrainingScheduleDao dao = new TrainingScheduleDaoImpl();
		dao.removeTrainingSchedule(training_schedule_id);
	}
	
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule){
		trainingSchedule.setModified_by(SessionUtils.getUsername());
		trainingSchedule.setModified_date(new java.sql.Date(new Date().getTime()));
		trainingSchedule.setUser_id(SessionUtils.getUserID());
		TrainingScheduleDao dao = new TrainingScheduleDaoImpl();
		dao.updateTrainingSchedule(trainingSchedule);
	}
	
	public List<AvailableTraining> allTrainings(){
		
		TrainingScheduleDao dao =new TrainingScheduleDaoImpl();
		List<AvailableTraining> list = dao.allTrainings();;
		
		return list; 
	}

	@Override
	public Map<String, Integer> getTechnologies() {
		TrainingScheduleDao dao =new TrainingScheduleDaoImpl();
		
		return dao.getTechnologies();
	}
	
	
}
