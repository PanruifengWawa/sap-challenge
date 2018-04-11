package com.job.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.annotation.CheckLogin;
import com.job.model.Job;
import com.job.service.JobService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

@Controller
@RequestMapping(value="/api/job")
public class JobController {
	
	@Autowired
	JobService jobService;
	
	
	/**
	* @api {post} api/job 添加职位(管理端)
	* @apiName job_add
	* @apiGroup job
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {String} position * 职位名（必须）
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
    public DataWrapper<Void> addJob(
    		@ModelAttribute Job job,
    		HttpServletRequest request
    		) {
		job.setCompanyId((Long)request.getAttribute("companyId"));
    	return jobService.addJob(job);
    }
	
	
	/**
	* @api {get} api/job 获取职位列表(管理端)
	* @apiName job_list
	* @apiGroup job
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Integer} pageSize * 分页（非必须）
	* @apiParam {Integer} pageIndex * 分页（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": [
    *			{
    *				"id": 1,
    *				"position": "IT",
    *				"description": "程序员",
    *				"companyId": 1
    *			},
    *		],
  	*		"status": 0,
  	*		"pageSize": 2,
    *		"pageIndex": 1,
    *		"totalNumber": 2,
    *		"totalPage": 1
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
    public DataPageWrapper<List<Job>> getJobList(
    		@RequestParam(name = "pageSize", required = false) Integer pageSize,
    		@RequestParam(name = "pageIndex", required = false) Integer pageIndex,
    		HttpServletRequest request
    		) {
    	return jobService.getJobList((Long)request.getAttribute("companyId"), pageIndex, pageSize);
    }
	
	/**
	* @api {get} api/job/company/{companyId} 获取职位列表(手机端)
	* @apiName job_list_company_id
	* @apiGroup job
	*
	* @apiParam {Long} companyId * 公司id（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": [
    *			{
    *				"id": 1,
    *				"position": "IT",
    *				"description": "程序员",
    *				"companyId": 1
    *			},
    *		],
  	*		"status": 0,
  	*		"pageSize": 2,
    *		"pageIndex": 1,
    *		"totalNumber": 2,
    *		"totalPage": 1
	*	}
	*
	* @apiSuccessExample {json} Error-Response:
	* 	HTTP/1.1 200 ok
	* 	{
	*  		"data": "错误",
	*  		"status": 1
	*	}
	**/

	@RequestMapping(value = "company/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public DataPageWrapper<List<Job>> getJobListForPhone(@PathVariable Long companyId) {
		return jobService.getJobList(companyId, null, null);
	}
	
	
	
	/**
	* @api {delete} api/job/{jobId} 删除职位(管理端端)
	* @apiName job_delete
	* @apiGroup job
	* 
	* @apiHeader {String} token 身份凭证
	* @apiParam {Long} jobId * 职位id（必须）
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
	@RequestMapping(value = "{jobId}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Void> deleteJob(
			@PathVariable Long jobId,
			HttpServletRequest request
			) {
		return jobService.deleteJob(jobId, (Long)request.getAttribute("companyId"));
	}

}
