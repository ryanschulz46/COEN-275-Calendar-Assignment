package DataManagers;

import java.sql.Date;

public class Node {

	public String event;
	public String date;
	public int location;
	
	
	public Node(String d, String e, int i) {
		// TODO Auto-generated constructor stub
		
		date = d;
		event = e;
		location = i;
	}
	
	
	public String toString() {
		return (""+date+" "+this.event+" "+ location);
	}

}
