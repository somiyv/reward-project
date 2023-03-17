package com.example.reward.domain.member.entity;

import com.google.common.base.Joiner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "member")
public class Member {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String loginId;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private boolean isLogin;

	public String getRewardDaysKey() {
		return Joiner.on(":").join("reward", "member", getId());
	}

	public void login() {
		this.isLogin = true;
	}

	public void logout() {
		this.isLogin = false;
	}
}
