package com.yash.vta.serviceimpl;

import java.util.List;

import com.yash.vta.daoimpl.NominationDaoImpl;
import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.Nomination;
import com.yash.vta.service.NominationService;
import com.yash.vta.util.SessionUtils;

public class NominationServiceImpl implements NominationService {

	NominationDaoImpl nominationDao = new NominationDaoImpl();

	@Override
	public void addNomination(Nomination nomination) {
		final String nomination_status = "pending";
		nomination.setNomination_status(nomination_status);
		nomination.setUser_id(SessionUtils.getUserID());
		System.out.println("service called");
		nominationDao.addNomination(nomination);
	}

	@Override
	public List<Nomination> getAllNominations() {
		List<Nomination> listnominations = nominationDao.getAllNominations();
		return listnominations;
	}

	@Override
	public List<AvailableTraining> getAllTrainingSchedules() {
		List<AvailableTraining> listAvailableTraining = nominationDao.getAllTrainingSchedules();
		return listAvailableTraining;
	}

	@Override
	public boolean checkNominee(int ScheduleId) {
		int userId = SessionUtils.getUserID();
		boolean nominee = nominationDao.checkNominee(userId, ScheduleId);
		return nominee;
	}
}

