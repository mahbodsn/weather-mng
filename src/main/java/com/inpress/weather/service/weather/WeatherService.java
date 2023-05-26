package com.inpress.weather.service.weather;

import com.inpress.weather.exception.BusinessException;
import com.inpress.weather.service.weather.model.WeatherSearchModel;

public interface WeatherService {

	Object search(WeatherSearchModel model) throws BusinessException;

}
