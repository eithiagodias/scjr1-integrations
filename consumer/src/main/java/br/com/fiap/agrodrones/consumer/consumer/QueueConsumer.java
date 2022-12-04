package br.com.fiap.agrodrones.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.drones}"})
    public void receiveMessage(@Payload String fileBody) {
        LogManager.write(fileBody);
    }
}
