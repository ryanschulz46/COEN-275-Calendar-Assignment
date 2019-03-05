package DataManagers;

/*
 * This class simply stores the abbreviations and total number of days in each month
 */

public class Months {
	private String month;
	private int days;
	private int monthNum;
	
	public Months(String monthAbbrev, int numOfDays, int monthNum) {
		month = monthAbbrev; //Abbreviation of the month
		days = numOfDays; //Total number of days in each month	
		this.monthNum = monthNum;
	}

	public String getMonth() {
		return month;
	}
	
	public int getDays() {
		return days;
	}

	public int getMonthNum() {
		return monthNum;
	}
}
