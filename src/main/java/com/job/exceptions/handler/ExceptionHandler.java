package com.job.exceptions.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.exceptions.AuthException;
import com.job.exceptions.DBException;
import com.job.exceptions.ParameterException;
import com.job.util.DataWrapper;



@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(RuntimeException ex) {
		ex.printStackTrace();
		return getReturns(1, ex.getMessage());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(AuthException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(AuthException ex) {  
		return getReturns(1, ex.getMessage());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DBException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(DBException ex) {  
		ex.printStackTrace();
		
		return getReturns(1,ex.getMessage());
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ParameterException.class)
	@ResponseBody
    public DataWrapper<String> exceptionProcess(ParameterException ex) {  
		
		return getReturns(1,ex.getMessage());
    }
	
	public DataWrapper<String> getReturns(int code , String message) {
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		dataWrapper.setStatus(code);
		dataWrapper.setData(message);
        return dataWrapper;
	}

}
