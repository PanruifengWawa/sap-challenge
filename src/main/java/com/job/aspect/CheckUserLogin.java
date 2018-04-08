package com.job.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.job.exceptions.AuthException;
import com.job.model.Token;
import com.job.repository.TokenRepository;



@Aspect
@Component
public class CheckUserLogin {
	
	@Autowired
	TokenRepository tokenRepository;
  
	 
	 @Before("@annotation(com.job.annotation.CheckLogin) && (args(request,..) || args(..,request))") 
	 public void beforeExec(JoinPoint joinPoint,HttpServletRequest request) {		 
		 String tokenStr = request.getHeader("token");
		 Token token = tokenRepository.findByTokenStr(tokenStr) ;
		 if (token != null) {
			 request.setAttribute("companyId", token.getCompanyId());
			 
		 } else {
			 
			 throw new AuthException("用户未登录");
		 }
		
		 
	 }
	
//	 @After("@annotation(com.demo.annotation.CheckUser) && (args(request,..) || args(..,request))")  
//	 public void afterExec(JoinPoint joinPoint,HttpServletRequest request){  
//		 //这里可以记录日志
//		 MethodSignature ms = (MethodSignature) joinPoint.getSignature();  
//		 Method method = ms.getMethod();  
//		 System.out.println("标记为"+tag.get()+"的方法"+method.getName()+"运行消耗"+(System.currentTimeMillis()-time.get())+"ms");  
//	 }
	 


}

