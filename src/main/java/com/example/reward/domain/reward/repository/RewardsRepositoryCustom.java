package com.example.reward.domain.reward.repository;

import com.example.reward.domain.reward.entity.Rewards;
import com.example.reward.domain.reward.enums.SortType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepositoryCustom  {

	Optional<Rewards> findByIdWithMember(Long id);
	List<Rewards> findAllByCreateDateWithMember(LocalDate createDate, SortType sortType);
}
