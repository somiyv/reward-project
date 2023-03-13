package com.example.reward.support;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private BaseApiError error;

	private ErrorResponse(BaseApiError error) {
		this.error = error;
	}

	public static ErrorResponse of(ApiException apiException) {
		BaseApiError error = BaseApiError.of(apiException.getCode(), apiException.getMessage());
		return new ErrorResponse(error);
	}


	public static ErrorResponse of(ErrorCode apiErrorCode) {
		BaseApiError error = BaseApiError.of(apiErrorCode.toString(), apiErrorCode.getMessage());

		return new ErrorResponse(error);
	}

	@Getter
	public static class BaseApiError {

		protected String code;
		protected String message;

		private BaseApiError(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public static BaseApiError of(String code, String message) {
			return new BaseApiError(code, message);
		}
	}
}
