package resourcingapi.connorturlan.com.au.DateUtils;

import java.time.LocalDate;

public class DateUtils {
	public static boolean DateWithinRange(LocalDate start, LocalDate end, LocalDate test) {
		// test whether a given date is within the range of two others inclusively.
		return start.compareTo(test) <= 0 && end.compareTo(test) >= 0;
	}

	public static boolean DatesOverlap(LocalDate a_start, LocalDate a_end, LocalDate b_start, LocalDate b_end) {
		// test whether a given date is within the range of two others inclusively.
		return DateWithinRange(a_start, a_end, b_start) || 
			DateWithinRange(a_start, a_end, b_end) || 
			DateWithinRange(b_start, b_end, a_start) || 
			DateWithinRange(b_start, b_end, a_end);
	}
}
