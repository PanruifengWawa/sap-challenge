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
import com.job.model.Examination;
import com.job.service.ExamService;
import com.job.util.DataPageWrapper;
import com.job.util.DataWrapper;

@Controller
@RequestMapping(value="/api/exam")
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	
	
	/**
	* @api {get} api/exam 获取考卷列表(管理端)
	* @apiName exam_list
	* @apiGroup exam
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Integer} pageSize * 分页（必须）
	* @apiParam {Integer} pageIndex * 分页（必须）
	*
	* @apiSuccessExample {json} Success-Response:
	* 	HTTP/1.1 200 ok
	* 	{
  	*		"data": [
    *			{
    *				"id":1,"name":"阿达",
    *				"content":"[{\"uuid\":\"123\",\"name\":\"请问xxx\",\"choice\":{\"A\":\"选择1\",\"B\":\"选择2\",\"C\":\"选择3\",\"D\":\"选择4\"},\"right_choice\":\"A\"}}]",
    *				"jobId":4,
    *				"jobPostion":"IT",
    *				"companyId":1
    *			}
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
    public DataPageWrapper<List<Examination>> getExamList(
    		@RequestParam(name = "pageSize", required = false) Integer pageSize,
    		@RequestParam(name = "pageIndex", required = false) Integer pageIndex,
    		HttpServletRequest request
    		) {
    	return examService.getExamList((Long)request.getAttribute("companyId"), pageIndex, pageSize);
    }
	
	
	/**
	* @api {post} api/exam 添加考卷(管理端)
	* @apiName exam_add
	* @apiGroup exam
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {String} name * 考卷名（必须）
	* @apiParam {String} content * 考卷内容（必须）
	* @apiParam {Long} jobId * 职位id（必须）
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
	@RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> addExam(
    		@ModelAttribute Examination examination,
    		HttpServletRequest request
    		) {
		examination.setCompanyId((Long)request.getAttribute("companyId"));
    	return examService.addExam(examination);
    }
	
	
	/**
	* @api {delete} api/exam/{examId} 删除考卷(管理端)
	* @apiName exam_delete
	* @apiGroup exam
	*
	* @apiHeader {String} token 身份凭证
	* 
	* @apiParam {Long} examId * 考卷id（必须）
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
	@RequestMapping(value="{examId}", method = RequestMethod.DELETE)
    @ResponseBody
    public DataWrapper<Void> deleteExam(
    		@PathVariable Long examId,
    		HttpServletRequest request
    		) {
    	return examService.deleteExam(examId, (Long)request.getAttribute("companyId"));
    }

}
