package com.inpress.weathermng.service.weather.model;

import java.io.Serializable;

public record WeatherHourlyConditionResult(Long date, Integer temp, Integer feelsLike, Integer humidity,
										   String conditions) implements Serializable {
}
