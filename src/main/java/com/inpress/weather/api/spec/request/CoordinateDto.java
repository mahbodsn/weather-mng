package com.inpress.weather.api.spec.request;

import javax.validation.constraints.NotNull;

public record CoordinateDto(@NotNull Long latitude, @NotNull Long longitude) {
}
