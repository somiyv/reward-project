package com.example.reward.domain.reward.controller;

import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import com.example.reward.domain.reward.dto.RewardDTO;
import com.example.reward.domain.reward.enums.SortType;
import com.example.reward.domain.reward.service.RewardsService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// 보상 데이터 리스트 조회
	@GetMapping()
	public ResponseEntity<List<RewardDTO>> getRewards(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createDate,
			@RequestParam(defaultValue = "asc") SortType sort) {
		return ResponseEntity.ok(rewardsService.getRewards(createDate, sort));
	}

	@PostMapping()
	public ResponseEntity<RewardDTO> createRewards(
			@RequestBody RewardCreateRequest request) {
		return ResponseEntity.ok(rewardsService.createRewards(request));
	}
}
