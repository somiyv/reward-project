package com.example.reward.domain.reward.service.impl;

import com.example.reward.domain.member.entity.Member;
import com.example.reward.domain.member.repository.MemberRepository;
import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import com.example.reward.domain.reward.dto.RewardDTO;
import com.example.reward.domain.reward.entity.Rewards;
import com.example.reward.domain.reward.enums.SortType;
import com.example.reward.domain.reward.event.CreatedRewardEvent;
import com.example.reward.domain.reward.repository.RewardsRepository;
import com.example.reward.domain.reward.service.RewardsService;
import com.example.reward.support.ApiException;
import com.example.reward.support.RewardPolicyConstants;
import com.example.reward.support.ErrorCode;
import com.example.reward.support.redis.RedisService;
import com.example.reward.utils.DateUtils;
import com.google.common.base.Joiner;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardsService {

	private final RewardsRepository rewardsRepository;
	private final MemberRepository memberRepository;
	private final RedisService redisService;
	private final ApplicationEventPublisher publish;



	@Override
	public RewardDTO getRewardById(Long id) {
		Rewards rewards = rewardsRepository.findByIdWithMember(id)
				.orElseThrow(() -> ApiException.of(ErrorCode.REWARDS_NOT_FOUND));
		return RewardDTO.of(rewards);
	}

	@Override
	public List<RewardDTO> getRewards(LocalDate createDate, SortType sortType) {
		return rewardsRepository.findAllByCreateDateWithMember(createDate, sortType)
				.stream().map(RewardDTO::of).collect(Collectors.toList());
	}

	@Override
	public RewardDTO createRewards(RewardCreateRequest request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> ApiException.of(ErrorCode.MEMBER_NOT_FOUND));
		try {
			Rewards rewards = rewardsRepository.save(
					Rewards.ofDefault(member, 3));
			publish.publishEvent(
					new CreatedRewardEvent(member.getRewardDaysKey()));
			return RewardDTO.of(rewards);
		} catch (DataIntegrityViolationException e) {
			// 같은날짜에 이미 보상받은 reward가 있다면 유니크 제약으로 throw
			throw ApiException.of(ErrorCode.ALREADY_HAS_REWARDED);
		}
	}

	@Override
	public RewardCreateRequest validation(Long memberId) {
		// 유효하지 않은 ID 체크를 위해 추가
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> ApiException.of(ErrorCode.MEMBER_NOT_FOUND));

		// 10명이 찼는가?
		if (Objects.equals(redisService.get(RewardPolicyConstants.TODAY_REWARDS_KEY, Integer.class),
				RewardPolicyConstants.MAXIMUM_REWARDS_DAYS)) {
			throw ApiException.of(ErrorCode.REWARD_EVENT_HAS_ENDED);
		}

		// 보상받을 멤버의 키, value : ['2023-03-15', '2023-03-16'..]
		String memberKey = member.getRewardDaysKey();
		// 멤버가 마지막에 보상받은 날짜
		LocalDate lastDate = DateUtils.toLocalDateString(redisService.getLastIndex(memberKey));
		// 현재 멤버가 보상받은 일수
		long rewardDays = redisService.size(memberKey);
		// 가장 마지막에 발급받은 날짜가 오늘인가?
		if (lastDate != null && lastDate.equals(DateUtils.today())) {
			throw ApiException.of(ErrorCode.ALREADY_HAS_REWARDED);
		}

		// 2. 마지막날짜가 어제가 아니면 연속이 아니니까 리셋시키기
		if (!DateUtils.isYesterday(lastDate)
				|| rewardDays >= RewardPolicyConstants.MAXIMUM_REWARDS_DAYS) {
			redisService.remove(memberKey);
		}
		return new RewardCreateRequest(memberId, rewardDays);
	}
}
