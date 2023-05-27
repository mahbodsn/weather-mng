package com.inpress.weathermng.api.web.mapper;

import com.inpress.spec.request.CoordinateDto;
import com.inpress.spec.request.TimeCriteriaDto;
import com.inpress.spec.request.WeatherSearchRequest;
import com.inpress.spec.response.WeatherSearchResponse;
import com.inpress.weathermng.service.weather.model.CoordinateModel;
import com.inpress.weathermng.service.weather.model.TimeCriteriaModel;
import com.inpress.weathermng.service.weather.model.WeatherSearchModel;
import com.inpress.weathermng.service.weather.model.WeatherSearchResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherResourceMapper {

	CoordinateModel toCoordinateModel(CoordinateDto coordinateDto);

	TimeCriteriaModel toTimeCriteriaModel(TimeCriteriaDto timeCriteriaDto);

	WeatherSearchModel toWeatherSearchModel(WeatherSearchRequest request);

	WeatherSearchResponse toWeatherSearchResponse(WeatherSearchResult result);
}
