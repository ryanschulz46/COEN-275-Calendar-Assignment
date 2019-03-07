package Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataManagers.Node;
import calendar.GuiManager;

public class History extends JPanel{
	
	
	
	public GuiManager gui;
	
	public JTextField eventTextField;
	public JScrollPane eventScroll;
	public JFormattedTextField dateTextField;
	
	public JButton addButton;
	
	public JList historyList;
	public JScrollPane historyListScroll;
	public DefaultListModel listModel; 
	
	public JButton deleteButton;
	

	public History(GuiManager gui) {
		this.gui = gui;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		//initilize textfield
		eventTextField = new JTextField("Enter event name");
		eventTextField.setSize(GuiManager.dimAll.inputEvent);
		eventScroll = new JScrollPane(eventTextField);
		
		//initilize date field
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		dateTextField = new JFormattedTextField(format);
		dateTextField.setSize(GuiManager.dimAll.inputDate);
		
		//initialize list
		listModel = new DefaultListModel();
		historyList = new JList(listModel);
		historyList.setSize(GuiManager.dimAll.history);
		historyListScroll = new JScrollPane(historyList);
		
		
		//initialize add button
		
		addButton = new JButton("Add");
		addButton.setSize(GuiManager.dimAll.button);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventString = eventTextField.getText();
				
				String dateString = dateTextField.getText();
				int m = Integer.parseInt(dateString.substring(0, 1));
				int d = Integer.parseInt(dateString.substring(3, 4));
				int i = getiValue(m,d);
				Node newEvent = new Node(dateString, eventString, i);
				addNode(newEvent);
			}
		});

		
		//initialize delete button
		deleteButton = new JButton("Delete");
		deleteButton.setSize(GuiManager.dimAll.button);
		
		this.add(eventScroll);
		this.add(dateTextField);
		this.add(addButton);
		this.add(historyListScroll);
		
	}	
		
		

	
	public void addNode(Node add) {
		listModel.addElement(add);
		this.revalidate();
		gui.addNode(add);
	}
	
	public int getiValue(int m, int d) {
		int counter = 2;
		int search;
		int key = 100*(m+1)+(d+1);
		for (int i = 0; i < 12; i++) {  //
			for (int j = 0 ; j < GridCal.monthData[i].getDays(); j++) {
				search = 100*(i+1)+(j+1);
				if (search == key){
					return counter;
				}
				counter++;
			}
		}
		return --counter;
		
	}




	

}
