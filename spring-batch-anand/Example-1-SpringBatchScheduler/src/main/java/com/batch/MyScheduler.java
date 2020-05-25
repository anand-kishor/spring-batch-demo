package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@EnableScheduling
public class MyScheduler {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;
	 @Scheduled(cron="*/10* * * * *")
	public void myScheduler()
	{
		JobParameters jobParameters= new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
		try {
			JobExecution jobExection=jobLauncher.run(job, jobParameters);
			System.out.println("............"+jobExection);
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
