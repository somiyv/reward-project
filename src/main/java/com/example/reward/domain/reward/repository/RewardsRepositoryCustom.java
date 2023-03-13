package com.example.reward.domain.reward.repository;

import com.example.reward.domain.reward.entity.Rewards;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepositoryCustom  {

	Optional<Rewards> findByIdWithMember(Long id);
}
