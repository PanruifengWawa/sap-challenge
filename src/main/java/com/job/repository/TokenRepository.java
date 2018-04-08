package com.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	Token findByTokenStr(String tokenStr);
	Token findByCompanyId(Long companyId);

}
