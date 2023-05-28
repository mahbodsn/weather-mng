package com.inpress.weathermng.service.weather.model;

import java.io.Serializable;
import java.util.List;

public record WeatherSearchResult(String timezone, String address, Long latitude, String longitude,
								  List<WeatherDailyConditionResult> days,
								  WeatherHourlyConditionResult currentConditions) implements Serializable {
}
