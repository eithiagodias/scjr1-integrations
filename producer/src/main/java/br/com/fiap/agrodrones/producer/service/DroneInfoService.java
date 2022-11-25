package br.com.fiap.agrodrones.producer.service;

import br.com.fiap.agrodrones.producer.config.Config;
import br.com.fiap.agrodrones.producer.dto.DroneInfoDTO;
import com.google.gson.Gson;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class DroneInfoService {

    public static void processDroneInfo(DroneInfoDTO dto) throws Exception {
        // Colocar as validações de latitude e longitude
        // Colocar as validações de temperatura e umidade

        double latitude = dto.getLatitude();
        double longitude = dto.getLongitude();

        int temperatura = dto.getTemperatura();
        int umidade = dto.getUmidade();

         if (latitude < -90 || latitude > 90) {
             throw new Exception("Latitude deve ser entre -90 e 90 graus.");
        }

         if (longitude < -180 || longitude > 180) {
             throw new Exception("Longitude deve ser entre -180 e 180 graus.");
         }

         if (temperatura < -25 || temperatura > 40) {
             throw new Exception("Temperatura deve ser um inteiro entre -25 e 40.");
         }

         if (umidade < 0 || umidade > 100) {
             throw new Exception("Umidade deve ser um inteiro entre 0 e 100.");
         }

        // Colocar informações na fila
        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(Config.getConnection());
        Queue queueDrones = new Queue("drones");

        final String exchange = "exchange.drones";

        admin.declareQueue(queueDrones);

        DirectExchange exchangeDrones = new DirectExchange(exchange);
        admin.declareExchange(exchangeDrones);

        admin.declareBinding(BindingBuilder.bind(queueDrones).to(exchangeDrones).with(dto.getId_drone().toString()));

        RabbitTemplate template = new RabbitTemplate(Config.getConnection());

        // transforma em JSON
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        System.out.println(json);
        template.convertAndSend(exchange, dto.getId_drone().toString(), json);
    }
}
