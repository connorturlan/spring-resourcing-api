package DateUtils;

import java.time.LocalDate;

public class DateUtils {
	public static boolean DateWithinRange(LocalDate start, LocalDate end, LocalDate test) {
		// test whether a given date is within the range of two others inclusively.
		return start.compareTo(test) >= 0 && end.compareTo(test) <= 0;
	}
}
