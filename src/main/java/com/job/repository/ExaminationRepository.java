package com.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

}
