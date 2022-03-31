package org.crm;

import java.text.SimpleDateFormat;
import java.util.Date;

class LocalDate {

	static String getCurrentDate() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatNow = formatter.format(now);
		return formatNow;
	}
}
