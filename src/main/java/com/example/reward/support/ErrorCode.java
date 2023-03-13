package com.example.reward.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청 형식이 잘못됐습니다"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버오류 발생");

	private HttpStatus httpStatus;
	private String message;


	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}


}
