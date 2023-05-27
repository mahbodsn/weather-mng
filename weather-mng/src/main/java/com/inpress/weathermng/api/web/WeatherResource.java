package com.inpress.weathermng.api.web;

import com.inpress.spec.request.WeatherSearchRequest;
import com.inpress.spec.response.WeatherSearchResponse;
import com.inpress.weathermng.api.web.mapper.WeatherResourceMapper;
import com.inpress.weathermng.api.web.validator.WeatherSearchValidator;
import com.inpress.weathermng.exception.BusinessException;
import com.inpress.weathermng.service.weather.WeatherService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class WeatherResource {

	private final WeatherService service;

	private final WeatherResourceMapper mapper;

	private final WeatherSearchValidator searchValidator;

	@InitBinder("weatherSearchRequest")
	public void weatherSearchValidator(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(searchValidator);
	}

	@PostMapping(path = "/weather/search", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "weather.search.api", description = "weather search api response time")
	public ResponseEntity<WeatherSearchResponse> getCurrentWeather(@Valid @RequestBody WeatherSearchRequest request)
			throws BusinessException {
		logger.info("received weather search request with body -> {}", request);
		var result = service.search(mapper.toWeatherSearchModel(request));
		logger.debug("search result is -> {}", result);
		return ResponseEntity.ok(mapper.toWeatherSearchResponse(result));
	}

}
