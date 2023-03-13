package com.example.reward.domain.reward.repository;

import com.example.reward.domain.reward.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Long> {

}
