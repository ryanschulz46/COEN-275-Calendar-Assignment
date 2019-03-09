package DataManagers;

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
	public Dimension button;
	public Dimension history;
	public Dimension inputEvent;
	public Dimension inputDate;
	
	
	public double frameX;//
	public double frameY;//
	
	//
	public double calendarX;//
	public double calendarY;
	

	public double topBarX;//
	public double topBarY;//
	public double titleY;//
	public double titleX;
	
	
	public double cellX;//
	public double cellY;//
	
	public double eventY;//
	public double dateY;//
	
	
	public double sideX;//
	public double sideY;//
	
	public double inputTextY;
	public double inputTextX;
	
	public double inputDateY;
	public double inputDateX;
	
	public double historyY;
	public double historyX;
	
	public double buttonY;
	public double buttonX;
	public GuiManager gui;
	
	
	public DimensionManager(GuiManager gui) {
		
		this.gui = gui;
		
		topBarSize = new Dimension();
		dateCell = new Dimension();
		eventCell = new Dimension();
		gridCell = new Dimension();
		calendarSize = new Dimension();
		JFrameSize = new Dimension();
		sidePanelSize = new Dimension();
		button = new Dimension();
		inputEvent = new Dimension();
		inputDate = new Dimension();
		history = new Dimension();
		
		
		
		this.yearView();
	}
	
	
	public void weekView() {
		eventY = calendarY - dateY;
		cellX = calendarX/7;
		this.refreshDim();
	}
	
	public void dayView() {
		cellX= calendarX;
		eventY = calendarY - dateY;
		this.refreshDim();
	}
	
	public void changeSize(int x) {
		if ((calendarX + x) < 700) {
			gui.displayError("Cannot make the GUI any smaller");
			return;
		}
		calendarX += x;
		
		
		this.refreshDim();
		gui.refresh();
		
		
	}
	
	public void yearView() {
		/* 
		* squareSize is used to set the X and Y size of the JFrame.
		* Recommended to be at least 1000
		* HD 1920 x 1080: Recommend 1000
		* UHD 3840 x 2160: Recommend 1500 
		*/
		
		
		
		calendarX = 700;
		dateY = 25;
		eventY = calendarX/7 - dateY;
		topBarY =  100;
		sideX = 400;
		cellX = calendarX/7;
		
		buttonY = 50;
		buttonX= 100;
		
		
		inputDateY = 50;
		

		
		this.refreshDim();
	}
	
	
	//recomputes dimensions for everything if one dim is changed
	public void refreshDim() {
		calendarY = calendarX;
		titleX = calendarX;
		topBarX = calendarX;
		cellY = dateY + eventY;
		frameY = calendarY + topBarY;
		frameX = calendarX + sideX;
		sideY = frameY;
		titleY =  topBarY - dateY;
		inputTextX = sideX*.5;
		inputDateX = sideX*.8;
		inputTextY = sideY*.2;
		historyX= sideX*.8;
		historyY= sideY*.8;
		
		
		topBarSize.setSize(cellX, topBarY);
		dateCell.setSize(cellX, dateY);
		eventCell.setSize(cellX, eventY);
		gridCell.setSize(cellX, cellY);
		calendarSize.setSize(calendarX + 48, calendarY);
		JFrameSize.setSize(frameX, frameY);
		sidePanelSize.setSize(sideX, sideY);
		inputDate.setSize(inputDateX, inputDateY);
		inputEvent.setSize(inputTextX, inputTextY);
		history.setSize(historyX, historyY);
		button.setSize(buttonX, buttonY);
	}
	
	

}
