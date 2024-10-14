package com.parameta.employee.util;

import java.time.LocalDate;

public class Utils {
	
	public static LocalDate formatDate(String date) {
		LocalDate dateFormat = LocalDate.parse(date);
		return dateFormat;
	}
}
