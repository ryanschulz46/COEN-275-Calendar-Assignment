package DataManagers;

import java.sql.Date;

public class Node {

	public String event;
	public String date;
	public int location;
	public String colorHex;
	
	
	public Node(String d, String e, int i, String c) {
		// TODO Auto-generated constructor stub
		
		date = d;
		event = e;
		location = i;
		colorHex = c;
	}
	
	public String getColoredEventName() {
		return ("<html>" + this.colorHex +this.event+"</font></html>");
	}
	
	public String toString() {
		return (""+date+" "+this.event);
	}

}
