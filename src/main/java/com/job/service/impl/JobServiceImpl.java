package com.job.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.job.exceptions.AuthException;
import com.job.exceptions.DBException;
import com.job.exceptions.ParameterException;
import com.job.model.Job;
import com.job.repository.JobRepository;
import com.job.service.JobService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	JobRepository jobRepository;

	@Override
	public DataPageWrapper<List<Job>> getJobList(Long companyId, Integer pageIndex, Integer pageSize) {
		if (companyId == null) {
			throw new ParameterException("参数错误");
		}
		Pageable pageable = null;
		
		if (pageIndex != null && pageSize != null) {
			pageable = PageRequest.of(pageIndex - 1, pageSize);
		} else {
			pageIndex = -1;
			pageSize = -1;
		}
		
		Page<Job> page = jobRepository.findByCompanyId(companyId, pageable);
		DataPageWrapper<List<Job>> dataPageWrapper = new DataPageWrapper<List<Job>>();
		dataPageWrapper.setData(page.getContent());
		dataPageWrapper.setTotalNumber((int)page.getTotalElements());
		dataPageWrapper.setPageIndex(pageIndex);
		dataPageWrapper.setTotalPage(page.getTotalPages());
		dataPageWrapper.setPageSize(pageSize);
		return dataPageWrapper;
	}

	@Override
	public DataWrapper<Void> addJob(Job job) {
		if (job.getCompanyId() == null || job.getPosition() == null || job.getPosition().equals("") || job.getDescription() == null || job.getDescription().equals("")) {
			throw new ParameterException("参数错误");
		}
		
		List<Job> jobsExisted = jobRepository.findByCompanyIdAndPosition(job.getCompanyId(), job.getPosition());
		if (jobsExisted != null && jobsExisted.size() > 0) {
			throw new ParameterException("职位已存在");
		}
		
		DataWrapper<Void> dataWrapper;
		job.setId(null);
		
		try {
			jobRepository.save(job);
		} catch (Exception e) {
			throw new DBException("数据库错误", e);
		}
		dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteJob(Long jobId, Long companyId) {
		
		DataWrapper<Void> dataWrapper;
		Optional<Job> job = jobRepository.findById(jobId);
		if (job.isPresent()) {
			if (job.get().getCompanyId().equals(companyId)) {
				try {
					jobRepository.delete(job.get());
				} catch (Exception e) {
					throw new DBException("数据库错误", e);
				}
			} else {
				throw new AuthException("权限错误");
			}
		} else {
			throw new ParameterException("职位不存在");
		}
		dataWrapper = new DataWrapper<>();
		return dataWrapper;
	}

}
