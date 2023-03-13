package com.example.reward.domain.reward.repository.impl;

import static com.example.reward.domain.member.entity.QMember.member;
import static com.example.reward.domain.reward.entity.QRewards.rewards;

import com.example.reward.domain.reward.entity.Rewards;
import com.example.reward.domain.reward.repository.RewardsRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RewardsRepositoryCustomImpl extends QuerydslRepositorySupport
		implements RewardsRepositoryCustom {

	public RewardsRepositoryCustomImpl() {
		super(Rewards.class);
	}

	private JPAQueryFactory factory;

	@PostConstruct
	public void postConstruct() {
		this.factory = new JPAQueryFactory(getEntityManager());
	}

	@Override
	public Optional<Rewards> findByIdWithMember(Long id) {
		return Optional.ofNullable(from(rewards)
				.join(rewards.member, member).fetchJoin()
				.where(rewards.id.eq(id))
				.fetchOne());
	}
}
