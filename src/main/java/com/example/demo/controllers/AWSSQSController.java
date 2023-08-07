package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSSQSController {
	
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Value("${sqsUrl}")
	private String queueUrl;
	
	
	@GetMapping("/send/{message}")
	public void sendMessageToQueue(@PathVariable String message) {
		queueMessagingTemplate.send(queueUrl,MessageBuilder.withPayload(message).build());
	}
	
	

}
