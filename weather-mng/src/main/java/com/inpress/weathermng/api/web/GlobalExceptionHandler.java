package com.inpress.weathermng.api.web;

import com.inpress.spec.ErrorCode;
import com.inpress.spec.ErrorResponse;
import com.inpress.spec.InvalidParameterErrorResponse;
import com.inpress.weathermng.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status,
																  WebRequest request) {
		logger.error("validation exception {}", ex.getBindingResult(), ex);
		var errors = ex.getBindingResult().getAllErrors()
				.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
		return ResponseEntity.badRequest().body(InvalidParameterErrorResponse.of(errors, ErrorCode.INVALID_PARAMETER));
	}


	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
		logger.error("received business exception -> " + ex);
		return ResponseEntity.unprocessableEntity().body(ErrorResponse.of(ex.getErrorCode()));
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleUnknownException(RuntimeException e, WebRequest request) {
		logger.error("received exception -> " + e);
		return ResponseEntity.unprocessableEntity().body(ErrorResponse.of(ErrorCode.UNKNOWN_ERROR));
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ErrorResponse> handleUnknownRuntimeException(RuntimeException e, WebRequest request) {
		logger.error("received runtime exception -> " + e);
		return ResponseEntity.unprocessableEntity().body(ErrorResponse.of(ErrorCode.UNKNOWN_ERROR));
	}

}
