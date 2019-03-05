package Panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import calendar.DimensionManager;

public class SidePanel extends JPanel{

	public Dimension panelSize;
	
	public SidePanel(DimensionManager dim) {
		this.setBackground(Color.BLACK);
		this.setPreferredSize(dim.sidePanelSize);
	}

}
