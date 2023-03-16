package com.example.reward.domain.reward.service;

import com.example.reward.domain.reward.controller.request.RewardCreateRequest;
import com.example.reward.domain.reward.dto.RewardDTO;
import com.example.reward.domain.reward.enums.SortType;
import java.time.LocalDate;
import java.util.List;

public interface RewardsService {

	RewardDTO getRewardById(Long id);
	List<RewardDTO> getRewards(LocalDate createDate, SortType sortType);
	RewardDTO createRewards(RewardCreateRequest request);

}
