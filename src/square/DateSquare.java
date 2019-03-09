package square;
import java.awt.Dimension;
import java.awt.Color;

public class DateSquare extends CalendarSquare {
	
	public DateSquare(Dimension cellSize){
		super(cellSize);
		add(list);
		this.setBackground(Color.WHITE);
	}
}
