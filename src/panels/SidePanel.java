package panels;

import java.awt.*;
import javax.swing.JPanel;
import calendar.GuiManager;
//side panel holds Radio buttons and the whole user interface for adding events

public class SidePanel extends JPanel{
	private ViewJRadio viewSelect;
	private GuiManager gui;
	private History history;

	public SidePanel(GuiManager gui) {
		this.gui = gui; //creates reference to gui manager to be passed to sub-panels
		
		this.setLayout(new BorderLayout());

		//create radio buttons to change view
		viewSelect = new ViewJRadio(gui);
		this.add(viewSelect, BorderLayout.NORTH);
		
		//create form to add events and history
		history = new History(gui);
		this.add(history, BorderLayout.CENTER);
	
		this.setPreferredSize(GuiManager.dimAll.sidePanelSize);
	}	
	
	
	//reload due to change in dimensions or add/remove items
	public void refresh() {
		history.revalidate();
		viewSelect.revalidate();
	}
}

