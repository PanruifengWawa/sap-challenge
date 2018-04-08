package com.job.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.annotation.CheckLogin;
import com.job.model.view.CompanyView;
import com.job.service.CompanyService;
import com.job.util.DataWrapper;

@Controller
@RequestMapping(value="/api/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	
	/**
	* @api {post} api/company/login 公司登录(管理端)
	* @apiName company_login
	* @apiGroup company
	*
	* @apiParam {String} userName * 用户名（必须）
	* @apiParam {String} password * 密码（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": "SK49ce4215-dd9c-49c8-92d9-71bac76a7e6f",
  	*		"status": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"data": "用户名或密码错误",
	*  		"status": 1
	*	}
	**/
	@RequestMapping(value="login", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<String> login(
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "password",required = true) String password
            ) {
    	return companyService.login(userName, password);
    }

	
	/**
	* @api {get} api/company 获取公司信息(管理端)
	* @apiName company_info
	* @apiGroup company
	*
	* @apiHeader {String} token 身份凭证(商铺身份)
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": {
  	*			"name":"4+1公司",
  	*			"phone":null,
  	*			"address":null,
  	*			"description":null
  	*		},
  	*		"status": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"data": "错误",
	*  		"status": 1
	*	}
	**/
	@CheckLogin
	@RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<CompanyView> getCompanyInfo(
    		HttpServletRequest request
    		) {
    	return companyService.getCompanyInfo((Long)request.getAttribute("companyId"));
    }
	
	
	/**
	* @api {get} api/company 获取公司信息(管理端)
	* @apiName company_info
	* @apiGroup company
	*
	* @apiHeader {String} token 身份凭证(商铺身份)
	* 
	* @apiParam {String} name * 公司名（非必须）
	* @apiParam {String} phone * 公司电话（非必须）
	* @apiParam {String} address * 公司地址（非必须）
	* @apiParam {String} description * 公司简介（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": null,
  	*		"status": 0
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"data": "错误",
	*  		"status": 1
	*	}
	**/
	@CheckLogin
	@RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> updateCompanyInfo(
    		@ModelAttribute CompanyView companyView,
    		HttpServletRequest request
    		) {
    	return companyService.updateCompanyInfo((Long)request.getAttribute("companyId"), companyView);
    }

}
