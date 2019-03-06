package calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import Panels.GridCal;
import Panels.SidePanel;
import Panels.TitleAndDays;

public class GuiManager {

	public JFrame window;
	public JPanel calendar;
	public SidePanel side;
	public TitleAndDays topBar;
	public GridCal allDays;
	public JScrollPane scrollableDays;
	public static DimensionManager dimAll;

	
	
	
	

	
	
	public GuiManager() {
		
		
		//Create dimensionManager to handle all dimensions of all JPanels and JFrame
		dimAll = new DimensionManager();
		
		//Initialize topBar and the grid of days, and combine into one JPanel, Calendar
		topBar= new TitleAndDays(dimAll);
	
		
		
		allDays = new GridCal(dimAll); 
		scrollableDays = new JScrollPane(allDays);
		scrollableDays.getVerticalScrollBar().setUnitIncrement(16);
		//scrollableDays.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		
		
		calendar = new JPanel();
		calendar.setLayout(new BorderLayout());
		calendar.add(topBar, BorderLayout.NORTH);
		calendar.add(scrollableDays, BorderLayout.CENTER); 
		calendar.setPreferredSize(dimAll.calendarSize);
		
		
		
		
		//Initialize side panel
		side = new SidePanel(dimAll, this);

		
		
		///Add Calendar and Side Panel to the JFrame!
		window = new JFrame();
		window.setLayout(new BorderLayout());
		window.add(side, BorderLayout.EAST);
		window.add(calendar, BorderLayout.CENTER);
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}

	public void resizeWeek() {
		allDays.changeToWeek();
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeYear() {
		allDays.changeToYear();
		scrollableDays.getVerticalScrollBar().setUnitIncrement(16);
		scrollableDays.revalidate();
		window.pack();
	}
	
	public void resizeDay() {
		allDays.changeToDay();
		scrollableDays.getVerticalScrollBar().setUnitIncrement((int) dimAll.calendarY);
		scrollableDays.revalidate();
		window.pack();
	}
	
}
