package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataManagers.ColorManager;
import DataManagers.Node;
import calendar.GuiManager;

public class History extends JPanel{
	
	
	
	public GuiManager gui;
	
	public JTextField eventTextField;
	public JScrollPane eventScroll;
	public JFormattedTextField dateTextField;
	public JComboBox<String> colorBox;
	public ColorManager colorOptions;
	
	public JButton addButton;
	
	public JList historyList;
	public JScrollPane historyListScroll;
	public DefaultListModel listModel;
	
	public int month;
	public int day;
	public int year;
	
	public JButton deleteButton;
	

	public History(GuiManager gui) {
		this.gui = gui;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		//initialize textfield
		eventTextField = new JTextField("Enter event name");
		eventTextField.setSize(GuiManager.dimAll.inputEvent);
		eventScroll = new JScrollPane(eventTextField);
		
		//initialize date field
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		dateTextField = new JFormattedTextField(format);
		dateTextField.setPreferredSize(GuiManager.dimAll.inputDate);
		
		//initialize color list
		String[] colorList = {"Red", "Yellow", "Green", "White", "Black"};
		colorBox = new JComboBox<String>(colorList);
		colorBox.setPreferredSize(GuiManager.dimAll.inputDate);
		colorBox.setSelectedIndex(4);
		colorOptions = new ColorManager();
		
		//initialize history list
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
				dateString = dateValidator(dateString);
				int i = getiValue(month,day,year);
				String colorHex = colorOptions.getColor((String) colorBox.getSelectedItem());
				Node newEvent = new Node(dateString, eventString, i, colorHex);
				addNode(newEvent);
			}
		});

		
		//initialize delete button
		deleteButton = new JButton("Delete");
		deleteButton.setSize(GuiManager.dimAll.button);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = historyList.getSelectedIndex();
					if(selectedIndex != -1) {
						removeNode(selectedIndex);
					}
			}
			
		});
		
		this.add(eventScroll);
		this.add(dateTextField);
		this.add(colorBox);
		this.add(addButton);
		this.add(historyListScroll);
		this.add(deleteButton);
		this.setSize(new Dimension(300,500));
	}	
		
		
	public void removeNode(int index) {
		Node toRemove = (Node) listModel.getElementAt(index);
		gui.removeNode(toRemove);
		listModel.remove(index);
		this.revalidate();
		
	}
	
	public void addNode(Node add) {
		listModel.addElement(add);
		this.revalidate();
		gui.addNode(add);
	}
	
	
	
	
	
	public String dateValidator(String checkString) {
		
		month = Integer.parseInt(checkString.substring(0, 2));
		day = Integer.parseInt(checkString.substring(3, 5));
		year = Integer.parseInt(checkString.substring(6, 10));

		if (year == 2018 && month == 12 && (day == 30 || day == 31) || (year == 2019 )) {
			return checkString;
		}
		else {
			year = 2019;
			gui.displayError("Error: Date is not a valid value for this 2019 calendar. Changing year to 2019");
			return (checkString.substring(0,6) + 2019);
		}
	}
	
	
	
	
	
	
	public int getiValue(int m, int d, int y) {
		
		if (y == 2018 && m == 12 && (d == 30 || d == 31)) {
			if(d == 30) return 0;
			else return 1;
		}
		
		if(y != 2019) {
			y = 2019;
			
		}
		
		
		int counter = 2;
		int search;
		int key = 100*(m)+(d);
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
