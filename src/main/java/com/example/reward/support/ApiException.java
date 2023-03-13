package com.example.reward.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

	private HttpStatus httpStatus;
	private String code;

	protected ApiException(HttpStatus httpStatus, String code, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
	}

	public static ApiException of(ErrorCode errorCode) {
		return new ApiException(errorCode.getHttpStatus(), errorCode.toString(),
				errorCode.getMessage());
	}
}
