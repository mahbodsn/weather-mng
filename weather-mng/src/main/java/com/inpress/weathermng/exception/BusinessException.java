package com.inpress.weathermng.exception;

import com.inpress.spec.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class BusinessException extends Exception {

	private final ErrorCode errorCode;

}
