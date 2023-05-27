package com.inpress.weather.service.weather.impl;

import com.inpress.weather.api.spec.WeatherSearchType;
import com.inpress.weather.client.weather.VisualCrossingClient;
import com.inpress.weather.exception.BusinessException;
import com.inpress.weather.service.metric.MetricService;
import com.inpress.weather.service.metric.model.MetricCounterModel;
import com.inpress.weather.service.metric.model.Metrics;
import com.inpress.weather.service.weather.WeatherSearchStrategy;
import com.inpress.weather.service.weather.mapper.WeatherServiceMapper;
import com.inpress.weather.service.weather.model.WeatherSearchModel;
import com.inpress.weather.service.weather.model.WeatherSearchResult;
import com.inpress.weather.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherSearchByCityStrategy implements WeatherSearchStrategy {

	private final VisualCrossingClient client;

	private final WeatherServiceMapper mapper;

	private final MetricService metricService;

	@Override
	public WeatherSearchResult search(WeatherSearchModel model) throws BusinessException {
		logger.debug("going to search weather with -> {}", this.getSearchType());
		var startDate = DateUtil.epochToLocalDate(model.timeCriteria().from());
		var endDate = DateUtil.epochToLocalDate(model.timeCriteria().to());
		logger.debug("search start date -> [{}] and end date -> [{}], coordinate -> [{}]", startDate, endDate,
				model.cityName());
		var response = client.getWeatherByCityName(model.cityName(), startDate.format(DateTimeFormatter.ISO_DATE),
				endDate.format(DateTimeFormatter.ISO_DATE));
		logger.debug("weather provider search response is -> {}", response);
		metricService.sendCounterMetric(new MetricCounterModel(Metrics.WEATHER_SEARCH_CITY.getKey(), 1, Map.of("type", WeatherSearchType.CITY.name())));
		return this.mapper.toWeatherSearchResult(response);
	}

	@Override
	public WeatherSearchType getSearchType() {
		return WeatherSearchType.CITY;
	}
}
