package com.inpress.weathermng.service.metric;

import com.inpress.weathermng.service.metric.model.MetricCounterModel;

public interface MetricService {

	void sendCounterMetric(MetricCounterModel model);

}
