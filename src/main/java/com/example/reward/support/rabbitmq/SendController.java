package com.example.reward.support.rabbitmq;

import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import com.example.reward.domain.reward.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private final RewardsService rewardsService;

	@PostMapping("/publish")
	public ResponseEntity<HttpStatus> publish(@RequestBody RewardCreateRequest request) {
		RewardCreateRequest createRequest = rewardsService.validation(request.getMemberId());
		rabbitTemplate.convertAndSend(exchange, routingKey, createRequest);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
