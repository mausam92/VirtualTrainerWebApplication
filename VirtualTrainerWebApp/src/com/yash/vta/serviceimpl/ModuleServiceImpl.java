package com.yash.vta.serviceimpl;

import java.util.List;
import java.util.Map;

import com.yash.vta.dao.ModuleDao;
import com.yash.vta.daoimpl.ModuleDaoImpl;
import com.yash.vta.model.Module;
import com.yash.vta.model.Modules;
import com.yash.vta.service.ModuleService;
import com.yash.vta.util.SessionUtils;

public class ModuleServiceImpl implements ModuleService {

	
	List<Modules> modules;
	private Map<String,Integer> trainingIds;
	
	@Override
	public Map<String,Integer> trainingIds() {
		
		ModuleDao moduleDao = new ModuleDaoImpl();
		 trainingIds = moduleDao.trainingIds();
		return  trainingIds;
	}

	@Override
	public void addModule(Module module) {
		
		ModuleDao moduleDao = new ModuleDaoImpl();
	
		module.setCreated_by(SessionUtils.getUsername());
		moduleDao.addModule(module);

	}
	
	@Override
	public List<Modules> getAllModules(){
		
		ModuleDao moduleDao = new ModuleDaoImpl();
		modules = moduleDao.getAllModules();
		return modules;
	}

	@Override
	public void deleteModule(Module mod) {
		
		ModuleDao moduleDao = new ModuleDaoImpl();
		moduleDao.deleteModule(mod);
		
	}
	public boolean checkModule(String name, int training_id){
		ModuleDao moduleDao = new ModuleDaoImpl();
		boolean module = moduleDao.checkModule(name, training_id);
		return module;
		
	}

	@Override
	public void updateModule(Module module) {
	module.setUpdated_by(SessionUtils.getUsername());
	module.setUpdated_date(new java.sql.Date(new java.util.Date().getTime()));
	
	ModuleDao moduleDao = new ModuleDaoImpl();
	moduleDao.updateModule(module);
	
	}
	
}
