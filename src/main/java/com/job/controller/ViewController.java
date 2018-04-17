package com.job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="")
public class ViewController {
	@RequestMapping(value="")
    public String indexPage() {
        return "index.html";
    }
	
	@RequestMapping(value="startExam")
    public String startExamPage() {
        return "writtenTestStart.html";
    }
	
	@RequestMapping(value="exam")
    public String examPage() {
        return "writtenTest.html";
    }

}
