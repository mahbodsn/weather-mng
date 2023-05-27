package com.inpress.weather.service.weather;

import com.inpress.weather.exception.BusinessException;
import com.inpress.weather.service.weather.model.WeatherSearchModel;
import com.inpress.weather.service.weather.model.WeatherSearchResult;

public interface WeatherService {

	WeatherSearchResult search(WeatherSearchModel model) throws BusinessException;

}
