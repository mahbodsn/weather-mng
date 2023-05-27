package com.inpress.weather.client.weather.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VisualCrossingWeatherTimelineResponse(String address, String timezone, Long latitude, Long longitude,
													List<VisualCrossingDailyWeatherDto> days,
													VisualCrossingHourlyWeatherDto currentConditions) {
}
