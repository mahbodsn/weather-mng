package com.inpress.weathermng.service.weather.model;

import com.inpress.spec.WeatherSearchType;

public record WeatherSearchModel(WeatherSearchType searchType, TimeCriteriaModel timeCriteria,
								 CoordinateModel coordinate, String cityName) {

}
