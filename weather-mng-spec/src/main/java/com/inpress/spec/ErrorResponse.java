package com.inpress.spec;

import java.time.LocalDateTime;

public record ErrorResponse(String title, String message, Integer code, LocalDateTime timestamp) {

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode.name(), errorCode.getMessage(), errorCode.getCode(), LocalDateTime.now());
	}

}
