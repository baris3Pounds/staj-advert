package com.threepounds.advert.messaging;


import com.threepounds.advert.config.RabbitMQConfig;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQReceiver {

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void receiveMessage(UserMessageModel model) {
    log.info("Registered user id:{} , username {} ", model.getId(), model.getUsername());
  }

  @RabbitListener(queues = RabbitMQConfig.ADVERT_DELETED_QUEUE)
  public void receiveDeletedAdvertMessage(UUID adId) {
    System.out.println("Deleted advert ID: " + adId);
  }

}
