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


    public static void processDroneInfo(DroneInfoDTO dto) {
        // Colocar as validações de latitude e longitude
        // Colocar as validações de temperatura e umidade

//        double latitude = dto.getLatitude();
//         if (latitude is valid) {
//            dto.setLatitude(dto.getLatitude());
//        }

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

        Gson gson = new Gson();
        String json = gson.toJson(dto);
        System.out.println(json);
        template.convertAndSend(exchange, dto.getId_drone().toString(), json);
    }
}
