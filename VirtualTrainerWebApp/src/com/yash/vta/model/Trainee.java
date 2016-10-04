package com.yash.vta.model;

public class Trainee {
	
	private int trainee_id;
	private int batch_id;
	private int user_id;

	public Trainee() {

	}

	public Trainee(int trainee_id, int batch_id, int user_id) {
		super();
		this.trainee_id = trainee_id;
		this.batch_id = batch_id;
		this.user_id = user_id;
	}

	public int getTrainee_id() {
		return trainee_id;
	}

	public void setTrainee_id(int trainee_id) {
		this.trainee_id = trainee_id;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
