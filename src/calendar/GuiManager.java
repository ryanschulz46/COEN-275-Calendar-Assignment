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
		dimAll = new DimensionManager();
		
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
		allDays.changeToWeek();
		topBar.showDays();
		topBar.revalidate();
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeYear() {
		allDays.changeToYear();
		topBar.showDays();
		topBar.revalidate();
		//scrollableDays.getVerticalScrollBar().setUnitIncrement(16);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeDay() {
		allDays.changeToDay();
		topBar.hideDays();
		topBar.revalidate();
		//scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
	
	//functions called for adding and removing grids
	
	public void addNode(Node add) {
		allDays.eventSquare[add.location].listModel.addElement(add.getColoredEventName());
	}
	
	public void removeNode(Node toRemove) {
		allDays.eventSquare[toRemove.location].listModel.removeElement(toRemove.getColoredEventName());
	}
	
	public void displayError(String errorMessage) {
		JOptionPane.showMessageDialog(window, errorMessage);
	}
	
}
