package com.inpress.weathermng.service.weather;

import com.inpress.spec.WeatherSearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherSearchFactory {

	private final List<WeatherSearchStrategy> searchStrategies;

	public WeatherSearchStrategy getSearchStrategy(WeatherSearchType searchType) {
		return searchStrategies.stream()
				.filter(weatherSearchStrategy -> weatherSearchStrategy.getSearchType().equals(searchType))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("couldn't find search implementation with type -> " + searchType.name()));
	}

}
