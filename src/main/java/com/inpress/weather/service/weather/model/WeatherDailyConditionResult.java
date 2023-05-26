package com.inpress.weather.service.weather.model;

import com.inpress.weather.client.weather.spec.VisualCrossingHourlyWeatherDto;

import java.util.List;

public record WeatherDailyConditionResult(Long date, Integer maxTemp, Integer minTemp, Integer temp, Integer maxFeelsLike,
		Integer minFeelsLike, Integer feelsLike, String conditions, String description,
										  List<WeatherHourlyConditionResult> hours) {
}
