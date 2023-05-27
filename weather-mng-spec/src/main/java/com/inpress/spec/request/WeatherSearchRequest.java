package com.inpress.spec.request;

import com.inpress.spec.WeatherSearchType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record WeatherSearchRequest(@NotNull WeatherSearchType searchType, CoordinateDto coordinate,
								   @Valid @NotNull TimeCriteriaDto timeCriteria, String cityName) {
}
