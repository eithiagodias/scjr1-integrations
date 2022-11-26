package br.com.fiap.agrodrones.microservice.config;

import br.com.fiap.agrodrones.microservice.tasks.DronesInfoTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  private JobBuilderFactory jobs;

  @Autowired
  private StepBuilderFactory steps;

  @Bean
  public Step droneInfoTask(){
    return steps.get("droneInfoTask")
        .tasklet(new DronesInfoTask())
        .build();
  }


  @Bean(name="droneInfoJob")
  public Job demoJobOne(){
    return jobs.get("droneInfoJob")
        .start(droneInfoTask())
        .build();
  }
}
