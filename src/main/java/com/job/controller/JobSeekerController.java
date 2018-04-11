package com.job.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.job.annotation.CheckLogin;
import com.job.model.JobSeeker;
import com.job.service.SeekerService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

@Controller
@RequestMapping(value="/api/seeker")
public class JobSeekerController {
	@Autowired
	SeekerService seekerService;
	
	/**
	* @api {get} api/seeker 获取求职者列表(管理端)
	* @apiName seeker_list
	* @apiGroup seeker
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Integer} pageSize * 分页（非必须）
	* @apiParam {Integer} pageIndex * 分页（非必须）
	* @apiParam {Long} examId * 考卷（非必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": [
    *			{
    *				"id": 1,
    *				"label": "a",
    *				"score": "程序员",
    *				"finishedDate": 1
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
    public DataPageWrapper<List<JobSeeker>> getSeekerList(
    		@RequestParam(name = "pageSize", required = false) Integer pageSize,
    		@RequestParam(name = "pageIndex", required = false) Integer pageIndex,
    		@RequestParam(name = "examId", required = false) Long examId,
    		HttpServletRequest request
    		) {
    	return seekerService.getSeekerList((Long)request.getAttribute("companyId"), examId, pageIndex, pageSize);
    }
	
	
	/**
	* @api {post} api/seeker 添加求职者列表(管理端)
	* @apiName seeker_add
	* @apiGroup seeker
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Integer} examId * 考卷id（必须）
	* @apiParam {String[]} labels * 考号（必须）
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
    public DataWrapper<List<String>> addSeeker(
    		@RequestParam(name = "examId", required = true) Long examId,
    		@RequestParam(name = "labels", required = true) String[] labels,
    		HttpServletRequest request
    		) {
    	return seekerService.addSeeker((Long)request.getAttribute("companyId"), examId, labels);
    }
	
	/**
	* @api {delete} api/seeker/{seekerId} 删除考生(管理端)
	* @apiName exam_delete
	* @apiGroup exam
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Long} seekerId * 考生id（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data":null
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
	@RequestMapping(value="{seekerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public DataWrapper<Void> deleteSeeker(
    		@PathVariable Long seekerId,
    		HttpServletRequest request
    		) {
    	return seekerService.deleteSeeker(seekerId, (Long)request.getAttribute("companyId"));
	}
	
	/**
	* @api {get} api/seeker/accessId/{accessId} 获取考卷内容(手机端)
	* @apiName seeker_accessId
	* @apiGroup seeker
	*
	* 
	* @apiParam {Long} accessId * 考生链接（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data":"[{\"uuid\":\"123\",\"name\":\"请问xxx\",\"choice\":{\"A\":\"选择1\",\"B\":\"选择2\",\"C\":\"选择3\",\"D\":\"选择4\"},\"right_choice\":\"A\"}}]",
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
	@RequestMapping(value="accessId/{accessId}", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<String> getByAccessId(
    		@PathVariable String accessId,
    		HttpServletRequest request
    		) {
    	return seekerService.getByAccessId(accessId);
	}
	
	
	/**
	* @api {post} api/seeker/accessId/{accessId}/finish 完成考卷内容(手机端)
	* @apiName seeker_finish
	* @apiGroup seeker
	*
	* 
	* @apiParam {Long} accessId * 考生链接（必须）
	* @apiParamExample {json} Request-Example:
	* {
	* 	"uuidxxx" : "A"
	* }
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data":null,
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
	@RequestMapping(value="accessId/{accessId}/finish", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> finish(
    		@PathVariable String accessId,
    		@RequestBody Map<String, String> answer,
    		HttpServletRequest request
    		) {
    	return seekerService.finish(accessId, answer);
	}

}
