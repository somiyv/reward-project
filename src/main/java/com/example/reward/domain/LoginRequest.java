package com.example.reward.domain;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {

	@NotNull
	String loginId;
	@NotNull
	String password;

}
