package datamanagers;

public class Node {

	private String event;
	private String date; 
	private int location; //index in grid that the date is at
	private String colorHex; //that the user selects for this event
	
	
	
	public Node(String d, String e, int i, String c) {
		date = d;
		event = e;
		location = i;
		colorHex = c;
	}
	
	// returns string with HTML color code to be added to grid
	public String getColoredEventName() {
		return ("<html>" + this.colorHex +this.event+"</font></html>");
	}
	
	//override toString for string to be added to history list
	@Override
	public String toString() {
		return (""+date+" "+this.event);
	}
	
	
	//returns index of grid that node is
	public int getLocation() {
		return location;
	}

}
