package com.example.reward.support.rabbitmq;

import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class SendController {

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routing}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	@PostMapping("/publish")
	public void publish(@RequestBody RewardCreateRequest request) {
		rabbitTemplate.convertAndSend(exchange, routingKey, request);
	}
}
