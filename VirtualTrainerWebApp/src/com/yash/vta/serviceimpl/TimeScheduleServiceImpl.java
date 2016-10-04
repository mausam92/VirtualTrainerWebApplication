package com.yash.vta.serviceimpl;

import java.util.List;

import com.yash.vta.dao.TimeSchedulesDao;
import com.yash.vta.daoimpl.TimeSchedulesImpl;
import com.yash.vta.model.AvailabilityBean;
import com.yash.vta.model.TimeSchedule;
import com.yash.vta.service.TimeScheduleService;
import com.yash.vta.util.SessionUtils;

public class TimeScheduleServiceImpl implements TimeScheduleService {

	TimeSchedulesDao dao = new TimeSchedulesImpl();

	@Override
	public void addTimeSchedule(TimeSchedule timeschedules) {

		timeschedules.setUser_id(SessionUtils.getUserID());
		dao.addTimeSchedule(timeschedules);

	}

	@Override
	public void blockTrainer(int id) {
		dao.blockTrainer(id);

	}

	@Override
	public void unBlockTrainer(int id) {
		dao.unBlockTrainer(id);

	}

	@Override
	public List<AvailabilityBean> availableTrainer() {
		List<AvailabilityBean> availableTrainer = dao.availableTrainer();
		return availableTrainer;
	}

	@Override
	public List<AvailabilityBean> blockedTrainers() {
		List<AvailabilityBean> blockedTrainers = dao.blockedTrainers();
		return blockedTrainers;
	}
	
	@Override
	public List<AvailabilityBean> viewIndividualTimeSchedule() {
		
		List<AvailabilityBean> listTrainerSchedule = dao.viewIndividualTimeSchedule(SessionUtils.getUserID());
		return listTrainerSchedule;
	}

	@Override
	public void removeTimeSchedule(int time_schedule_id) {
		dao.removeTimeSchedule(time_schedule_id);
		
	}

}
