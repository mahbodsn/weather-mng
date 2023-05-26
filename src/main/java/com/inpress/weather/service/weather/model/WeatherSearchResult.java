package com.inpress.weather.service.weather.model;

import com.inpress.weather.client.weather.spec.VisualCrossingDailyWeatherDto;
import com.inpress.weather.client.weather.spec.VisualCrossingHourlyWeatherDto;

import java.util.List;

public record WeatherSearchResult(String timezone, String address, Long latitude, String longitude,
								  List<WeatherDailyConditionResult> days,
								  VisualCrossingHourlyWeatherDto currentConditions) {
}
