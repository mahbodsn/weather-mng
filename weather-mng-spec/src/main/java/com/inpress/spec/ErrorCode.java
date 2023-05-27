package com.inpress.spec;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Getter
public enum ErrorCode {

	UNKNOWN_ERROR(1000, "error-code.unknown"),

	INVALID_PARAMETER(2000, "error-code.invalid-parameter"),

	INVALID_LOCATION(3000, "error-code.invalid-location"),
	PROVIDER_RATE_LIMIT_EXCEPTION(3001, "error-code.provider-rate-limit"),
	PROVIDER_UNKNOWN_ERROR(3002, "error-code.provider-unknown-error");

	private final Integer code;

	private final String message;

	ErrorCode(Integer code, String message) {
		this.code = code;
		try {
			ClassLoader classLoader = ErrorCode.class.getClassLoader();
			InputStream resourceAsStream = classLoader.getResourceAsStream("labels.properties");
			BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
			var properties = new Properties();
			properties.load(reader);
			this.message = (String) properties.get(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
