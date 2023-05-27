package com.inpress.weathermng.client.weather.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VisualCrossingHourlyWeatherDto(@JsonProperty("datetimeEpoch") Long date, Integer temp,
											 @JsonProperty("feelslike") Integer feelsLike, Integer humidity,
											 String conditions) {
}
