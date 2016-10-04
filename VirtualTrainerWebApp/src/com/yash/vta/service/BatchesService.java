package com.yash.vta.service;

import java.util.List;
import java.util.Map;

import com.yash.vta.model.Batch;

public interface BatchesService {
	
	public void addBatches(Batch batches);

	public List<Batch> showAllBatches();

	public Map<String, Integer> getTrainingScheduleIds();

	public void deleteBatches(Batch batches);

	public void updateBatches(Batch batches);
}
