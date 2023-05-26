package com.inpress.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "visual-crossing")
public class VisualCrossingConfig {

	private String apiKey;

}
