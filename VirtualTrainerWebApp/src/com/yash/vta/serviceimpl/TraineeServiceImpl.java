package com.yash.vta.serviceimpl;

import java.util.List;
import java.util.Map;

import com.yash.vta.dao.TraineesDao;
import com.yash.vta.dao.UserDao;
import com.yash.vta.daoimpl.TraineesDaoImpl;
import com.yash.vta.daoimpl.UserDaoImpl;
import com.yash.vta.model.Trainee;
import com.yash.vta.model.TraineeModel;
import com.yash.vta.service.TraineeService;

public class TraineeServiceImpl implements TraineeService {
	
	private List<TraineeModel> trainees = null;
	private Map<String,Integer> users = null;
	private Map<String,Integer> batches = null;
	private Map<String,Integer> userNames = null;

	private TraineesDao traineesDao = new TraineesDaoImpl();
	
	private UserDao userDao = new UserDaoImpl();
	

	@Override
	public void addTrainees(Trainee trainees) {

		traineesDao.addTrainees(trainees);

	}

	@Override
	public List<TraineeModel> showAllTrainees() {
		trainees = traineesDao.showAllTrainees();

		return trainees;
	}

	@Override
	public void deleteTrainees(Trainee trainees) {
		traineesDao.deleteTrainees(trainees);

	}

	public void updateTrainees(Trainee trainees) {

		traineesDao.updateTrainees(trainees);
	}

	@Override
	public Map<String, Integer> getUsers() {
		users= traineesDao.getAllUserName();
	
		return users;
	}

	@Override
	public Map<String, Integer> getbatches() {
		batches= traineesDao.getAllBatches();
		return batches;
	}

	
	@Override
	public Map<String, Integer> getAllUserName() {
		userNames = userDao.getAllUserName();
		return userNames;
	}


}

