package com.spring.batch.parallel;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
@ComponentScan("com.spring.batch")
@SpringBootApplication
@EnableBatchProcessing
@ImportResource("classpath:batchjob.xml")
public class ScalingParallelStepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalingParallelStepsApplication.class, args);
	}

}
