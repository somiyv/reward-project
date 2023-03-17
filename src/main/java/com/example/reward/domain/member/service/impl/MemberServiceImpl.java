package com.example.reward.domain.member.service.impl;

import com.example.reward.domain.LoginRequest;
import com.example.reward.domain.LogoutRequest;
import com.example.reward.domain.member.dto.MemberDTO;
import com.example.reward.domain.member.entity.Member;
import com.example.reward.domain.member.repository.MemberRepository;
import com.example.reward.domain.member.service.MemberService;
import com.example.reward.support.ApiException;
import com.example.reward.support.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public MemberDTO login(LoginRequest request) {
		Member member = memberRepository.findByLoginId(request.getLoginId())
				.orElseThrow(() -> ApiException.of(ErrorCode.AUTHENTICATION_FAIL));

		if (!member.getPassword().equals(request.getPassword())) {
			throw ApiException.of(ErrorCode.AUTHENTICATION_FAIL);
		}

		member.login();
		return MemberDTO.of(member);
	}

	@Override
	public void logout(LogoutRequest request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> ApiException.of(ErrorCode.MEMBER_NOT_FOUND));
		member.logout();
	}
}
