package com.example.reward.domain.reward.controller.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RewardCreateRequest {

	@NotNull
	Long memberId;
	Long rewardDays;

}
