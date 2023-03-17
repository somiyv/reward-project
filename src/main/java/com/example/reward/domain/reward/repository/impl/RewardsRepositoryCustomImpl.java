package com.example.reward.domain.reward.repository.impl;

import static com.example.reward.domain.member.entity.QMember.member;
import static com.example.reward.domain.reward.entity.QRewards.rewards;

import com.example.reward.domain.reward.entity.Rewards;
import com.example.reward.domain.reward.enums.SortType;
import com.example.reward.domain.reward.repository.RewardsRepositoryCustom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

	@Override
	public List<Rewards> findAllByCreateDateWithMember(LocalDate createDate, SortType sortType) {
		Order direction = getDirection(sortType);
		OrderSpecifier<?> order = new OrderSpecifier<>(direction, rewards.id);

		return from(rewards)
				.join(rewards.member, member).fetchJoin()
				.where(rewards.rewardDate.eq(createDate))
				.orderBy(order)
				.fetch();
	}

	private Order getDirection(SortType sortType) {
		return sortType.isAsc() ? Order.ASC : Order.DESC;
	}
}
