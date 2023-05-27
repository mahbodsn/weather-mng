package com.inpress.weather.service.metric.model;

import java.util.Map;

public record MetricCounterModel(String name, Integer count, Map<String, String> tags) {
}
