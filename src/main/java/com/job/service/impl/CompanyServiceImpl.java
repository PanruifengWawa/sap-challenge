package com.job.service.impl;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.exceptions.AuthException;
import com.job.exceptions.DBException;
import com.job.exceptions.ParameterException;
import com.job.model.Company;
import com.job.model.Token;
import com.job.model.view.CompanyView;
import com.job.repository.CompanyRepository;
import com.job.repository.TokenRepository;
import com.job.service.CompanyService;
import com.job.util.DataWrapper;
import com.job.util.MD5Util;
import com.job.util.Parameters;
import com.job.util.UUIDGenerator;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	TokenRepository tokenRepository;

	@Override
	public DataWrapper<String> login(String userName, String password) {
		DataWrapper<String> dataWrapper;
		Company company;
		Token token;
		
		company = companyRepository.findByUserName(userName);
		if (company == null) {
			throw new AuthException("用户名或密码错误");
		}
		
		if (company.getPassword().equals(MD5Util.getMD5String(Parameters.salt + MD5Util.getMD5String(password)))) {
			token = tokenRepository.findByCompanyId(company.getId());
			if (token == null) {
				token = new Token();
				token.setId(null);
				token.setCompanyId(company.getId());
			}
			token.setLoginDate(new Date());
			token.setTokenStr(UUIDGenerator.getCode());
			try {
				tokenRepository.save(token);
			} catch (Exception e) {
				throw new DBException("数据库错误", e);
			}
			dataWrapper = new DataWrapper<>();
			dataWrapper.setData(token.getTokenStr());
		} else {
			throw new AuthException("用户名或密码错误");
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<CompanyView> getCompanyInfo(Long companyId) {
		Optional<Company> company;
		CompanyView companyView;
		DataWrapper<CompanyView> dataWrapper = null;
		try {
			company = companyRepository.findById(companyId);
			if (company.isPresent()) {
				companyView = new CompanyView();
				companyView.setAddress(company.get().getAddress());
				companyView.setDescription(company.get().getDescription());
				companyView.setName(company.get().getName());
				companyView.setPhone(company.get().getPhone());
				dataWrapper = new DataWrapper<>();
				dataWrapper.setData(companyView);
			} else {
				throw new AuthException("用户不存在");
			}
		} catch (Exception e) {
			throw new DBException("数据库错误", e);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateCompanyInfo(Long companyId, CompanyView companyView) {
		Optional<Company> company;
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (companyView == null) {
			throw new ParameterException("用户名或密码错误");
		}
		try {
			company = companyRepository.findById(companyId);
			if (company.isPresent()) {
				if (companyView.getName() != null && !companyView.getName().equals("")) {
					company.get().setName(companyView.getName());
				}
				if (companyView.getAddress() != null && !companyView.getAddress().equals("")) {
					company.get().setAddress(companyView.getAddress());
				}
				if (companyView.getPhone() != null && !companyView.getPhone().equals("")) {
					company.get().setPhone(companyView.getPhone());
				}
				if (companyView.getDescription() != null && !companyView.getDescription().equals("")) {
					company.get().setDescription(companyView.getDescription());
				}
				companyRepository.save(company.get());
			} else {
				throw new AuthException("用户不存在");
			}
		} catch (Exception e) {
			throw new DBException("数据库错误", e);
		}
		return dataWrapper;
	}

}
