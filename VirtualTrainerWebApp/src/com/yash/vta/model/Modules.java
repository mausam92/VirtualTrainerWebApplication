package com.yash.vta.model;

public class Modules {
	
	private Training training;
	private Module module;

	public Training getTraining() {
		return training;
	}

	public Modules(Training training, Module module) {
		super();
		this.training = training;
		this.module = module;
	}

	public Modules() {
		super();

	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
