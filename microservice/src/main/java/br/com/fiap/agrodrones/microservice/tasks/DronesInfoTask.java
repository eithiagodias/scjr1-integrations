package br.com.fiap.agrodrones.microservice.tasks;

import br.com.fiap.agrodrones.microservice.api.Api;
import br.com.fiap.agrodrones.microservice.api.Response;
import br.com.fiap.agrodrones.microservice.dtos.DroneInfoDTO;
import br.com.fiap.agrodrones.microservice.managers.DronesManager;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DronesInfoTask implements Tasklet {

  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    System.out.println("DronesInfoTask start..");
    DroneInfoDTO drone = DronesManager.getInstance().random();
    Response res = Api.send(drone);
    System.out.println("Send Drone Info:");
    System.out.println(drone.toString());
    System.out.println("Response:");
    System.out.println(res.toString());
    return RepeatStatus.FINISHED;
  }

}