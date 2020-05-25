package com.javasampleapproach.batch.parallelstep;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath:batchjob.xml")
public class SpringBatchParallelStepApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchParallelStepApplication.class, args);
	}
}