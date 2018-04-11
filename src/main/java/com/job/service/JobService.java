package com.job.service;

import java.util.List;

import com.job.model.Job;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

public interface JobService {
	DataPageWrapper<List<Job>> getJobList(Long companyId, Integer pageIndex, Integer pageSize);
	
	DataWrapper<Void> addJob(Job job);
	
	DataWrapper<Void> deleteJob(Long jobId, Long companyId);

}
