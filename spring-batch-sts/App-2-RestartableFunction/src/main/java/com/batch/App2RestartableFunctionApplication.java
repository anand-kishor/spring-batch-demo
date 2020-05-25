package com.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableBatchProcessing
@ImportResource({"classpath:batchjob.xml"})
public class App2RestartableFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(App2RestartableFunctionApplication.class, args);
	}

}
