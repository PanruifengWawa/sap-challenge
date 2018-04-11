package com.job.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	Page<Job> findByCompanyId(Long companyId, Pageable pageable);
	
	List<Job> findByCompanyIdAndPosition(Long companyId, String position);

}
