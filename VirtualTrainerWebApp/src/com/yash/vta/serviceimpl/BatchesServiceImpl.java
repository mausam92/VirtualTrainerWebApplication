package com.yash.vta.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.BatchesDao;
import com.yash.vta.daoimpl.BatchesDaoImpl;
import com.yash.vta.model.Batch;
import com.yash.vta.service.BatchesService;

public class BatchesServiceImpl implements BatchesService {
	
	BatchesDao dao = new BatchesDaoImpl();

	@Override
	public void addBatches(Batch batches) {
		dao.addBatches(batches);
	}

	@Override
	public List<Batch> showAllBatches() {
		List<Batch> listBatches = dao.showAllBatches();
		return listBatches;
	}

	@Override
	public Map<String, Integer> getTrainingScheduleIds() {
		
		Map<String, Integer> training_schedule_map = dao.getTrainingScheduleIds();
		
		return training_schedule_map;
	}

	@Override
	public void deleteBatches(Batch batches) {
		
	}

	@Override
	public void updateBatches(Batch batches) {

	}
	

	
	
}
