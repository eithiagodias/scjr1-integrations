package br.com.fiap.agrodrones.microservice;

import br.com.fiap.agrodrones.microservice.dtos.DroneInfoDTO;
import br.com.fiap.agrodrones.microservice.managers.DronesManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgroDronesMicroserviceApplication {

	public static void main(String[] args) {
		DroneInfoDTO dOne = new DroneInfoDTO();
		dOne.setId_drone(1);
		dOne.setLongitude(10.3);
		dOne.setLatitude(11.4);
		dOne.setUmidade(14);
		dOne.setTemperatura(36);
		dOne.setAtivar_rastreamento(true);
		DronesManager.getInstance().addDrone(dOne);

		DroneInfoDTO dTwo = new DroneInfoDTO();
		dTwo.setId_drone(2);
		dTwo.setLongitude(20.3);
		dTwo.setLatitude(40.5);
		dTwo.setTemperatura(20);
		dTwo.setUmidade(20);
		dTwo.setAtivar_rastreamento(false);
		DronesManager.getInstance().addDrone(dOne);

		SpringApplication.run(AgroDronesMicroserviceApplication.class, args);
	}

}
