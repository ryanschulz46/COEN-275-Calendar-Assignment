package square;
import java.awt.Dimension;
import java.awt.Color;

//this square panel holds the date info on the grid
public class DateSquare extends CalendarSquare {
	
	public DateSquare(Dimension cellSize){
		super(cellSize);
		add(list);
		this.setBackground(Color.WHITE);
	}
}
