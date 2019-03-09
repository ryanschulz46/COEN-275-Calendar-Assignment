package square;

import java.awt.Color;
import java.awt.Dimension;

public class EventSquare extends CalendarSquare{

	public EventSquare(Dimension cellSize) {
		super(cellSize);
		list.setBackground(Color.CYAN);
		this.setBackground(Color.CYAN);
		
	}

}
