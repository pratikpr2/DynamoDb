package com.cg.dynamodb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.cg.dynamodb.repo")
public class DynamoDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String dbEndPoint;
	
	@Value("{amazon.aws.accesskey}")
	private String accessKey;
	
	@Value("{amazon.aws.secretkey}")
	private String secretKey;
	
	@Bean
	public AmazonDynamoDB amazonDynamodb() {
		AmazonDynamoDB dynamoDb = new AmazonDynamoDBClient();
		
		if(!StringUtils.isNullOrEmpty(dbEndPoint)) {
			dynamoDb.setEndpoint(dbEndPoint);
		}
		return dynamoDb;
	}
	
	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}
	
}
