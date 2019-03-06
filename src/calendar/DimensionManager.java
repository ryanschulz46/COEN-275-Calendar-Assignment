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
	
	
	
	
	public DimensionManager() {
		
		topBarSize = new Dimension();
		dateCell = new Dimension();
		eventCell = new Dimension();
		gridCell = new Dimension();
		calendarSize = new Dimension();
		JFrameSize = new Dimension();
		sidePanelSize = new Dimension();
		
		
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
		
		
		topBarSize.setSize(cellX, topBarY);
		dateCell.setSize(cellX, dateY);
		eventCell.setSize(cellX, eventY);
		gridCell.setSize(cellX, cellY);
		calendarSize.setSize(calendarX, calendarY);
		JFrameSize.setSize(frameX, frameY);
		sidePanelSize.setSize(sideX, sideY);
	}
	
	

}
