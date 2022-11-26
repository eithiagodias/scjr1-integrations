package br.com.fiap.agrodrones.microservice;

import br.com.fiap.agrodrones.microservice.dtos.DroneInfoDTO;
import br.com.fiap.agrodrones.microservice.managers.DronesManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgroDronesMicroserviceApplication {

	public static void main(String[] args) {
		DroneInfoDTO dOne = new DroneInfoDTO();
		dOne.setId_drone(123456);
		dOne.setAtivar_rastreamento(true);
		dOne.setLatitude(123456);
		dOne.setLatitude(654321);
		DronesManager.getInstance().addDrone(dOne);

		DroneInfoDTO dTwo = new DroneInfoDTO();
		dTwo.setId_drone(456123);
		dTwo.setAtivar_rastreamento(true);
		dTwo.setLatitude(321654);
		dTwo.setLatitude(654321);
		DronesManager.getInstance().addDrone(dOne);

		SpringApplication.run(AgroDronesMicroserviceApplication.class, args);
	}

}
