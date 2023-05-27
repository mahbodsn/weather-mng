package com.inpress.weather.service.metric.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Metrics {

	WEATHER_SEARCH_CITY("weather.search");

	private final String key;

}
