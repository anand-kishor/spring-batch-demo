package com.batch.listener;

import javax.batch.runtime.BatchStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class JobNotificationsListener implements JobExecutionListener {
	private static final Logger log=LoggerFactory.getLogger(JobNotificationsListener.class);
	private final  JdbcTemplate jdbcTemplate;
	@Autowired
	public JobNotificationsListener(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		 log.info("Intercepting Job Excution - Before Job!");
	}
	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		 log.info("Intercepting Job Excution - After Job!");
	}


}
