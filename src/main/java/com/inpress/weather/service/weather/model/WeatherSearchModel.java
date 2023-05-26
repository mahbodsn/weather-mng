package com.inpress.weather.service.weather.model;

import com.inpress.weather.api.spec.WeatherSearchType;

public record WeatherSearchModel(WeatherSearchType searchType, TimeCriteriaModel timeCriteria,
								 CoordinateModel coordinate, String cityName) {

}
