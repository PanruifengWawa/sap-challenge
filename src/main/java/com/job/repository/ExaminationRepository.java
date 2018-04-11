package com.job.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
	Page<Examination> findByCompanyId(Long companyId, Pageable pageable);
}
