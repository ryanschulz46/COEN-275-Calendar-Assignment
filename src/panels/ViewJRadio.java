package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import calendar.GuiManager;

/*
 * this class makes the radio buttons to be pressed to change the view on the calendar
 * it calls on the resizing functions in GuiManager that is passed to it
 */
public class ViewJRadio extends JPanel implements ActionListener{

	
	private JRadioButton year;
	private JRadioButton week;
	private JRadioButton day;
	private ButtonGroup viewSelector;
	private GuiManager gui;
	

	public ViewJRadio(GuiManager gui) {
		this.gui = gui; //gui for references for actionPerformed();
		
		year = new JRadioButton("Year");
		week = new JRadioButton("Week");
		day = new JRadioButton("Day");
		
		
		//used to differentiate what button is pressed in one actionPerformed function
		year.setActionCommand("year");
		week.setActionCommand("week");
		day.setActionCommand("day");
	
		year.setSelected(true);

		
		
		viewSelector = new ButtonGroup();
		viewSelector.add(year);
		viewSelector.add(week);
		viewSelector.add(day);
	
		
		year.addActionListener(this);
		week.addActionListener(this);
		day.addActionListener(this);
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
	
		this.add(year);
		this.add(week);
		this.add(day);
	}

	
	//calls the right resizing function in GuiManager depending on what radio button is pressed
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "year") {
			gui.resizeYear();
		}
		else if(e.getActionCommand() == "week") {
			gui.resizeWeek();
		}
		else if(e.getActionCommand() == "day") {
			gui.resizeDay();
		}
	}
	
}
