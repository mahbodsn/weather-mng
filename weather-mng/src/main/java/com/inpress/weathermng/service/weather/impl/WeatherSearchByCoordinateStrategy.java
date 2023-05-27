package com.inpress.weathermng.service.weather.impl;

import com.inpress.spec.WeatherSearchType;
import com.inpress.weathermng.client.weather.VisualCrossingClient;
import com.inpress.weathermng.exception.BusinessException;
import com.inpress.weathermng.service.metric.MetricService;
import com.inpress.weathermng.service.metric.model.MetricCounterModel;
import com.inpress.weathermng.service.metric.model.Metrics;
import com.inpress.weathermng.service.weather.WeatherSearchStrategy;
import com.inpress.weathermng.service.weather.mapper.WeatherServiceMapper;
import com.inpress.weathermng.service.weather.model.WeatherSearchModel;
import com.inpress.weathermng.service.weather.model.WeatherSearchResult;
import com.inpress.weathermng.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherSearchByCoordinateStrategy implements WeatherSearchStrategy {

	private final VisualCrossingClient client;

	private final WeatherServiceMapper mapper;

	private final MetricService metricService;

	@Cacheable(value = "weatherSearchByCoordinate", key = "#model.searchType().name() + #model.coordinate().latitude() + " +
			"#model.coordinate().longitude() + #model.timeCriteria().from() + #model.timeCriteria().to()")
	@Override
	public WeatherSearchResult search(WeatherSearchModel model) throws BusinessException {
		logger.debug("going to search weather with -> {}", this.getSearchType());
		var startDate = DateUtil.epochToLocalDate(model.timeCriteria().from());
		var endDate = DateUtil.epochToLocalDate(model.timeCriteria().to());
		logger.debug("search start date -> [{}] and end date -> [{}], coordinate -> [{}]", startDate, endDate,
				model.coordinate());
		var response = this.client.getWeatherByCoordinates(model.coordinate().latitude(), model.coordinate().longitude(),
				startDate.format(DateTimeFormatter.ISO_DATE), endDate.format(DateTimeFormatter.ISO_DATE));
		logger.debug("weather provider search response is -> {}", response);
		metricService.sendCounterMetric(new MetricCounterModel(Metrics.WEATHER_SEARCH_CITY.getKey(), 1, Map.of("type", getSearchType().name())));
		return this.mapper.toWeatherSearchResult(response);
	}

	@Override
	public WeatherSearchType getSearchType() {
		return WeatherSearchType.COORDINATE;
	}

}
