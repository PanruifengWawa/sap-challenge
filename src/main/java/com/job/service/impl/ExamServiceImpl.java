package com.job.service.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.job.exceptions.AuthException;
import com.job.exceptions.DBException;
import com.job.exceptions.ParameterException;
import com.job.model.Examination;
import com.job.model.Job;
import com.job.repository.ExaminationRepository;
import com.job.repository.JobRepository;
import com.job.service.ExamService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;
import com.job.util.UUIDGenerator;


@Service
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	ExaminationRepository examinationRepository;

	@Override
	public DataPageWrapper<List<Examination>> getExamList(Long companyId, Integer pageIndex, Integer pageSize) {
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
		
		Page<Examination> page = examinationRepository.findByCompanyId(companyId, pageable);
		DataPageWrapper<List<Examination>> dataPageWrapper = new DataPageWrapper<List<Examination>>();
		dataPageWrapper.setData(page.getContent());
		dataPageWrapper.setTotalNumber((int)page.getTotalElements());
		dataPageWrapper.setPageIndex(pageIndex);
		dataPageWrapper.setTotalPage(page.getTotalPages());
		dataPageWrapper.setPageSize(pageSize);
		return dataPageWrapper;
	}

	@Override
	public DataWrapper<Void> addExam(Examination examination) {
		if (examination.getCompanyId() == null || examination.getName() == null || examination.getName().trim().equals("") || examination.getJobId() == null || examination.getContent() == null) {
			throw new ParameterException("参数错误");
		}
		Optional<Job> job = jobRepository.findById(examination.getJobId());
		if (job.isPresent() && job.get().getCompanyId().equals(examination.getCompanyId())) {
			examination.setJobPostion(job.get().getPosition());
		} else {
			throw new AuthException("职位不存在或者职位不属于本公司");
		}
		try {
			JSONArray exams = new JSONArray(examination.getContent());
			if (exams.length() == 0) {
				throw new ParameterException("选项错误");
			}
			
			for(int i = 0 ; i < exams.length(); i++) {
				JSONObject exam = exams.getJSONObject(i);
				exam.put("uuid", UUIDGenerator.getCode());
			}
			examination.setContent(exams.toString());
		} catch (Exception e) {
			throw new ParameterException("选项错误");
		}
		
		examination.setId(null);
		try {
			examinationRepository.save(examination);
		} catch (Exception e) {
			throw new DBException("数据库错误", e);
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteExam(Long examId, Long companyId) {
		DataWrapper<Void> dataWrapper;
		Optional<Examination> examination = examinationRepository.findById(examId);
		if (examination.isPresent()) {
			if (examination.get().getCompanyId().equals(companyId)) {
				try {
					examinationRepository.delete(examination.get());
				} catch (Exception e) {
					throw new DBException("数据库错误", e);
				}
			} else {
				throw new AuthException("权限错误");
			}
		} else {
			throw new ParameterException("考卷不存在");
		}
		dataWrapper = new DataWrapper<>();
		return dataWrapper;
	}

}
