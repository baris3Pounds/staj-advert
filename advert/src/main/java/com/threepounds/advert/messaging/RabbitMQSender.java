package com.threepounds.advert.messaging;

import com.threepounds.advert.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQSender<T> {
    private final RabbitTemplate rabbitTemplate;

  public void send(T t) {
    rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, t);
  }
}
