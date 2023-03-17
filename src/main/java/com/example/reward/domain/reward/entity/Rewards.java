package com.example.reward.domain.reward.entity;

import static javax.persistence.FetchType.LAZY;

import com.example.reward.domain.member.entity.Member;
import com.example.reward.support.RewardPolicyConstants;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "rewards")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rewards {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private long point;

	@Column
	@CreatedDate
	private LocalDate rewardDate;

	private Rewards(Long id, Member member, String title, String description, long point) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.description = description;
		this.point = point;
	}

	public static Rewards ofDefault(Member member, long streakRewardCount) {

		return new Rewards(null, member, RewardPolicyConstants.REWARD_TITLE, RewardPolicyConstants.REWARD_DESCRIPTION,
				getPoint(streakRewardCount));
	}

	public static long getPoint(long streakRewardCount) {
		switch (Math.toIntExact(streakRewardCount)) {
			case 2:
				return RewardPolicyConstants.THREE_DAYS_POINT;
			case 4:
				return RewardPolicyConstants.FIVE_DAYS_POINT;
			case 9:
				return RewardPolicyConstants.TEN_DAYS_POINT;
			default:
				return RewardPolicyConstants.DEFAULT_POINT;
		}

	}
}
