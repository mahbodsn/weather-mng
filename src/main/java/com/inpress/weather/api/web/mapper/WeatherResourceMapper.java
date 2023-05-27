package com.inpress.weather.api.web.mapper;

import com.inpress.weather.api.spec.request.CoordinateDto;
import com.inpress.weather.api.spec.request.TimeCriteriaDto;
import com.inpress.weather.api.spec.request.WeatherSearchRequest;
import com.inpress.weather.api.spec.response.WeatherSearchResponse;
import com.inpress.weather.service.weather.model.CoordinateModel;
import com.inpress.weather.service.weather.model.TimeCriteriaModel;
import com.inpress.weather.service.weather.model.WeatherSearchModel;
import com.inpress.weather.service.weather.model.WeatherSearchResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherResourceMapper {

	CoordinateModel toCoordinateModel(CoordinateDto coordinateDto);

	TimeCriteriaModel toTimeCriteriaModel(TimeCriteriaDto timeCriteriaDto);

	WeatherSearchModel toWeatherSearchModel(WeatherSearchRequest request);

	WeatherSearchResponse toWeatherSearchResponse(WeatherSearchResult result);
}
