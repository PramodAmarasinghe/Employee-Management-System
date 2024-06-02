package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DayFilter {

	String year;
	String month;
	String day;

	public void filter(String dateStr) {
		// String dateStr = "25/10/2023"; // Replace with your date in "dd/MM/yyyy"
		// format

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date = sdf.parse(dateStr);

			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
			SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

			year = yearFormat.format(date);
			month = monthFormat.format(date);
			day = dayFormat.format(date);

		} catch (ParseException e) {
			System.err.println("Invalid date format: " + dateStr);
			e.printStackTrace();
		}
	}

	public Date day() {

		Date str;
		str = java.sql.Date
				.valueOf(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
		return str;

	}

}
