package DataManagers;

import java.util.HashMap;
import java.util.Map;

public class ColorManager {
	
	public static Map<String, String> colorMap;
	

	public ColorManager() {


		colorMap = new HashMap<String, String>();
		colorMap.put("Red","<font color='#FF0000'>");
		colorMap.put("Yellow","<font color='#FFFF00'>");
		colorMap.put("Green","<font color='#008000'>");
		colorMap.put("White","<font color='#FFFFFF'>");
		colorMap.put("Black","<font color='#000000'>");
		
	}


	
	public String getColor(String input) {
		return colorMap.get(input);
	}


	
}
