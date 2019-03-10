package panels;
import java.awt.*;
import javax.swing.*;

import calendar.GuiManager;
import datamanagers.DimensionManager;
import datamanagers.HolidayManager;
import datamanagers.Months;
import square.*;

import java.util.HashMap;
import java.util.Map;

public class GridCal extends JPanel {
	
	
	public static CalendarSquare[] eventSquare;
	public static Months[] monthData;
	private JPanel[] dateAndEvent;
	private HolidayManager holidays;
	private CalendarSquare[] dateSquare;
	private int counter;
	private int holidayCheck;

	
	
	
	
	public GridCal() {
		holidays = new HolidayManager();
		/*
		 * create an array of months class, adding their respective info (abbreviations and # of days)
		 * Months are added in order (1st item = Jan, 2nd = Feb, etc.)
		 */
		monthData = new Months[12];
		monthData[0] = new Months("Jan", 31, 1);
		monthData[1] = new Months("Feb", 28, 2);
		monthData[2] = new Months("Mar", 31, 3);
		monthData[3] = new Months("Apr", 30, 4);
		monthData[4] = new Months("May", 31, 5);
		monthData[5] = new Months("Jun", 30, 6);
		monthData[6] = new Months("Jul", 31, 7);
		monthData[7] = new Months("Aug", 31, 8);
		monthData[8] = new Months("Sep", 30, 9);
		monthData[9] = new Months("Oct", 31, 10);
		monthData[10] = new Months("Nov", 30, 11);
		monthData[11] = new Months("Dec", 31, 12);

		
		
		
		
		
/*
 * Creating JPanels with the correct Month-Date in order
 */
		
		/*
		 * Create an array of 367 JPanels for each date (365 + Dec 30 and Dec 31st)
		 * Initialize, set size, and background color of each JPanel
		 */
		
		dateSquare = new CalendarSquare[367];
		for(int i = 0; i<367; i++) {
			dateSquare[i] = new DateSquare(GuiManager.dimAll.dateCell);
		}
		
		//calls function to create labels with dates, but not include days of week
		addLabelsDate();
		

		/*
		 * Create an array of 367 JPanels for each date (365 + Dec 30 and Dec 31st)
		 * Index aligns up with Date Array for easy combining
		 * Ex: Index of "Dec 25th" in the date array is the same index as "Christmas Day" in this array
		 */
		
		eventSquare = new CalendarSquare[367];
		for(int i = 0; i<367; i++) {
			eventSquare[i] = new EventSquare(GuiManager.dimAll.eventCell);
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
				if (holidays.checkDate(i,j) == true){
					//If the date's key we are checking is in the hashmap, add a label to its respective holiday panel
				eventSquare[counter].listModel.addElement("<html>" + "<font color='#000000'>" + holidays.getHoliday() + "</font></html>");
				}
				counter++;
			}
		}
	
		
		
		
		
		
		
		
		
/*
 * Combine Date JPanel Array and Holiday JPanel arrays into one JPanel Array
 */
		
		dateAndEvent = new JPanel[367]; 
		
		/*
		 * Initialize, set size, and background color of each JPanel.
		 * Then, set the layout, and combined respective Date and Holiday JPanels 
		 */
		
