package com.inpress.weather.service.metric;

import com.inpress.weather.service.metric.model.MetricCounterModel;

public interface MetricService {

	void sendCounterMetric(MetricCounterModel model);

}
