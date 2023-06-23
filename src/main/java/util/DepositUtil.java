package util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class DepositUtil {
	public static String plusDay(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate date = LocalDate.parse(input, formatter);
		LocalDate nextDay = date.plusDays(1);
		String output = nextDay.format(formatter);

		return output;
	}
	
	public static String convertTimestampToString(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String dateString = dateFormat.format(timestamp);
		return dateString.substring(0, 10);
	}
}
