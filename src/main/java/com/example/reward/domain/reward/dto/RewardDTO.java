package com.example.reward.domain.reward.dto;

import com.example.reward.domain.member.dto.MemberDTO;
import com.example.reward.domain.reward.entity.Rewards;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RewardDTO {

	private Long id;
	private MemberDTO member;
	private String title;
	private String description;
	private int point;
	private LocalDateTime createDate;

	public static RewardDTO of(Rewards rewards) {
		RewardDTO instance = new RewardDTO();
		instance.id = rewards.getId();
		instance.title = rewards.getTitle();
		instance.description = rewards.getDescription();
		instance.createDate = LocalDateTime.now();
		instance.point = rewards.getPoint();
		instance.member = MemberDTO.of(rewards.getMember());
		return instance;
	}
}
