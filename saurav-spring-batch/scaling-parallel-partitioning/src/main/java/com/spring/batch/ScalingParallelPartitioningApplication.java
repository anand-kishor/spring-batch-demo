package com.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
@EnableBatchProcessing
@ImportResource("classpath:batchjoba.xml")
@SpringBootApplication
@ComponentScan("com.spring.batch")
public class ScalingParallelPartitioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScalingParallelPartitioningApplication.class, args);
	}

}
