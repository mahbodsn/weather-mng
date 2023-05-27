package com.inpress.weathermng.service.metric.model;

import java.util.Map;

public record MetricCounterModel(String name, Integer count, Map<String, String> tags) {
}
