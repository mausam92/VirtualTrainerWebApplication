package com.yash.vta.model;

import java.util.Date;

public class Nomination {

	private int nomination_id;
	private int user_id;
	private int training_schedule_id;
	private Date created_date;
	public String nomination_status;
	private int trainingId;
	
	public Nomination() {

	}

	public Nomination(int nomination_id, int user_id, int training_schedule_id,
			Date created_date, String nomination_status) {
		super();
		this.nomination_id = nomination_id;
		this.user_id = user_id;
		this.training_schedule_id = training_schedule_id;
		this.created_date = created_date;
		this.nomination_status = nomination_status;
	}

	public int getNomination_id() {
		return nomination_id;
	}

	public void setNomination_id(int nomination_id) {
		this.nomination_id = nomination_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTraining_schedule_id() {
		return training_schedule_id;
	}

	public void setTraining_schedule_id(int training_schedule_id) {
		this.training_schedule_id = training_schedule_id;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getNomination_status() {
		return nomination_status;
	}

	public void setNomination_status(String nomination_status) {
		this.nomination_status = nomination_status;
	}

	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

}
