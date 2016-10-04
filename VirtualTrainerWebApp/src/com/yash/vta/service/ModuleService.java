package com.yash.vta.service;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.Module;
import com.yash.vta.model.Modules;

public interface ModuleService {
	
	public Map<String, Integer> trainingIds();

	public void addModule(Module module);

	public List<Modules> getAllModules();

	public void deleteModule(Module mod);
	public boolean checkModule(String name, int training_id);
	public void updateModule(Module module);
}
