package com.inpress.weather.api.spec.response;

public record WeatherHourlyConditionDto(Long date, Integer temp, Integer feelsLike, Integer humidity,
										String conditions) {
}
