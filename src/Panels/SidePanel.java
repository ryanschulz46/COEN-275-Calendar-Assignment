package Panels;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DataManagers.DimensionManager;
import DataManagers.Node;
import calendar.GuiManager;

public class SidePanel extends JPanel{
	public ViewJRadio viewSelect;
	public GuiManager gui;
	public History history;

	public SidePanel(GuiManager gui) {
		this.gui = gui;
		this.setLayout(new BorderLayout());
		//this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		viewSelect = new ViewJRadio(gui);
		this.add(viewSelect, BorderLayout.NORTH);

		history = new History(gui);
		this.add(history, BorderLayout.CENTER);
				
		
		

		this.setBackground(Color.BLACK);
		this.setPreferredSize(GuiManager.dimAll.sidePanelSize);
	}	
	


}

