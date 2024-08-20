package com.threepounds.advert.messaging;


import com.threepounds.advert.config.RabbitMQConfig;
import java.util.UUID;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void receiveMessage(String username) {
    System.out.println("Registered user : " + username);
    // Process the message here
  }

}
