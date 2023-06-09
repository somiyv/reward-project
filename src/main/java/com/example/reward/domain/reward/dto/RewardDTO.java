package com.example.reward.domain.reward.dto;

import com.example.reward.domain.member.dto.MemberDTO;
import com.example.reward.domain.reward.entity.Rewards;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RewardDTO {

	private Long id;
	private MemberDTO member;
	private String title;
	private String description;
	private long point;
	private LocalDate rewardDate;

	public static RewardDTO of(Rewards rewards) {
		RewardDTO instance = new RewardDTO();
		instance.id = rewards.getId();
		instance.title = rewards.getTitle();
		instance.description = rewards.getDescription();
		instance.rewardDate = rewards.getRewardDate();
		instance.point = rewards.getPoint();
		instance.member = MemberDTO.of(rewards.getMember());
		return instance;
	}
}
