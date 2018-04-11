package com.job.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
	Page<JobSeeker> findByCompanyId(Long companyId, Pageable pageable);
	Page<JobSeeker> findByCompanyIdAndExaminationId(Long companyId, Long examinationId, Pageable pageable);
	Optional<JobSeeker> findByAccessId(String accessId);

}
