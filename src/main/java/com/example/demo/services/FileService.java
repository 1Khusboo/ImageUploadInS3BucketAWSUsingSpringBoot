package com.example.demo.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;


public interface FileService {
	
//	String generateUrl(String Filename,HttpMethod httpMethod);
	
	String deleteFile(String Filename);
	
	List<String> listAllFiles();

	String saveFile(MultipartFile file);
	
	
}

