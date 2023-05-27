package com.inpress.weathermng.service.weather;

import com.inpress.weathermng.exception.BusinessException;
import com.inpress.weathermng.service.weather.model.WeatherSearchModel;
import com.inpress.weathermng.service.weather.model.WeatherSearchResult;

public interface WeatherService {

	WeatherSearchResult search(WeatherSearchModel model) throws BusinessException;

}
