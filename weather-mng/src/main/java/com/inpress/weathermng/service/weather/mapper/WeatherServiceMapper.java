package com.inpress.weathermng.service.weather.mapper;

import com.inpress.weathermng.client.weather.spec.VisualCrossingWeatherTimelineResponse;
import com.inpress.weathermng.service.weather.model.WeatherSearchResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherServiceMapper {

	WeatherSearchResult toWeatherSearchResult(VisualCrossingWeatherTimelineResponse searchResult);

}
