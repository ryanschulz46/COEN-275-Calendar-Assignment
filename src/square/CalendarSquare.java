package square;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;

public abstract class CalendarSquare extends JPanel{
	
	
	public JList list;
	public DefaultListModel listModel;
	
	
	public CalendarSquare(Dimension cellSize){
		super();
		listModel = new DefaultListModel();
		list = new JList(listModel);
		this.add(list);
		this.setPreferredSize(cellSize);

	}
	
	public void changeSize(Dimension cellSize) {
		this.changeSize(cellSize);
		//this.setPrefferedSize(cellSize);
	}

	
}
