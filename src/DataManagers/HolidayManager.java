package DataManagers;

import java.util.HashMap;
import java.util.Map;

public class HolidayManager {
	
	public Map<Integer, String> holidays;
	public int dateKey;
	

	public HolidayManager() {
		
		/*
		 * This hashmap stores the holidays with their dates
		 * The two least significant numbers (Tens and Ones spot) are the day of the month
		 * The two greatest significant numbers (Thousands and Hundreds spot) are the month number
		 * Ex: 101 => Jan 1st, 1111 => Nov 11th
		 */
		

		holidays = new HashMap<Integer, String>(); 
		holidays.put(101, "New Year's Day");
		holidays.put(121, "MLK's Birthday");
		holidays.put(218, "Washington's Birthday");
		holidays.put(527, "Memorial Day");
		holidays.put(604, "Independence Day");
		holidays.put(902, "Labor Day");
		holidays.put(1014, "Columbus Day");
		holidays.put(1111, "Veterans Day");
		holidays.put(1128, "Thanksgiving Day");
		holidays.put(1225, "Christmas Day");
	}

	
	public String getHoliday() {
		return holidays.get(dateKey);
	}

	public boolean checkDate(int i, int j) {
		dateKey = 100*(i+1)+(j+1);
		if (holidays.containsKey(dateKey)){
			return true;
		}
		return false;
	}


	
}
