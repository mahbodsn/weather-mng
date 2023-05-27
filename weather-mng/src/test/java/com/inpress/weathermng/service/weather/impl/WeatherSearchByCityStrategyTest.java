package com.inpress.weathermng.service.weather.impl;

import com.inpress.spec.WeatherSearchType;
import com.inpress.weathermng.client.weather.VisualCrossingClient;
import com.inpress.weathermng.client.weather.spec.VisualCrossingWeatherTimelineResponse;
import com.inpress.weathermng.exception.BusinessException;
import com.inpress.weathermng.service.metric.MetricService;
import com.inpress.weathermng.service.weather.mapper.WeatherServiceMapper;
import com.inpress.weathermng.service.weather.model.TimeCriteriaModel;
import com.inpress.weathermng.service.weather.model.WeatherSearchModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class WeatherSearchByCityStrategyTest {

	@Autowired
	WeatherSearchByCityStrategy strategy;

	@MockBean
	MetricService metricService;

	@MockBean
	VisualCrossingClient client;

	@Test
	void search_validScenario_returnData() throws BusinessException {
		when(client.getWeatherByCityName(any(), any(), any()))
				.thenReturn(new VisualCrossingWeatherTimelineResponse("", "", 1L, 1L, List.of(), null));

		var model = new WeatherSearchModel(WeatherSearchType.CITY, new TimeCriteriaModel(1L, 2L), null, "mammad");
		var result = strategy.search(model);

		verify(metricService, times(1)).sendCounterMetric(any());

		assertThat(result).isNotNull();
	}

	@TestConfiguration
	@ComponentScan(basePackageClasses = {WeatherSearchByCityStrategy.class, WeatherServiceMapper.class},
			useDefaultFilters = false,
			includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
					classes = {WeatherSearchByCityStrategy.class, WeatherServiceMapper.class}))
	static class Configuration {

		@Bean
		public CacheManager getCacheManager() {
			return new NoOpCacheManager();
		}

	}

}