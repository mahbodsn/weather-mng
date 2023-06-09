package com.inpress.weathermng.client.weather;

import com.inpress.weathermng.client.weather.spec.VisualCrossingWeatherTimelineResponse;
import com.inpress.weathermng.exception.BusinessException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "visualCrossingFeignClient", url = "${visual-crossing.base.url}",
		configuration = {VisualCrossingFeignInterceptor.class, VisualCrossingClientErrorDecoder.class})
public interface VisualCrossingClient {

	@GetMapping(path = "/timeline/{latitude},{longitude}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	VisualCrossingWeatherTimelineResponse getWeatherByCoordinates(@PathVariable Long latitude, @PathVariable Long longitude,
																  @PathVariable String startDate, @PathVariable String endDate) throws BusinessException;

	@GetMapping(path = "/timeline/{cityName}/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	VisualCrossingWeatherTimelineResponse getWeatherByCityName(@PathVariable String cityName,
															   @PathVariable String startDate,
															   @PathVariable String endDate) throws BusinessException;

}
