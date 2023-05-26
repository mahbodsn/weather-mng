package com.inpress.weather;

import com.inpress.weather.client.weather.VisualCrossingClient;
import com.inpress.weather.config.VisualCrossingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@EnableFeignClients
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
@RequiredArgsConstructor
public class WeatherApplication implements ApplicationRunner {

	private final VisualCrossingClient client;

	private final VisualCrossingConfig config;

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		var startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		var endDate = LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//		var result = client.getWeatherByCoordinates(35L, 51L, startDate, endDate);
//		logger.info("mammad -> {}", result);

	}
}
