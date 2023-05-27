package com.inpress.weathermng.api.web.validator;

import com.inpress.spec.WeatherSearchType;
import com.inpress.spec.request.WeatherSearchRequest;
import com.inpress.weathermng.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Slf4j
@Component
public class WeatherSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WeatherSearchRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		var request = (WeatherSearchRequest) target;

		ValidationUtils.rejectIfEmpty(errors, "timeCriteria", "empty value", "timeCriteria should not be null");

		if (Objects.nonNull(request.timeCriteria())) {
			var startDate = DateUtil.epochToLocalDate(request.timeCriteria().from());
			var endDate = DateUtil.epochToLocalDate(request.timeCriteria().to());

			if (startDate.isAfter(endDate)) {
				errors.rejectValue("timeCriteria.from", "invalid.timeCriteria.from", "invalid start date, start date should not be after end date \uD83D\uDE01");
			}
		}

		if (request.searchType().equals(WeatherSearchType.COORDINATE)) {
			ValidationUtils.rejectIfEmpty(errors, "coordinate", "empty value", "coordinate field should not be null");
			if (Objects.nonNull(request.coordinate())) {
				ValidationUtils.rejectIfEmpty(errors, "coordinate.latitude", "empty value", "latitude empty");
				ValidationUtils.rejectIfEmpty(errors, "coordinate.longitude", "empty value", "longitude empty");
			}
		} else if (request.searchType().equals(WeatherSearchType.CITY)) {
			ValidationUtils.rejectIfEmpty(errors, "cityName", "empty value", "city name is empty");
		} else {
			logger.error("validation for search type => [{}] not implemented", request.searchType());
			throw new UnsupportedOperationException("validation not implemented");
		}
	}
}
