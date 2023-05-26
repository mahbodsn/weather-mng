package com.inpress.weather.service.weather.impl;

import com.inpress.weather.exception.BusinessException;
import com.inpress.weather.service.weather.WeatherSearchFactory;
import com.inpress.weather.service.weather.WeatherService;
import com.inpress.weather.service.weather.mapper.WeatherServiceMapper;
import com.inpress.weather.service.weather.model.WeatherSearchModel;
import com.inpress.weather.service.weather.model.WeatherSearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

	private final WeatherSearchFactory weatherSearchFactory;

	private final WeatherServiceMapper mapper;

	@Override
	public WeatherSearchResult search(WeatherSearchModel model) throws BusinessException {
		logger.debug("going to get weather with model -> {}", model);
		var searchStrategy = weatherSearchFactory.getSearchStrategy(model.searchType());
		logger.debug("selected strategy is -> {}", searchStrategy.getClass());
		return searchStrategy.search(model);
	}

}
