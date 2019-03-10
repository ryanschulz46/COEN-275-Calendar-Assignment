package calendar;

import java.awt.BorderLayout;
import javax.swing.*;

import datamanagers.*;
import panels.*;

public class GuiManager {

	
	public static DimensionManager dimAll;
	private JFrame window;
	private JPanel calendar;
	private SidePanel side;
	private TitleAndDays topBar;
	private GridCal allDays;
	private JScrollPane scrollableDays;
	private JScrollPane scrollableSide;

	

	
	public GuiManager() {
		
		
		//Create dimensionManager to handle all dynamic dimensions
		dimAll = new DimensionManager(this);
		
		
		
		
		//Initialize topBar
		topBar= new TitleAndDays();
	
		
		
		//initialize grid cells
		allDays = new GridCal(); 
		scrollableDays = new JScrollPane(allDays);
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.getCalendarY());

		
		//combine grid cells and top bar into one calendar
		calendar = new JPanel();
		calendar.setLayout(new BorderLayout());
		calendar.add(topBar, BorderLayout.NORTH);
		calendar.add(scrollableDays, BorderLayout.CENTER); 
		calendar.setPreferredSize(dimAll.calendarSize);
		
		
		
		//Initialize side panel
		side = new SidePanel(this);
		scrollableSide = new JScrollPane(side);

		
		
		///Add Calendar and Side Panel to the JFrame!
		window = new JFrame();
		window.setLayout(new BorderLayout());
		window.add(scrollableSide, BorderLayout.EAST);
		window.add(calendar, BorderLayout.CENTER);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}
	
	
	
/*
 * Functions triggered by radio buttons to resize everything
 */
	
	public void resizeWeek() {
		allDays.changeToWeek();
		//removes all labels and re-adds them without day of week showing in each cell
		allDays.addLabelsDate();
		//show days of week on top bar
		topBar.showDays();
		guiRevalidation();
	}
	
	public void resizeYear() {
		allDays.changeToYear();
		//removes all labels and re-adds them without day of week showing in each cell
		allDays.addLabelsDate();
		//show days of week on top bar
		topBar.showDays();
		guiRevalidation();
	}
	
	public void resizeDay() {
		allDays.changeToDay();
		//removes all labels and re-adds them with the day of week in each cell
		allDays.addLabelsDateWithDayName();
		//hide day of weeks on top bar
		topBar.hideDays();
		guiRevalidation();
	}
	
	//when resizing, we also need to reset back to Zero so the scroll works appropriately 
	public void refresh() {
		scrollableDays.getVerticalScrollBar().setValue(0);
		side.refresh();
		guiRevalidation();
	}
	
	
	//revalidates everything and repacks the frame
	public void guiRevalidation() {
		allDays.revalidate();
		topBar.revalidate();
		
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.getCalendarY());
		scrollableDays.revalidate();
		side.revalidate();
		window.revalidate();
		window.pack();
		window.revalidate();
	}
	


	
	/*
	 * functions called for adding and removing events in the grid
	 * it returns true, and allows the event to be added to history if there is no duplicate
	 * if there is a duplicate, it returns false, and does not add it to history
	 */
	public boolean addNode(Node add) {
		//must remove first 28 characters of string that contain HTML color data
		//this allows same events of different colors to be detected
		String entry = (add.getColoredEventName()).substring(28);
		String test;
		
		if (!allDays.eventSquare[add.getLocation()].listModel.isEmpty()) { //if not empty, check for duplicates
			try {
				for(int i = 0; i < allDays.eventSquare[add.getLocation()].listModel.getSize(); i++) {
					test = (String) allDays.eventSquare[add.getLocation()].listModel.elementAt(i);
					test = test.substring(28); //remove HTML color data
						if (entry.equals(test)) {
							displayError("Duplicate Event Error.");
							return false; //returns a failed add
						}
				}
			}
			catch(Exception e){
				System.out.println("Exception when trying check for duplicates: " + e);
				return false; //returns a failed add
			}
		}
		
		allDays.eventSquare[add.getLocation()].listModel.addElement(add.getColoredEventName());
		return true; //returns a successful add
	}
	
	
	/*
	 * History list nodes contain index location in grid
	 * Retrieves that location, and removes the node based on matching the string
	 */
	public void removeNode(Node toRemove) {
		allDays.eventSquare[toRemove.getLocation()].listModel.removeElement(toRemove.getColoredEventName());
	}
	
	
	
	
	
	////Error popup message on GUI
	public void displayError(String errorMessage) {
		JOptionPane.showMessageDialog(window, errorMessage);
	}
	
}
