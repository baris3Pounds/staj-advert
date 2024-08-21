package com.threepounds.advert.messaging;

import com.threepounds.advert.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    public void send(String username){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME , username);
    }
}
