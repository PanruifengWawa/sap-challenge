package com.job.service;

import java.util.List;
import java.util.Map;

import com.job.model.JobSeeker;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

public interface SeekerService {
	DataPageWrapper<List<JobSeeker>> getSeekerList(Long companyId, Long examId, Integer pageIndex, Integer pageSize);
	
	DataWrapper<List<String>> addSeeker(Long companyId, Long examId, String[] labels);
	
	DataWrapper<Void> deleteSeeker(Long seekerId, Long companyId);
	
	DataWrapper<String> getByAccessId(String accessId);

	 DataWrapper<Void> finish(String accessId, Map<String, String> answer);
}
