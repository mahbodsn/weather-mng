package com.inpress.weathermng.client.weather;

import com.inpress.weathermng.config.VisualCrossingConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class VisualCrossingFeignInterceptor implements RequestInterceptor {

	private final VisualCrossingConfig config;

	@Override
	public void apply(RequestTemplate requestTemplate) {
		logger.debug("going to append api key to visual crossing url \uD83D\uDE01");
		requestTemplate.uri(String.format("?key=%s&?unitGroup=uk", config.getApiKey()), Boolean.TRUE);
	}
}
