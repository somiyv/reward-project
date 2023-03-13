package com.example.reward.domain.reward.controller;

import com.example.reward.domain.reward.dto.RewardDTO;
import com.example.reward.domain.reward.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardsController {

	private final RewardsService rewardsService;

	// 보상 데이터 상세 조회
	@GetMapping("{id}")
	public ResponseEntity<RewardDTO> getRewardById(@PathVariable Long id) {
		return ResponseEntity.ok(rewardsService.getRewardById(id));
	}
}
