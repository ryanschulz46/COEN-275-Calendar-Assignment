package calendar;

import java.awt.Dimension;

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
	
	
	double frameX;//
	double frameY;//
	
	//
	double calendarX;//
	double calendarY;
	

	double topBarX;//
	double topBarY;//
	double titleY;//
	double titleX;
	
	
	double cellX;//
	double cellY;//
	
	double eventY;//
	double dateY;//
	
	
	double sideX;//
	double sideY;//
	
	double inputTextY;
	double inputTextX;
	
	double inputDateY;
	double inputDateX;
	
	double historyY;
	double historyX;
	
	double buttonY;
	double buttonX;
	
	
	public DimensionManager() {
		
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
	
	public void yearView() {
		/* 
		* squareSize is used to set the X and Y size of the JFrame.
		* Recommended to be at least 1000
		* HD 1920 x 1080: Recommend 1000
		* UHD 3840 x 2160: Recommend 1500 
		*/
		
		
		
		calendarX = 900;
		dateY = 25;
		eventY = 75;
		topBarY =  100;
		sideX = 500;
		cellX = calendarX/7;
		
		buttonY = 50;
		buttonX= 100;
		
		
		inputDateY = 100;
		

		
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
		inputDateX = sideX*.5;
		inputTextY = sideY*.2;
		historyX= sideX*.5;
		historyY= sideY*.2;
		
		
		topBarSize.setSize(cellX, topBarY);
		dateCell.setSize(cellX, dateY);
		eventCell.setSize(cellX, eventY);
		gridCell.setSize(cellX, cellY);
		calendarSize.setSize(calendarX, calendarY);
		JFrameSize.setSize(frameX, frameY);
		sidePanelSize.setSize(sideX, sideY);
	}
	
	

}
