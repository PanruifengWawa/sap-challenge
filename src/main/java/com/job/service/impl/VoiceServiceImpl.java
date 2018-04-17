package com.job.service.impl;

import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.job.service.VoiceService;
import com.job.util.DataWrapper;
import com.job.util.HttpRequestUtil;
import com.job.util.XFUtil;

@Service
public class VoiceServiceImpl implements VoiceService {
	
	@Autowired
	RedisTemplate<String, String>  redisTemplate;
	@Override
	public DataWrapper<String> upload(MultipartFile file) {
		String voice = XFUtil.recognize(file);
		String voiceContent = "";
		String result = null;

		try {
			JSONObject voiceJson = new JSONObject(voice);
			voiceContent = voiceJson.getJSONObject("data").getString("result");
			voiceContent = voiceContent.replaceAll("\\pP","");
			String sentence = URLEncoder.encode(voiceContent, "utf-8");
			String predict = getModelResult(sentence);
			System.out.println(predict);
			JSONObject predictJson = new JSONObject(predict);
			
			result = predictJson.getString("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataWrapper<String> dataWrapper = new DataWrapper<>();
		dataWrapper.setData(result);
		return dataWrapper;
	}
	
	public String getModelResult(String sentence) {
		String result = redisTemplate.opsForValue().get(sentence);
		if (result == null) {
			result =  HttpRequestUtil.sendGet("http://192.168.43.121:8000/predict?sentence=" + sentence );
//			result =  "{\"status\":0,\"result\":\"WE\"}";
			redisTemplate.opsForValue().set(sentence, result);
		} 

		return result;
	}
}
