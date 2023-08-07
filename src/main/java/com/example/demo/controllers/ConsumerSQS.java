package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerSQS {
	
	Logger logger=LoggerFactory.getLogger(ConsumerSQS.class);
	
	@SqsListener(value="Khus-Queue",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void loadMessageFromSQS(String message) {
		System.out.println("Message Received: " + message);
		logger.info("message From SQS Queue {}",message);
	}

}
