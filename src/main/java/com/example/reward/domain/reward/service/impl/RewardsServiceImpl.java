package com.example.reward.domain.reward.service.impl;

import com.example.reward.domain.member.entity.Member;
import com.example.reward.domain.member.repository.MemberRepository;
import com.example.reward.domain.reward.dto.RewardDTO;
import com.example.reward.domain.reward.entity.Rewards;
import com.example.reward.domain.reward.repository.RewardsRepository;
import com.example.reward.domain.reward.service.RewardsService;
import com.example.reward.support.ApiException;
import com.example.reward.support.ErrorCode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardsService {

	private final RewardsRepository rewardsRepository;

	@Override
	public RewardDTO getRewardById(Long id) {
		Rewards rewards = rewardsRepository.findByIdWithMember(id)
				.orElseThrow(() -> ApiException.of(ErrorCode.BAD_REQUEST));
		return RewardDTO.of(rewards);
	}

	@Override
	public List<RewardDTO> getRewards(LocalDate createDate, SortType sortType) {
		return rewardsRepository.findAllByCreateDateWithMember(createDate, sortType)
				.stream().map(RewardDTO::of).collect(Collectors.toList());
	}
}
