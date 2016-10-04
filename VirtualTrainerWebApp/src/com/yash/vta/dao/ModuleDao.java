package com.yash.vta.dao;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.Module;
import com.yash.vta.model.Modules;

public interface ModuleDao {
	
	public void addModule(Module module);

	public List<Modules> getAllModules();

	public void deleteModule(Module mod);

	public void updateModule(Module module);

	public Map<String, Integer> trainingIds();

	public boolean checkModule(String name, int training_id);

}
