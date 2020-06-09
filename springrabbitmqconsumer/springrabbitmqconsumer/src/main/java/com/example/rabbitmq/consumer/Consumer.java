package com.example.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = "${example.rabbitmq.queue}")
	public void recieveMessage(String message) {
		System.out.println("Recieved message: " + message);
	}
}
