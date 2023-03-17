package com.example.reward.domain.reward.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatedRewardEvent {
	private String memberKey;
}
