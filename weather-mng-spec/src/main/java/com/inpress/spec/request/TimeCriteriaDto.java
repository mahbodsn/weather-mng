package com.inpress.spec.request;

import javax.validation.constraints.NotNull;

public record TimeCriteriaDto(@NotNull Long from, @NotNull Long to) {
}
