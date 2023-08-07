package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.services.FileService;

@RestController
public class FileController {	

	@Autowired
	private FileService fileService;

	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ResponseEntity<String> generateUrl(@RequestParam(value = "file",required = true) MultipartFile file) throws IOException{
		
		return ResponseEntity.ok(fileService.saveFile(file));
	}

}