package com.inpress.weather.api.spec.request;

import com.inpress.weather.api.spec.WeatherSearchType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record WeatherSearchRequest(@NotNull WeatherSearchType searchType, CoordinateDto coordinate,
								   @Valid @NotNull TimeCriteriaDto timeCriteria, String cityName) {
}
