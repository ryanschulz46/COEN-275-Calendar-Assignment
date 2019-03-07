package Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import calendar.GuiManager;

public class ViewJRadio extends JPanel implements ActionListener{

	
	public JRadioButton year;
	public JRadioButton week;
	public JRadioButton day;
	public ButtonGroup viewSelector;
	public GuiManager gui;
	

	public ViewJRadio(GuiManager gui) {
		this.gui = gui;
		
		year = new JRadioButton("Year");
		week = new JRadioButton("Week");
		day = new JRadioButton("Day");
		
		year.setActionCommand("year");
		week.setActionCommand("week");
		day.setActionCommand("day");
	
		year.setSelected(true);
		//year.setForeground(Color.WHITE);
		//year.setBackground(Color.BLACK);
		
		
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
