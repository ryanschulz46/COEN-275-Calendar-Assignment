package Panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import calendar.DimensionManager;
import calendar.GuiManager;

public class SidePanel extends JPanel implements ActionListener{

	public JRadioButton year;
	public JRadioButton week;
	public JRadioButton day;
	public ButtonGroup viewSelector;
	public DimensionManager dim;
	public GuiManager gui;
	
	public SidePanel(DimensionManager dim, GuiManager gui) {
		this.dim = dim;
		this.gui = gui;
		year = new JRadioButton("Year");
		week = new JRadioButton("Week");
		day = new JRadioButton("Day");
		
		year.setActionCommand("year");
		week.setActionCommand("week");
		day.setActionCommand("day");
		
		viewSelector = new ButtonGroup();
		viewSelector.add(year);
		viewSelector.add(week);
		viewSelector.add(day);
		
		year.addActionListener(this);
		week.addActionListener(this);
		day.addActionListener(this);
		

		this.add(year);
		this.add(week);
		this.add(day);
		
		
		

		this.setBackground(Color.BLACK);
		this.setPreferredSize(dim.sidePanelSize);
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

