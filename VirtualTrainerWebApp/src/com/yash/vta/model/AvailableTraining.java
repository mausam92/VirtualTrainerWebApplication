package com.yash.vta.model;

public class AvailableTraining {
	
	private Training training;
	private TrainingSchedule schedules;
	
	public AvailableTraining(){
		
	}
	
	public AvailableTraining(Training training, TrainingSchedule schedules) {
		super();
		this.training = training;
		this.schedules = schedules;
	}
	
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public TrainingSchedule getSchedules() {
		return schedules;
	}
	public void setSchedules(TrainingSchedule schedules) {
		this.schedules = schedules;
	}
	
	

}