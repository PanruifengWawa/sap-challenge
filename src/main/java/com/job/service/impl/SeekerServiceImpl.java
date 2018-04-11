package com.job.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.exceptions.AuthException;
import com.job.exceptions.DBException;
import com.job.exceptions.ParameterException;
import com.job.model.Examination;
import com.job.model.JobSeeker;
import com.job.repository.ExaminationRepository;
import com.job.repository.JobSeekerRepository;
import com.job.service.SeekerService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;
import com.job.util.UUIDGenerator;

@Service
public class SeekerServiceImpl implements SeekerService {
	
	@Autowired
	JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	ExaminationRepository examinationRepository;

	@Override
	public DataPageWrapper<List<JobSeeker>> getSeekerList(Long companyId,Long examId, Integer pageIndex, Integer pageSize) {
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
		
		Page<JobSeeker> page = null ;
		if (examId != null) {
			page = jobSeekerRepository.findByCompanyIdAndExaminationId(companyId, examId, pageable);
		} else {
			page = jobSeekerRepository.findByCompanyId(companyId, pageable);
		}
		DataPageWrapper<List<JobSeeker>> dataPageWrapper = new DataPageWrapper<List<JobSeeker>>();
		dataPageWrapper.setData(page.getContent());
		dataPageWrapper.setTotalNumber((int)page.getTotalElements());
		dataPageWrapper.setPageIndex(pageIndex);
		dataPageWrapper.setTotalPage(page.getTotalPages());
		dataPageWrapper.setPageSize(pageSize);
		return dataPageWrapper;
	}

	@Override
	@Transactional
	public DataWrapper<List<String>> addSeeker(Long companyId, Long examId, String[] labels) {
		if (companyId == null || examId == null || labels == null || labels.length == 0) {
			throw new ParameterException("参数错误");
		}
		for(int i = 0 ; i < labels.length ; i++) {
			if (labels[i] == null || labels[i].trim().equals("")) {
				throw new ParameterException("参数错误");
			} else {
				labels[i] = labels[i].trim();
			}
		}
		
		Optional<Examination> examination = examinationRepository.findById(examId);
		List<JobSeeker> seekers;
		DataWrapper<List<String>> dataWrapper;
		List<String> links;
		if (examination.isPresent() && examination.get().getCompanyId().equals(companyId)) {
			seekers = new ArrayList<>();
			links = new ArrayList<String>();
			for(String label: labels) {
				JobSeeker jobSeeker = new JobSeeker();
				jobSeeker.setId(null);
				jobSeeker.setAccessId(UUIDGenerator.getCode());
				jobSeeker.setScore(0);
				jobSeeker.setFinishedDate(null);
				jobSeeker.setChoice(null);
				jobSeeker.setExaminationId(examination.get().getId());
				jobSeeker.setCompanyId(examination.get().getCompanyId());
				jobSeeker.setLabel(label);
				seekers.add(jobSeeker);
				links.add(jobSeeker.getLabel() + ":" + jobSeeker.getAccessId() + "\n");
			}
			jobSeekerRepository.saveAll(seekers);
			
			dataWrapper = new DataWrapper<>();
			dataWrapper.setData(links);
		} else {
			throw new ParameterException("考卷不存在");
		}
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteSeeker(Long seekerId, Long companyId) {
		DataWrapper<Void> dataWrapper;
		Optional<JobSeeker> seeker = jobSeekerRepository.findById(seekerId);
		if (seeker.isPresent()) {
			if (seeker.get().getCompanyId().equals(companyId)) {
				try {
					jobSeekerRepository.delete(seeker.get());
				} catch (Exception e) {
					throw new DBException("数据库错误", e);
				}
			} else {
				throw new AuthException("权限错误");
			}
		} else {
			throw new ParameterException("考生不存在");
		}
		dataWrapper = new DataWrapper<>();
		return dataWrapper;
	}

	@Override
	public DataWrapper<String> getByAccessId(String accessId) {
		Optional<JobSeeker> seeker = jobSeekerRepository.findByAccessId(accessId);
		if (!seeker.isPresent() || seeker.get().getFinishedDate() != null ) {
			throw new ParameterException("考生不存在");
		}
		
		Optional<Examination> examination = examinationRepository.findById(seeker.get().getExaminationId());
		
		String content = null;
		if (examination.isPresent()) {
			try {
				JSONArray questions = new JSONArray(examination.get().getContent());
				for(int i = 0 ; i < questions.length(); i++) {
					JSONObject question = questions.getJSONObject(i);
					question.remove("right_choice");
				}
				content = questions.toString();
			} catch (Exception e) {
			}
			
		} else {
			throw new ParameterException("考卷不存在");
		}
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setData(content);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> finish(String accessId, Map<String, String> answer) {
		Optional<JobSeeker> seeker = jobSeekerRepository.findByAccessId(accessId);
		if (!seeker.isPresent() || seeker.get().getFinishedDate() != null ) {
			throw new ParameterException("考生不存在");
		}
		
		Optional<Examination> examination = examinationRepository.findById(seeker.get().getExaminationId());
		
		
		
		if (examination.isPresent()) {
			int score = 0;
			try {
				JSONObject choices = new JSONObject();
				JSONArray questions = new JSONArray(examination.get().getContent());
				for(int i = 0 ; i < questions.length(); i++) {
					JSONObject question = questions.getJSONObject(i);
					String choice = answer.get(question.get("uuid"));;
					if (question.get("right_choice") != null && question.get("right_choice").equals(choice)) {
						score += question.getInt("score");
					}
					choices.put(question.getString("uuid"), choice);
				}
				seeker.get().setChoice(choices.toString());
				seeker.get().setFinishedDate(new Date());
				seeker.get().setScore(score);
				
			} catch (Exception e) {
				throw new ParameterException("位置错误");
			}
			
			try {
				jobSeekerRepository.save(seeker.get());
			} catch (Exception e) {
				throw new DBException("数据库错误", e);
			}
			
			
		} else {
			throw new ParameterException("考卷不存在");
		}
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		return dataWrapper;
	}

}
