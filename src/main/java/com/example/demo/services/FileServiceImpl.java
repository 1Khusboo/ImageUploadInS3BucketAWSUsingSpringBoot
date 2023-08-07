package com.example.demo.services;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

@Service
public class FileServiceImpl implements FileService{
	
	@Value("${bucketName}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3;
	
	@Override
	public String saveFile(MultipartFile file) {
		String originalFilename=file.getOriginalFilename();
		System.out.println(originalFilename);
		try {
			File file1= convertMultiPartFileToFile(file);
			s3.putObject(bucketName,originalFilename,file1);
			
			Calendar calendar= Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MINUTE, 10);
			URL url=s3.generatePresignedUrl(bucketName,originalFilename, calendar.getTime(),HttpMethod.GET);
			
			return url.toString();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String deleteFile(String filename) {
		s3.deleteObject(bucketName,filename);
		
		return "File Deleted";
	}

	@Override
	public List<String> listAllFiles() {
		
		ListObjectsV2Result listObjectsV2Result=s3.listObjectsV2(bucketName);
		return listObjectsV2Result.getObjectSummaries().stream().map(e->e.getKey()).collect(Collectors.toList());
		
	
	}
	
	private File convertMultiPartFileToFile(MultipartFile file) throws IOException{
		File conFile= new File(file.getOriginalFilename());
		FileOutputStream fos= new FileOutputStream(conFile);
		fos.write(file.getBytes());
		fos.close();
		return conFile;
	}

}
