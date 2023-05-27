package com.inpress.spec;

import java.time.LocalDateTime;
import java.util.List;

public record InvalidParameterErrorResponse(List<String> errors, String title, String message, Integer code,
											LocalDateTime timestamp) {

	public static InvalidParameterErrorResponse of(List<String> errors, ErrorCode errorCode) {
		return new InvalidParameterErrorResponse(errors, errorCode.name(), errorCode.getMessage(), errorCode.getCode(),
				LocalDateTime.now());
	}

}
