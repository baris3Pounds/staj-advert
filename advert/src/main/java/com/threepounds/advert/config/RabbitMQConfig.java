package com.threepounds.advert.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String QUEUE_NAME = "q-user-registration";
  public static final String EXCHANGE_NAME = "user-registration-exchange";
  public static final String ROUTING_KEY = "example-routing-key";

  public static final String ADVERT_CREATED_QUEUE = "q-advert-created";
  public static final String ADVERT_DELETED_QUEUE = "q-advert-deleted";


  @Bean
  public Queue queueUser() {
    return new Queue(QUEUE_NAME, true);
  }

  @Bean
  public Queue queueAdvert() {
    return new Queue(ADVERT_CREATED_QUEUE, true);
  }

  @Bean
  public Queue queueAdvertDeleted() {
    return new Queue(ADVERT_DELETED_QUEUE, true);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(EXCHANGE_NAME);
  }

  @Bean
  public Binding binding(@Qualifier("queueUser") Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }

  @Bean
  public Binding bindingAdvertCreated(@Qualifier("queueAdvert") Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }

  @Bean
  public Binding bindingAdvertDeleted(@Qualifier("queueAdvertDeleted") Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }

  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }


}
