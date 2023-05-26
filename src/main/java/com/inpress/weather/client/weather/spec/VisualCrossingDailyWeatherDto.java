package com.inpress.weather.client.weather.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VisualCrossingDailyWeatherDto(@JsonProperty("datetimeEpoch") Long date,
											@JsonProperty("tempmax") Integer maxTemp,
											@JsonProperty("tempmin") Integer minTemp, Integer temp,
											@JsonProperty("feelslikemax") Integer maxFeelsLike,
											@JsonProperty("feelslikemin") Integer minFeelsLike,
											@JsonProperty("feelslike") Integer feelsLike, String conditions,
											String description, List<VisualCrossingHourlyWeatherDto> hours) {
}
