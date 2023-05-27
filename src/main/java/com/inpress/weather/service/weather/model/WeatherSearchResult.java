package com.inpress.weather.service.weather.model;

import java.util.List;

public record WeatherSearchResult(String timezone, String address, Long latitude, String longitude,
								  List<WeatherDailyConditionResult> days,
								  WeatherHourlyConditionResult currentConditions) {
}
