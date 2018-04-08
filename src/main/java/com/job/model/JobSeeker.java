package com.job.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job_seeker")
public class JobSeeker {
	private Long id;
	private String accessId;
	private Integer score;
	private Date finishedDate;
	private String choice;
	private Long examinationId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "access_id")
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	
	@Basic
    @Column(name = "score")
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	@Basic
    @Column(name = "finished_date")
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	
	@Basic
    @Column(name = "choice")
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	@Basic
    @Column(name = "examination_id")
	public Long getExaminationId() {
		return examinationId;
	}
	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
	
}
