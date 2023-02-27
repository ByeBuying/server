package com.buying.back.application.quartz.job;

import com.buying.back.application.quartz.service.BatchService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class CheckAccountBirthDayJob implements Job {

  @Autowired
  private BatchService batchService;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    batchService.batchCheckAccountBirthDay();
  }
}
