package com.example.reward.domain.reward.controller.request;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class RewardCreateRequest {

	@NotNull
	Long memberId;

}
