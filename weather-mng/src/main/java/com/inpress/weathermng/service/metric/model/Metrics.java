package com.inpress.weathermng.service.metric.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Metrics {

	WEATHER_SEARCH_CITY("weather.search"),
	VISUAL_CROSSING_ERROR("visual.crossing.error");

	private final String key;

}
