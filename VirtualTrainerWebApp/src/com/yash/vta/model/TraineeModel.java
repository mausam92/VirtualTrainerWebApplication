package com.yash.vta.model;

public class TraineeModel {

	private Trainee trainee;
	private Batch batch;
	private User user;

	public TraineeModel() {
		super();
	}

	public TraineeModel(Trainee trainee, Batch batch, User user) {
		super();
		this.trainee = trainee;
		this.batch = batch;
		this.user = user;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
