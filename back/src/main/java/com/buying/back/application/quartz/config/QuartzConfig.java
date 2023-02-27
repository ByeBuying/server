package com.buying.back.application.quartz.config;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import com.buying.back.application.quartz.job.CheckAccountBirthDayJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

  @Bean
  public JobDetail checkAccountBirthDayDetail() {
    return JobBuilder.newJob().ofType(CheckAccountBirthDayJob.class)
      .storeDurably()
      .withIdentity("Check_Account_Birth_Day_Detail")
      .withDescription("Check_Account_Birth_Day_Detail")
      .build();
  }

  @Bean
  public Trigger checkAccountBirthDayTrigger(JobDetail checkAccountBirthDayDetail) {
    return TriggerBuilder.newTrigger().forJob(checkAccountBirthDayDetail)
      .withIdentity("Check_Account_Birth_Day_Trigger")
      .withDescription("Check_Account_Birth_Day_Trigger")
      .withSchedule(cronSchedule("0 0 4 * * ?")) // 매일 오전 4시
      .build();
  }

}
