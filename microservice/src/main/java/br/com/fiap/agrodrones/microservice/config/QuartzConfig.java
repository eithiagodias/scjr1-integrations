package br.com.fiap.agrodrones.microservice.config;
import br.com.fiap.agrodrones.microservice.jobs.CustomQuartzJob;
import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzConfig
{
  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private JobLocator jobLocator;

  @Bean
  public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    return jobRegistryBeanPostProcessor;
  }


  @Bean
  public JobDetail droneInfoJobDetail() {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("jobName", "droneInfoJob");
    jobDataMap.put("jobLauncher", jobLauncher);
    jobDataMap.put("jobLocator", jobLocator);

    return JobBuilder.newJob(CustomQuartzJob.class)
        .withIdentity("droneInfoJob")
        .setJobData(jobDataMap)
        .storeDurably()
        .build();
  }

  @Bean
  public Trigger droneInfoJobTrigger()
  {
    SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
        .simpleSchedule()
        .withIntervalInSeconds(10)
        .repeatForever();

    return TriggerBuilder
        .newTrigger()
        .forJob(droneInfoJobDetail())
        .withIdentity("droneInfoJobTrigger")
        .withSchedule(scheduleBuilder)
        .build();
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() throws IOException
  {
    SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
    scheduler.setTriggers(droneInfoJobTrigger());
    scheduler.setQuartzProperties(quartzProperties());
    scheduler.setJobDetails(droneInfoJobDetail());
    return scheduler;
  }

  @Bean
  public Properties quartzProperties() throws IOException
  {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }
}
