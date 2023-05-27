package com.inpress.weathermng.client.weather;

import com.inpress.spec.ErrorCode;
import com.inpress.weathermng.exception.BusinessException;
import com.inpress.weathermng.service.metric.MetricService;
import com.inpress.weathermng.service.metric.model.MetricCounterModel;
import com.inpress.weathermng.service.metric.model.Metrics;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class VisualCrossingClientErrorDecoder implements ErrorDecoder {

	private final MetricService metricService;

	@Override
	public Exception decode(String s, Response response) {
		logger.error("error occurred on visual crossing feign client -> {}", response.body().toString());

		if (response.status() == 429) {
			logger.error("visual crossing rate limit \uD83D\uDE22");
			metricService.sendCounterMetric(new MetricCounterModel(Metrics.VISUAL_CROSSING_ERROR.getKey(), 1, Map.of("errorCode", ErrorCode.PROVIDER_RATE_LIMIT_EXCEPTION.name())));
			return new BusinessException(ErrorCode.PROVIDER_RATE_LIMIT_EXCEPTION);
		} else if (response.status() == 400) {
			logger.error("visual crossing bad request \uD83D\uDEB6\uD83C\uDFFE\u200D♂\uFE0F");
			metricService.sendCounterMetric(new MetricCounterModel(Metrics.VISUAL_CROSSING_ERROR.getKey(), 1, Map.of("errorCode", ErrorCode.INVALID_LOCATION.name())));
			return new BusinessException(ErrorCode.INVALID_LOCATION);
		} else {
			logger.error("");
			metricService.sendCounterMetric(new MetricCounterModel(Metrics.VISUAL_CROSSING_ERROR.getKey(), 1, Map.of("errorCode", ErrorCode.PROVIDER_UNKNOWN_ERROR.name())));
			return new BusinessException(ErrorCode.PROVIDER_UNKNOWN_ERROR);
		}
	}
}
