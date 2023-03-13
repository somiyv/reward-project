package com.example.reward.config;

import com.example.reward.support.ApiException;
import com.example.reward.support.ErrorCode;
import com.example.reward.support.ErrorResponse;
import java.time.format.DateTimeParseException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleBaseException(ApiException apiException) {
		ErrorResponse errorResponse = ErrorResponse.of(apiException);
		return ResponseEntity.status(apiException.getHttpStatus()).body(errorResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAMETER);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class, BindException.class})
	public ResponseEntity<ErrorResponse> handleBadRequest(Exception exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExceptions(HttpServletRequest req,
			Exception exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
		return ResponseEntity.internalServerError().body(errorResponse);
	}
}
