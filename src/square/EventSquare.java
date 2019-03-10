package square;

import java.awt.Color;
import java.awt.Dimension;


//this square holds the event info on the grid
public class EventSquare extends CalendarSquare{

	public EventSquare(Dimension cellSize) {
		super(cellSize);
		list.setBackground(Color.CYAN);
		this.setBackground(Color.CYAN);
		
	}

}
