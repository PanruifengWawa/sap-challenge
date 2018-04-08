package com.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

}
