package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootH2DbWebAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootH2DbWebAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2DbWebAppApplication.class, args);
		logger.debug("application started.");
	}
}
