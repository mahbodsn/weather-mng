package com.inpress.weather.api.spec.response;

import java.util.List;

public record WeatherSearchResponse(String timezone, String address, Long latitude, String longitude,
									List<WeatherDailyConditionDto> days,
									WeatherHourlyConditionDto currentConditions) {
}
