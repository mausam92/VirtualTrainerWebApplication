package com.yash.vta.dao;

import java.util.List;

import com.yash.vta.model.AvailableTraining;
import com.yash.vta.model.Nomination;



public interface NominationDao {

	public void addNomination(Nomination nomination);

	public List<Nomination> getAllNominations();

	public List<AvailableTraining> getAllTrainingSchedules();

	public boolean checkNominee(int userId, int ScheduleId);
}
