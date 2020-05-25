package com.spring.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;
	
	@RequestMapping("/launchJob")
	public String handle()
	{
		Logger logger=LoggerFactory.getLogger(this.getClass());
		JobParameters jobParametrs=new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
		try {
			jobLauncher.run(job, jobParametrs);
		} 
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return "DONE";
	}
}
