package calendar;

public class Main {
	public static void main(String[] args) {

		/* 
		* squareSize is used to set the X and Y size of the JFrame.
		* Recommended to be at least 1000
		* HD 1920 x 1080: Recommend 1000
		* UHD 3840 x 2160: Recommend 1500 
		*/
		
		GuiManager gui = new GuiManager(); 
		gui.resizeWeek();
	}
}
