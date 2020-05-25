package com.batch.partion;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
@EnableBatchProcessing
@ImportResource("classpath:batchjob.xml")
@SpringBootApplication
public class App7BatchPartitioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(App7BatchPartitioningApplication.class, args);
	}

}
