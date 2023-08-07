package com.example.demo;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
//@EnableDiscoveryClient
public class ImageUploadRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploadRestApiApplication.class, args);
	}

}
