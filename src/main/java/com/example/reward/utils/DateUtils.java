package com.example.reward.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtils {

	public static final String yyyy_MM_dd = "yyyy-MM-dd";

	// 오늘 날짜 구하기
	public static LocalDate today() {
		return LocalDate.now();
	}

	public static String todayString() {
		return today().format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
	}

	// 전날인지 체크
	public static boolean isYesterday(LocalDate localDate) {
		return today().minusDays(1).equals(localDate);
	}

	public static LocalDate toLocalDateString(String localDate) {
		return Optional.ofNullable(localDate)
				.map(l -> LocalDate.parse(localDate, DateTimeFormatter.ofPattern(yyyy_MM_dd)))
				.orElse(null);
	}
}
