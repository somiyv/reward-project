package com.example.reward.domain.member.service;

import com.example.reward.domain.LoginRequest;
import com.example.reward.domain.LogoutRequest;
import com.example.reward.domain.member.dto.MemberDTO;

public interface MemberService {

	MemberDTO login(LoginRequest request);
	void logout(LogoutRequest request);
}
