package com.inpress.weathermng.service.metric.impl;

import com.inpress.weathermng.service.metric.MetricService;
import com.inpress.weathermng.service.metric.model.MetricCounterModel;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements MetricService {

	private final MeterRegistry meterRegistry;

	@Override
	public void sendCounterMetric(MetricCounterModel model) {
		logger.debug("going to send metric with model -> {}", model);
		var tags = model.tags().keySet().stream()
				.map(key -> Tag.of(key, model.tags().get(key)))
				.toList();
		meterRegistry.counter(model.name(), tags).increment(model.count());
	}
}
