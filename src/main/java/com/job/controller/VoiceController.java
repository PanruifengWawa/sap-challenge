package com.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.job.service.VoiceService;
import com.job.util.DataWrapper;

@Controller
@RequestMapping(value="/api/voice")
public class VoiceController {
	
	@Autowired
	VoiceService voiceService;
	
	@RequestMapping(value="upload", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<String> getSeekerList(
    		@RequestParam(value="data", required=true) MultipartFile file
    		) {
    	return voiceService.upload(file);
    }

}
