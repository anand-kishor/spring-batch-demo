package com.spring.batch.parallel.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SimpleStep implements Tasklet {

	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		workload();
		System.out.println("done");
		return RepeatStatus.FINISHED;
	}

	private void workload() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(5000);
		
	}

}
