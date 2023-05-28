package com.inpress.weathermng.config;

import com.inpress.weathermng.service.weather.model.WeatherSearchResult;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@EnableCaching
@Configuration
@Profile("!test")
public class CacheConfig {

	@Bean
	public javax.cache.CacheManager inMemoryCacheManager() {
		CachingProvider provider = Caching.getCachingProvider();
		javax.cache.CacheManager cacheManager = provider.getCacheManager();


		var weatherSearchByCityConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
						String.class,
						WeatherSearchResult.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB))
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(5)))
				.build();

		var weatherSearchByCoordinateConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
						String.class,
						WeatherSearchResult.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB)).withExpiry(
						ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(5)))
				.build();

		cacheManager.createCache("weatherSearchByCity", Eh107Configuration.fromEhcacheCacheConfiguration(weatherSearchByCityConfiguration));
		cacheManager.createCache("weatherSearchByCoordinate", Eh107Configuration.fromEhcacheCacheConfiguration(weatherSearchByCoordinateConfiguration));
		return cacheManager;
	}

}
