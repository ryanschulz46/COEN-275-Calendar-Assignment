package square;

import javax.swing.*;
import java.awt.*;

public abstract class CalendarSquare extends JPanel{
	/*
	 * super class for dateSquare and EventSquare
	 * Jlist is where all text will be added for either the grid date or grid events for each actual calendar day
	 */
	
	
	//public as many classes access it
	public JList list;
	public DefaultListModel listModel;
	
	
	public CalendarSquare(Dimension cellSize){
		super();
		listModel = new DefaultListModel();
		list = new JList(listModel);
		add(list);
		this.setPreferredSize(cellSize);

	}
	
	
	public void changeSize(Dimension cellSize) {
		this.changeSize(cellSize);
	}

	
}
