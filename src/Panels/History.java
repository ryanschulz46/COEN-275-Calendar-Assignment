package Panels;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataManagers.ColorManager;
import DataManagers.Node;
import calendar.GuiManager;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class History extends JPanel{
	private JTextField eventTextField;
	private JFormattedTextField dateTextField;
	private JComboBox<String> colorBox;
	private ColorManager colorOptions;
	private DefaultListModel listModel;
	private JList historyList;
	private GuiManager gui;
	private int month;
	private int year;
	private int day;
	
	public History(GuiManager gui) {
		this.gui = gui;
		setLayout(null);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(37, 26, 121, 38);
		
		add(lblEventName);
		
		eventTextField = new JTextField();
		eventTextField.setBounds(37, 74, 116, 22);
		add(eventTextField);
		eventTextField.setColumns(10);
		
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		dateTextField = new JFormattedTextField(format);
		dateTextField.setSize(GuiManager.dimAll.inputDate);
		dateTextField.setBounds(37, 151, 116, 22);
		add(dateTextField);
		dateTextField.setColumns(10);
		
		
		
		JLabel lblEventDate = new JLabel("Event Date");
		lblEventDate.setBounds(37, 122, 140, 16);
		add(lblEventDate);
		
		
		
		
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventString = eventTextField.getText();
				String dateString = dateTextField.getText();
				dateString = dateValidator(dateString);
				if (dateString == "error") return;
				int i = getiValue(month,day,year);
				String colorHex = colorOptions.getColor((String) colorBox.getSelectedItem());
				Node newEvent = new Node(dateString, eventString, i, colorHex);
				
				
				addNode(newEvent);
			}
		});
		btnAddEvent.setBounds(253, 150, 97, 25);
		add(btnAddEvent);
		
		
		
		
		String[] colorList = {"Red", "Yellow", "Green", "White", "Black"};
		colorOptions = new ColorManager();
		colorBox = new JComboBox(colorList);
		colorBox.setSelectedIndex(4);
		colorBox.setBounds(240, 74, 134, 22);
		add(colorBox);
		
		JLabel lblEventTextColor = new JLabel("Event Text Color:");
		lblEventTextColor.setBounds(230, 37, 120, 27);
		add(lblEventTextColor);
		
		
		
		
//		JList list = new JList();
//		list.setBounds(37, 247, 325, 371);
//		add(list);
		listModel = new DefaultListModel();
		historyList = new JList(listModel);
		historyList.setSize(GuiManager.dimAll.history);
		JScrollPane historyListScroll = new JScrollPane(historyList);
		historyListScroll.setBounds(37, 247, 325, 171);
		add(historyListScroll);
		
		
		
		
		JLabel lblEventHistory = new JLabel("Event History");
		lblEventHistory.setBounds(44, 218, 133, 16);
		add(lblEventHistory);
		
		
		
		JButton btnDeleteSelectedEvent = new JButton("Delete Selected Event");
		btnDeleteSelectedEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = historyList.getSelectedIndex();
					if(selectedIndex != -1) {
						removeNode(selectedIndex);
					}
			}
			
		});
		btnDeleteSelectedEvent.setBounds(102, 441, 178, 25);
		
		add(btnDeleteSelectedEvent);
		
		
		JButton btnLarger = new JButton("Increase Size");
		btnLarger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiManager.dimAll.changeSize(50);
			}
			
		});
		btnLarger.setBounds(102, 650, 178, 25);
		add(btnLarger);
		
		
		JButton btnSmaller = new JButton("Decrease Size");
		btnSmaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiManager.dimAll.changeSize(-50);
			}
			
		});
		btnSmaller.setBounds(102, 700, 178, 25);
		add(btnSmaller);
	}
	
	
	
	
	public void removeNode(int index) {
		Node toRemove = (Node) listModel.getElementAt(index);
		gui.removeNode(toRemove);
		listModel.remove(index);
		this.revalidate();
		
	}
	
	public void addNode(Node add) {
		if(!gui.addNode(add)) {
			return;
		}
		listModel.addElement(add);
		this.revalidate();

	}
	
	
	
	
	
	public String dateValidator(String checkString) {
		
		try {
		month = Integer.parseInt(checkString.substring(0, 2));
		day = Integer.parseInt(checkString.substring(3, 5));
		year = Integer.parseInt(checkString.substring(6, 10));
		}
		catch(Exception e) {
			gui.displayError("No date entered.");
			return ("error");
		
		}

		if (year == 2018 && month == 12 && (day == 30 || day == 31) || (year == 2019 )) {
			return checkString;
		}
		else {
			gui.displayError("Error: Date is not a valid value for this 2019 calendar.");
			return("error");
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
