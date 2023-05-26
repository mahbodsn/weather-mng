package com.inpress.weather.api.web;

import com.inpress.weather.api.spec.request.WeatherSearchRequest;
import com.inpress.weather.api.spec.response.WeatherSearchResponse;
import com.inpress.weather.api.web.mapper.WeatherResourceMapper;
import com.inpress.weather.exception.BusinessException;
import com.inpress.weather.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class WeatherResource {

	private final WeatherService service;

	private final WeatherResourceMapper mapper;

	@PostMapping(path = "/weather/search", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WeatherSearchResponse> getCurrentWeather(@Valid @RequestBody WeatherSearchRequest request)
			throws BusinessException {
		logger.info("received weather search request with body -> {}", request);
		service.search(mapper.toWeatherSearchModel(request));
		return ResponseEntity.ok(new WeatherSearchResponse());
	}

}