		for (int i = 0; i<367;i++) {
			dateAndEvent[i] = new JPanel();
			dateAndEvent[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
			dateAndEvent[i].setPreferredSize(GuiManager.dimAll.gridCell);
			
			dateAndEvent[i].setLayout(new BoxLayout(dateAndEvent[i], BoxLayout.Y_AXIS));
			dateAndEvent[i].add(dateSquare[i]);
			dateAndEvent[i].add(eventSquare[i]);
		}	
/*
 * Add the combined panels into the grid of this object
 */
		
		this.setLayout(new GridLayout(53,7));
		for (int i = 0; i<367; i++) {
			this.add(dateAndEvent[i]);
		}	
			
	}//end of constructor
	
	
	
	
	
	
	
	
	
	
/*
 * Date Label Management Functions
 */
	
	
	//add labels without the day of week
	public void addLabelsDate() {
		//Manually create the first two cells: Dec 30th and Dec 31st for 2018
		dateSquare[0].listModel.removeAllElements();
		dateSquare[1].listModel.removeAllElements();
		dateSquare[0].listModel.addElement("Dec - 30");
		dateSquare[1].listModel.addElement("Dec - 31");
		
		/*
		 * Counter is incremented for dayOfYear[] array as the i and j cannot do this for us easily
		 * Counter starts at 2, since we already added Dec30 and 31st
		 * Outer loop increments between each month
		 * Inner loop increments between the days of each month. monthData[i].getDays() ensures the for loop will not go beyond the number of days in each month
		 * It clears any previously existing label and then adds a new label
		 * Date label is red if first of month - date label is black otherwise
		 */

		counter = 2;

		for (int i = 0; i < 12; i++) { //
			for (int j = 0 ; j < monthData[i].getDays(); j++) {
				dateSquare[counter].listModel.removeAllElements();
				if (j == 0) { 
					dateSquare[counter].listModel.addElement("<html>" + "<font color='#FF0000'>" + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>");
				}
				else { 
					dateSquare[counter].listModel.addElement("<html>" + "<font color='#000000'>" + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>");	
				}
					counter++;
			}
		}
	}
	
	

	
	//This function is for the day view to add Day of Week to each invididual cell
	public void addLabelsDateWithDayName() {
		//Manually create the first two cells: Dec 30th and Dec 31st for 2018
		dateSquare[0].listModel.removeAllElements();
		dateSquare[1].listModel.removeAllElements();
		dateSquare[0].listModel.addElement("Sunday: Dec - 30");
		dateSquare[1].listModel.addElement("Monday: Dec - 31");
		
		/*
		 * Counter is incremented for dayOfYear[] array as the i and j cannot do this for us easily
		 * Counter starts at 2, since we already added Dec30 and 31st
		 * Outer loop increments between each month
		 * Inner loop increments between the days of each month. monthData[i].getDays() ensures the for loop will not go beyond the number of days in each month
		 * It clears any previously existing label and then adds a new label
		 * Utilizes counter%7 to find out what day of the week should go on each date 
		 * Date label is red if first of month - date label is black otherwise
		 */
		
		String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		counter = 2;

		for (int i = 0; i < 12; i++) { //
			for (int j = 0 ; j < monthData[i].getDays(); j++) {
				//clears any previous labels
				if (j == 0) { 
					dateSquare[counter].listModel.addElement("<html>" + "<font color='#FF0000'>"+ daysOfWeek[(counter%7)] + ": "  + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>");
				}
				else { 
					dateSquare[counter].listModel.addElement("<html>" + "<font color='#000000'>" + daysOfWeek[(counter%7)] + ": " + monthData[i].getMonth()  + " - " + (j+1) + "</font></html>");	
				}
					counter++;
			}
		}
	}
	
	
	
	
	
	

/*
 * View Management Functions
 */
	
	public void changeToWeek() {
		//adjust dimensions saved to be resized for desired view
		GuiManager.dimAll.weekView();
		//ensures grid is 53 x 7
		this.setLayout(new GridLayout(53,7));
		//update the dimensions for each cell
		for(int i= 0; i< 367; i++) {
			dateAndEvent[i].setPreferredSize(GuiManager.dimAll.gridCell);
			eventSquare[i].setPreferredSize(GuiManager.dimAll.eventCell);
			dateAndEvent[i].revalidate();
			eventSquare[i].revalidate();
		}
	}
	
	
	public void changeToYear() {
		GuiManager.dimAll.yearView();
		//ensures grid is 53 x 7
		this.setLayout(new GridLayout(53,7));
		//update the dimensions for each cell
		for(int i= 0; i< 367; i++) {
			dateAndEvent[i].setPreferredSize(GuiManager.dimAll.gridCell);
			eventSquare[i].setPreferredSize(GuiManager.dimAll.eventCell);
			dateAndEvent[i].revalidate();
			eventSquare[i].revalidate();
		}
	}
	
	public void changeToDay() {
		GuiManager.dimAll.dayView();
		//Changes calendar to one collumn with each day being its own row
		this.setLayout(new GridLayout(367, 1));
		//update the dimensions for each cell
		for(int i= 0; i< 367; i++) {
			dateAndEvent[i].setPreferredSize(GuiManager.dimAll.gridCell);
			eventSquare[i].setPreferredSize(GuiManager.dimAll.eventCell);
			dateAndEvent[i].revalidate();
			eventSquare[i].revalidate();
			
		}
	}

}
