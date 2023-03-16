package com.example.reward.support.rabbitmq;

import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import com.example.reward.domain.reward.service.RewardsService;
import com.example.reward.utils.GsonHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReceiver {

	private final RewardsService rewardsService;

	@RabbitListener(queues = "${rabbitmq.queue}")
	public void receiveMessage(RewardCreateRequest message) {
		log.info("Received message: {}", GsonHelper.toJson(message));
		rewardsService.createRewards(message);
	}

}
