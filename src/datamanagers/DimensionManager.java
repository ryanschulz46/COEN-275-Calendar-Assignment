package datamanagers;

import java.awt.Dimension;

import calendar.GuiManager;

public class DimensionManager {

	public Dimension dateCell;
	public Dimension eventCell;
	public Dimension gridCell;
	public Dimension calendarSize;
	public Dimension JFrameSize;
	public Dimension sidePanelSize;
	public Dimension topBarSize;
	
	private double frameX;
	private double frameY;
	private double calendarX;
	private double calendarY;
	private double cellX;
	private double cellY;
	private double eventY;
	private double sideY;
	private String dimState;
	private GuiManager gui;
	
	private final double topBarY = 100;
	private final double dateY = 25;
	private final double sideX = 400;
	
	
	public DimensionManager(GuiManager gui) {
		
		//create a reference to the GUI manager to call its functions
		this.gui = gui;
		
		//initialize the functions
		topBarSize = new Dimension();
		dateCell = new Dimension();
		eventCell = new Dimension();
		gridCell = new Dimension();
		calendarSize = new Dimension();
		JFrameSize = new Dimension();
		sidePanelSize = new Dimension();
		
		//initial state
		calendarX = 700;
		this.yearView();
	}
	
	

	//sets cells X and Y to show week
	public void weekView() {
		dimState = "week";
		eventY = calendarY - dateY;
		cellX = calendarX/7;
		this.refreshDim();
	}
	
	
	
	//sets cells X and Y to view only each day
	public void dayView() {
		dimState = "day";
		cellX= calendarX;
		eventY = calendarY - dateY;
		this.refreshDim();
	}

	
	
	//sets cells X and Y to view whole year
	public void yearView() {
		dimState = "year";
		cellX = calendarX/7;
		eventY = calendarX/7 - dateY;
		this.refreshDim();
	}
	
	
	
	/*
	 * a lot of dimensions are linked to each other
	 * if one changes, you need to update the others
	 * this recomputes the linked dimensions for everything if one dim is changed
	 */
	public void refreshDim() {
		calendarY = calendarX;
		cellY = dateY + eventY;
		frameY = calendarY + topBarY;
		frameX = calendarX + sideX;
		sideY = frameY;
		
		topBarSize.setSize(cellX, topBarY);
		dateCell.setSize(cellX, dateY);
		eventCell.setSize(cellX, eventY);
		gridCell.setSize(cellX, cellY);
		calendarSize.setSize(calendarX + 48, calendarY);
		JFrameSize.setSize(frameX, frameY);
		sidePanelSize.setSize(sideX, sideY);
	}
	
	
	
	
	/*
	 * this is triggered by the button being pressed to change GUI Size
	 * x can be negative if size is to be decreased
	 * prevents going smaller than 700, as things look weird
	 * 
	 * checks what state the Gui was in previously
	 * recall's that resize for its state to make sure the grid squares fit
	 */
	public void changeSize(int x) {
		
		//x is negative if decreasing
		//smaller than 700 makes things look weird
		if ((calendarX + x) < 700) {
			gui.displayError("Cannot make the GUI any smaller");
			return;
		}
		
		calendarX += x;
		
		//refresh Gui and resize everything appropriately
		this.refreshDim();
		if (dimState.equals("day")) {
			dayView();
		}
		else if (dimState.equals("week")) {
			weekView();
		}
		else {
			yearView();
		}
		
		gui.refresh();
	}


	
	
	///Accessor needed to set scroll speed
	public double getCalendarY() {
		return calendarY;
	}
	

}
