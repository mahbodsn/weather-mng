package com.inpress.weathermng.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public final class DateUtil {

	private DateUtil() {

	}

	public static LocalDate epochToLocalDate(Long epoch) {
		return Instant.ofEpochMilli(epoch).atZone(ZoneOffset.systemDefault()).toLocalDate();
	}

}
