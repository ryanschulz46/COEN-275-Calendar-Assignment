package calendar;

/*
 * This class simply stores the abbreviations and total number of days in each month
 */

public class Months {
	private String month;
	private int days;
	
	public Months(String monthAbbrev, int numOfDays) {
		month = monthAbbrev; //Abbreviation of the month
		days = numOfDays; //Total number of days in each month	
	}

	public String getMonth() {
		return month;
	}
	
	public int getDays() {
		return days;
	}

}
