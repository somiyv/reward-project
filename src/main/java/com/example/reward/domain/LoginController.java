package com.example.reward.domain;

import com.example.reward.domain.member.dto.MemberDTO;
import com.example.reward.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

	private final MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<MemberDTO> memberLogin(
			@RequestBody LoginRequest request) {

		return ResponseEntity.ok(memberService.login(request));
	}

	@PatchMapping("/logout")
	public ResponseEntity<HttpStatus> memberLogin(@RequestParam Long memberId) {
		memberService.logout(memberId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
