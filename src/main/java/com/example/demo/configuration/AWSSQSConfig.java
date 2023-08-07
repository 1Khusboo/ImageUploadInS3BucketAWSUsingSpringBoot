package com.example.demo.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class AWSSQSConfig {
	
	@Value("${secret}")
	private String secretKey;
	
	@Value("${accessKey}")
	private String accessKey;
	
	@Value("${regionSQS}")
	private String region;
	
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
	@Bean
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(region).withCredentials
				(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey))).build();
	}
	
	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
		SimpleMessageListenerContainerFactory factory= new SimpleMessageListenerContainerFactory();
		factory.setAmazonSqs(amazonSQSAsync());
		return factory;
		
	}
	@Bean
	public QueueMessageHandlerFactory queueMessageHandlerFactory() {
	   QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
	   MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
	   messageConverter.setStrictContentTypeMatch(false);
	   factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
	   return factory;
	}
	
//	@Bean
//	public QueueMessageHandler queueMessageHandler() {
//		QueueMessageHandlerFactory factory= new QueueMessageHandlerFactory();
//		factory.setAmazonSqs(amazonSQSAsync());
//		QueueMessageHandler handler= factory.createQueueMessageHandler();
//		List<HandlerMethodArgumentResolver> list= new ArrayList<>();
//		HandlerMethodArgumentResolver resolver= new PayloadArgumentResolver(new MappingJackson2MessageConverter());
//		list.add(resolver);
//		handler.setArgumentResolvers(list);
//		return handler;
//		
//	}

}
