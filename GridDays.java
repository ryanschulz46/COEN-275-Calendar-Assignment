package calendar;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GridDays extends JPanel {
	
	GridDays(double dateY, double holidayY, double cellX) {
		
//Initialize and fill the structures needed for holidays and month information
		
		/*
		 * This hashmap stores the holidays with their dates
		 * The two least significant numbers (Tens and Ones spot) are the day of the month
		 * The two greatest significant numbers (Thousands and Hundreds spot) are the month number
		 * Ex: 101 => Jan 1st, 1111 => Nov 11th
		 */
		
		Map<Integer, String> holidays = new HashMap<Integer, String>(); 
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

		/*
		 * create an array of months class, adding their respective info (abbreviations and # of days)
		 * Months are added in order (1st item = Jan, 2nd = Feb, etc.)
		 */
		Months[] monthData = new Months[12];
		monthData[0] = new Months("Jan", 31);
		monthData[1] = new Months("Feb", 28);
		monthData[2] = new Months("Mar", 31);
		monthData[3] = new Months("Apr", 30);
		monthData[4] = new Months("May", 31);
		monthData[5] = new Months("Jun", 30);
		monthData[6] = new Months("Jul", 31);
		monthData[7] = new Months("Aug", 31);
		monthData[8] = new Months("Sep", 30);
		monthData[9] = new Months("Oct", 31);
		monthData[10] = new Months("Nov", 30);
		monthData[11] = new Months("Dec", 31);
		
//Size Variables

		/*
		 * Create dimensions of the holiday section, date section, and each individual cell
		 * This is populated from the three values (double) that are passed through
		 */
		
		Dimension dateSize = new Dimension();
		Dimension holidaySize = new Dimension();
		Dimension cellSize = new Dimension();
		dateSize.setSize(cellX, dateY);
		holidaySize.setSize(cellX, holidayY);
		cellSize.setSize(cellX, cellX);
		
		
//Creating JPanels with the correct Month-Date in order
		
		/*
		 * Create an array of 367 JPanels for each date (365 + Dec 30 and Dec 31st)
		 * Initialize, set size, and background color of each JPanel
		 */
		
		JPanel[] dayOfYear = new JPanel[367]; 
		for (int i = 0; i<367;i++) {
			dayOfYear[i] = new JPanel();
			dayOfYear[i].setPreferredSize(dateSize);
			dayOfYear[i].setBackground(Color.WHITE);
		}
		
		//Manually create the first two cells: Dec 30th and Dec 31st for 2018
		JLabel dec30 = new JLabel("Dec - 30");
		JLabel dec31 = new JLabel("Dec - 31");
		dayOfYear[0].add(dec30);
		dayOfYear[1].add(dec31);
		
		/*
		 * Counter is incremented for dayOfYear[] array as the i and j cannot do this for us easily
		 * Counter starts at 2, since we already added Dec30 and 31st
		 * Outer loop increments between each month
		 * Inner loop increments between the days of each month. monthData[i].getDays() ensures the for loop will not go beyond the number of days in each month
		 */

		int counter = 2;

		for (int i = 0; i < 12; i++) { //
			for (int j = 0 ; j < monthData[i].getDays(); j++) { //
				//If first day of the month, make the date yellow
				if (j == 0) { 
					dayOfYear[counter].add(new JLabel("<html>" + "<font color='#FF0000'>" + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>", SwingConstants.CENTER));
				}
				//If not the first day, make the date black.
				else { 
					dayOfYear[counter].add(new JLabel("<html>" + "<font color='#000000'>" + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>", SwingConstants.CENTER));	
				}
					counter++;
			}
		}
		
//Creating JPanels with Federal Holidays

		/*
		 * Create an array of 367 JPanels for each date (365 + Dec 30 and Dec 31st)
		 * Index aligns up with Date Array for easy combining
		 * Ex: Index of "Dec 25th" in the date array is the same index as "Christmas Day" in this array
		 */
		
		int holidayCheck;
		JPanel[] holidayArray = new JPanel[367];
		for (int i = 0; i<367;i++) {
			holidayArray[i] = new JPanel();
			holidayArray[i].setPreferredSize(holidaySize);
			holidayArray[i].setBackground(Color.CYAN);
		}
		
		/*
		 * Counter starts at 2, like above, to avoid checking dates from the Dec 30, 2018 and Dec 31, 2018.
		 * Outer loop increments between each month
		 * Inner loop increments between the days of each month. 
		 * monthData[i].getDays() ensures the for loop will not go beyond the number of days in each month
		 */
		
		counter = 2; 
		for (int i = 0; i < 12; i++) {  //
			for (int j = 0 ; j < monthData[i].getDays(); j++) { //
				//This converts the day we are checking to the format of hashmap's keys
				// => July 6th = 706
				holidayCheck = 100*(i+1)+(j+1); 
				if (holidays.containsKey(holidayCheck)){
					//If the date's key we are checking is in the hashmap, add a label to its respective holiday panel
					holidayArray[counter].add(new JLabel(holidays.get(holidayCheck)));
				}
				counter++;
			}
		}
	
//Combine Date JPanel Array and Holiday JPanel arrays into one JPanel Array
		JPanel[] dateAndHoliday = new JPanel[367]; 
		
		/*
		 * Initialize, set size, and background color of each JPanel.
		 * Then, set the layout, and combined respective Date and Holiday JPanels 
		 */
		
		for (int i = 0; i<367;i++) {
			dateAndHoliday[i] = new JPanel();
			dateAndHoliday[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
			dateAndHoliday[i].setPreferredSize(cellSize);
			
			dateAndHoliday[i].setLayout(new BoxLayout(dateAndHoliday[i], BoxLayout.Y_AXIS));
			dateAndHoliday[i].add(dayOfYear[i]);
			dateAndHoliday[i].add(holidayArray[i]);
		}
		
		
//Add the combined panels into the grid of this object
		
		this.setLayout(new GridLayout(53,7));
		for (int i = 0; i<367; i++) {
			this.add(dateAndHoliday[i]);
		}	
			
	}
}
