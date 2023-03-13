package com.example.reward.config;

import com.example.reward.support.ApiException;
import com.example.reward.support.ErrorCode;
import com.example.reward.support.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleBaseException(ApiException apiException) {
		ErrorResponse errorResponse = ErrorResponse.of(apiException);
		return ResponseEntity.status(apiException.getHttpStatus()).body(errorResponse);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExceptions(HttpServletRequest req,
			Exception exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		return ResponseEntity.internalServerError().body(errorResponse);
	}
}
