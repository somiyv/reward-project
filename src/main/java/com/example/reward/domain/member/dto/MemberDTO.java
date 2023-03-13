package com.example.reward.domain.member.dto;

import com.example.reward.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberDTO {

	private Long id;
	private String loginId;
	private String name;

	public static MemberDTO of(Member member) {
		MemberDTO instance = new MemberDTO();
		instance.id = member.getId();
		instance.name = member.getName();
		instance.loginId = member.getLoginId();
		return instance;
	}
}
