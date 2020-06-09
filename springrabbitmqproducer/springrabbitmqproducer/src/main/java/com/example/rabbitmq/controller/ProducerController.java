package com.example.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.producer.Producer;

@RestController
public class ProducerController {

	@Autowired
	Producer producer;
	
	@RequestMapping("/send")
	public String sendMessage(@RequestParam("message")String message) {
		producer.produceMessage(message);
		return "Sent!";
	}
}
