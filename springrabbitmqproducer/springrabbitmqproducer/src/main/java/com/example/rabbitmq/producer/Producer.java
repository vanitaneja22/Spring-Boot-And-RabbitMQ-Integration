package com.example.rabbitmq.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${example.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${example.rabbitmq.routingkey}")
	private String routingKey;
	
	@Value("${example.rabbitmq.queue}")
	private String queueName;
		
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean 
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	public void produceMessage(String message) {
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
		System.out.println("Send message: "+ message);
	}

}