package br.com.fiap.agrodrones.consumer.consumer;

import br.com.fiap.agrodrones.consumer.dtos.DroneInfoDTO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class LogManager {

    private static final Logger terminalLog = LoggerFactory.getLogger(QueueConsumer.class);
    private static final  java.util.logging.Logger logger = java.util.logging.Logger.getLogger("DroneAlerts");
    public static void write(String fileBody) {
        DroneInfoDTO drone = (new Gson()).fromJson(fileBody, DroneInfoDTO.class);

        Boolean temperaturaErrada = drone.getTemperatura() <= 0 || drone.getTemperatura() >= 35;
        Boolean umidadeErrada = drone.getUmidade() <= 15;
        if(temperaturaErrada || umidadeErrada) {
            terminalLog.info("\nALERTA TEMPERATURA/UMIDADE" + drone.toString());
            FileHandler fh;
            try {
                fh = new FileHandler("drone-alerts.log", true);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.info(drone.toString());
                fh.close();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            terminalLog.info("\nINFO DRONE: " + drone.toString());
        }
    }
}
