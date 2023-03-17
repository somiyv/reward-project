package com.example.reward.support.redis.listner;

import com.example.reward.domain.reward.event.CreatedRewardEvent;
import com.example.reward.support.RewardPolicyConstants;
import com.example.reward.support.redis.RedisService;
import com.example.reward.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisEventListener {

	private final RedisService redisService;

	@TransactionalEventListener
	public void handleCreateRewardEvent(CreatedRewardEvent event) {
		String member = event.getMemberKey();
		// reward 키 카운트 up
		redisService.increment(RewardPolicyConstants.TODAY_REWARDS_KEY);
		// memeber 키 set or 마지막 날짜 add
		redisService.push(member, DateUtils.todayString());
	}

}
