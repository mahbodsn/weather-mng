package com.inpress.weather.service.weather.mapper;

import com.inpress.weather.client.weather.spec.VisualCrossingWeatherTimelineResponse;
import com.inpress.weather.service.weather.model.WeatherSearchResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherServiceMapper {

	WeatherSearchResult toWeatherSearchResult(VisualCrossingWeatherTimelineResponse searchResult);

}
