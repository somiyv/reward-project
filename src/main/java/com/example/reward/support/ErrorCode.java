package com.example.reward.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청 형식이 잘못됐습니다"),
	MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "필수 정보가 존재하지 않습니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버오류 발생"),

	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "요청 메소드가 허용되지 않습니다."),

	REWARDS_NOT_FOUND(HttpStatus.NOT_FOUND, "보상데이터가 존재하지 않습니다."),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버가 존재하지 않습니다."),

	REWARD_EVENT_HAS_ENDED(HttpStatus.CONFLICT, "보상 이벤트 지급이 종료되었습니다."),
	ALREADY_HAS_REWARDED(HttpStatus.CONFLICT, "오늘 이미 보상 지급을 받으셨습니다.");


	private HttpStatus httpStatus;
	private String message;


	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}


}
