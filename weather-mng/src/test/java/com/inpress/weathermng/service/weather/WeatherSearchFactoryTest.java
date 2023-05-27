package com.inpress.weathermng.service.weather;

import com.inpress.spec.WeatherSearchType;
import com.inpress.weathermng.client.weather.VisualCrossingClient;
import com.inpress.weathermng.service.metric.MetricService;
import com.inpress.weathermng.service.weather.impl.WeatherSearchByCityStrategy;
import com.inpress.weathermng.service.weather.impl.WeatherSearchByCoordinateStrategy;
import com.inpress.weathermng.service.weather.mapper.WeatherServiceMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
class WeatherSearchFactoryTest {

	@Autowired
	WeatherSearchFactory weatherSearchFactory;

	@MockBean
	VisualCrossingClient weatherSearchByCityStrategy;

	@MockBean
	MetricService metricService;

	@Test
	void getSearchStrategy_searchByCity_returnImplementation() {
		Assertions.assertThat(weatherSearchFactory.getSearchStrategy(WeatherSearchType.CITY))
				.isInstanceOf(WeatherSearchByCityStrategy.class);
	}

	@Test
	void getSearchStrategy_searchByCoordinate_returnImplementation() {
		Assertions.assertThat(weatherSearchFactory.getSearchStrategy(WeatherSearchType.COORDINATE))
				.isInstanceOf(WeatherSearchByCoordinateStrategy.class);
	}

	@TestConfiguration
	@ComponentScan(basePackageClasses = {WeatherSearchFactory.class, WeatherSearchByCoordinateStrategy.class, WeatherSearchByCityStrategy.class,
			WeatherServiceMapper.class},
			useDefaultFilters = false,
			includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
					classes = {WeatherSearchFactory.class, WeatherSearchByCoordinateStrategy.class, WeatherSearchByCityStrategy.class,
							WeatherServiceMapper.class}))
	static class Configuration {

	}

}