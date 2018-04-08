package com.job.service;

import com.job.model.view.CompanyView;
import com.job.util.DataWrapper;

public interface CompanyService {
	DataWrapper<String> login(String userName, String password);
	
	DataWrapper<CompanyView> getCompanyInfo(Long companyId);
	
	DataWrapper<Void> updateCompanyInfo(Long companyId, CompanyView companyView);

}
