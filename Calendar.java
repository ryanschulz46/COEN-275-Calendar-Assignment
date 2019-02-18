package calendar;

import java.awt.*;
import javax.swing.*;

public class Calendar extends JFrame {
	
	public Calendar(int frameSize){
	
	/* 
	 * Creates sizing constants based on the passed through value "Frame Size"
	 * .25 and .75 were chosen to keep the ratio between the grey date section and blue holiday/wrtie in section to look like the example	
	 */
		
	final double cellX = frameSize/7;
	final double dateY = (cellX * 0.25);
	final double holidayY = (cellX * 0.75);

	//Initializes JPanel of the top bar (Mon-Sun Labels and Title)	
	TitleAndDays topBar= new TitleAndDays(cellX);
	
	/*
	 * Initialize a scrollable JPanel containing a grid of dates and holidays
	 *
	 *.setUnitIncrement speeds up the vertical scroll bar speed
	 * source: https://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
	 */
	
	GridDays allDays = new GridDays(dateY, holidayY,cellX); 
	JScrollPane scrollableDays = new JScrollPane(allDays); //Adds scrollbar features to the grid
	scrollableDays.getVerticalScrollBar().setUnitIncrement(16);
	
	//Set Layout and add the two JPanels to the JFrame
	this.setLayout(new BorderLayout());
	this.add(topBar, BorderLayout.NORTH);
	this.add(scrollableDays, BorderLayout.CENTER); 
	//Create dimension of the JFrame using the input Frame Size. A constant buffer is added to the X
	this.setPreferredSize(new Dimension(frameSize+48, frameSize));

	this.pack();
	this.setVisible(true);
	}
}