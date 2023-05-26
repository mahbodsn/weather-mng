package com.inpress.weather.service.weather.model;

public record WeatherHourlyConditionResult(Long date, Integer temp, Integer feelsLike, Integer humidity,
										   String conditions) {
}
