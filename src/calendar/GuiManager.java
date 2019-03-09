package calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import DataManagers.*;
import Panels.*;

public class GuiManager {

	public JFrame window;
	public JPanel calendar;
	public SidePanel side;
	public TitleAndDays topBar;
	public GridCal allDays;
	public JScrollPane scrollableDays;
	public JScrollPane scrollableSide;
	public static DimensionManager dimAll;
	
	
	

	
	
	public GuiManager() {
		
		
		//Create dimensionManager to handle all dimensions of all JPanels and JFrame
		dimAll = new DimensionManager(this);
		
		//Initialize topBar and the grid of days, and combine into one JPanel, Calendar
		topBar= new TitleAndDays(dimAll);
	
		
		
		allDays = new GridCal(dimAll); 
		scrollableDays = new JScrollPane(allDays);
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		//scrollableDays.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		
		
		calendar = new JPanel();
		calendar.setLayout(new BorderLayout());
		calendar.add(topBar, BorderLayout.NORTH);
		calendar.add(scrollableDays, BorderLayout.CENTER); 
		calendar.setPreferredSize(dimAll.calendarSize);
		
		
		
		//Initialize side panel
		side = new SidePanel(this);
		scrollableSide = new JScrollPane(side);
		//scrollableSide.getVerticalScrollBar().setUnitIncrement(dimAll.calendarSize);
		
		
		///Add Calendar and Side Panel to the JFrame!
		window = new JFrame();
		window.setLayout(new BorderLayout());
		window.add(scrollableSide, BorderLayout.EAST);
		window.add(calendar, BorderLayout.CENTER);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}
	
	
	
	//functions called for resizing grid

	public void resizeWeek() {
		allDays.removeLabels();
		allDays.addLabelsDate();
		allDays.revalidate();
		allDays.changeToWeek();
		topBar.showDays();
		topBar.revalidate();
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeYear() {
		dimAll.yearView();
		allDays.removeLabels();
		allDays.addLabelsDate();
		allDays.revalidate();
		allDays.changeToYear();
		topBar.showDays();
		topBar.revalidate();
		//scrollableDays.getVerticalScrollBar().setUnitIncrement(16);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeDay() {
		allDays.removeLabels();
		allDays.addLabelsDateWithDayName();
		allDays.changeToDay();
		topBar.hideDays();
		topBar.revalidate();
		//scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void refresh() {
		allDays.revalidate();
		topBar.revalidate();
		side.revalidate();
		scrollableDays.revalidate();
		scrollableDays.getVerticalScrollBar().setValue(0);
		window.revalidate();
		window.pack();
		window.revalidate();
	}
	//functions called for adding and removing grids
	
	public boolean addNode(Node add) {
		String entry = (add.getColoredEventName()).substring(28);
		String test;
		if (!allDays.eventSquare[add.location].listModel.isEmpty()) {
			try {
				for(int i = 0; i < allDays.eventSquare[add.location].listModel.getSize(); i++) {
					test = (String) allDays.eventSquare[add.location].listModel.elementAt(i);
					test = test.substring(28);
						if (entry.equals(test)) {
							displayError("Duplicate Event Error.");
							return false;
						}
				}
			}
			catch(Exception e){
				System.out.println("Exception when trying check for duplicates: " + e);
			}
		}
		
		allDays.eventSquare[add.location].listModel.addElement(add.getColoredEventName());
		return true;
	}
	
	public void removeNode(Node toRemove) {
		allDays.eventSquare[toRemove.location].listModel.removeElement(toRemove.getColoredEventName());
	}
	
	public void displayError(String errorMessage) {
		JOptionPane.showMessageDialog(window, errorMessage);
	}
	
}
