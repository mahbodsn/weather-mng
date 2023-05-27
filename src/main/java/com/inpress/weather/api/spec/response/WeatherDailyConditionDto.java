package com.inpress.weather.api.spec.response;

import java.util.List;

public record WeatherDailyConditionDto(Long date, Integer maxTemp, Integer minTemp, Integer temp,
									   Integer maxFeelsLike,
									   Integer minFeelsLike, Integer feelsLike, String conditions,
									   String description,
									   List<WeatherHourlyConditionDto> hours) {
}
