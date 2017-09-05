package com.mycom.myapp;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import com.mycom.myapp.dao.TranConfigDAO;

public class App {
	
	@Scheduled(fixedRate = 1000 * 60)
	public void ticker() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/ticker.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());	
		
		Job job = (Job) context.getBean("helloWorldJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tickerJob Done");

	}
	
	@Scheduled(fixedRate = 1000 * 60 * 5)
	public void tranJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/tranJob.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());	
		
		Job job = (Job) context.getBean("tranJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tranJob Done");
	}	
	
	@Scheduled(fixedRate = 1000 * 10)
	public void tranCoinJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		String[] springConfig = { "spring/batch/jobs/tranJob.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());	
		
		Job job = (Job) context.getBean("tranCoinJob");

		try {

			JobExecution execution = jobLauncher.run(job, builder.toJobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("tranCoinJob Done");
	}		
}