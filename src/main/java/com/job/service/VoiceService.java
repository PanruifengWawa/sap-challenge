package com.job.service;

import org.springframework.web.multipart.MultipartFile;

import com.job.util.DataWrapper;

public interface VoiceService {
	DataWrapper<String> upload(MultipartFile file);
}
