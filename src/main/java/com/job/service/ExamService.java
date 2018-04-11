package com.job.service;

import java.util.List;

import com.job.model.Examination;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

public interface ExamService {
	DataPageWrapper<List<Examination>> getExamList(Long companyId,Integer pageIndex, Integer pageSize);

	DataWrapper<Void> addExam(Examination examination);
	
	DataWrapper<Void> deleteExam(Long examId, Long companyId);
}
