package panels;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calendar.GuiManager;
import datamanagers.ColorManager;
import datamanagers.Node;

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
	private JButton btnLarger;
	private JButton btnSmaller;
	private JButton btnDeleteSelectedEvent;
	private JScrollPane historyListScroll;
	private int month;
	private int year;
	private int day;
	
	/*
	 * This whole panel contains the whole user interface for users to add/remove events on the calendar
	 */
	
	
	public History(GuiManager gui) {
		//reference to gui that this is called from
		this.gui = gui;
		//Absolute layout
		setLayout(null);
		
		
		//Labels for side panel
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(37, 26, 121, 38);
		
		JLabel lblEventDate = new JLabel("Event Date");
		lblEventDate.setBounds(37, 122, 140, 16);
		
		JLabel lblEventHistory = new JLabel("Event History");
		lblEventHistory.setBounds(44, 218, 133, 16);
		
		JLabel lblEventTextColor = new JLabel("Event Text Color:");
		lblEventTextColor.setBounds(230, 37, 120, 27);
		

		//Enter event name textbox
		eventTextField = new JTextField();
		eventTextField.setBounds(37, 74, 116, 22);
		eventTextField.setColumns(10);
		
		
		//Date textbox
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		dateTextField = new JFormattedTextField(format);
		dateTextField.setBounds(37, 151, 116, 22);	
		dateTextField.setColumns(10);
		
		
		//create text color selection box
		String[] colorList = {"Black", "Red", "Yellow", "Green", "White"}; //color options
		colorOptions = new ColorManager(); //maps color options to color hex code
		colorBox = new JComboBox(colorList);
		colorBox.setSelectedIndex(0); //default black
		colorBox.setBounds(240, 74, 134, 22);
		
		
		//create history list
		listModel = new DefaultListModel();
		historyList = new JList(listModel);
		historyListScroll = new JScrollPane(historyList);
		historyListScroll.setBounds(37, 247, 325, (int) (GuiManager.dimAll.getCalendarY() - 200)-247);
		
		
		// create and place all buttons
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.setBounds(253, 150, 97, 25);
		
		btnDeleteSelectedEvent = new JButton("Delete Selected Event");
		btnDeleteSelectedEvent.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 175), 178, 25);
		
		btnSmaller = new JButton("Decrease Size");
		btnSmaller.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 25), 178, 25);
		
		btnLarger = new JButton("Increase Size");
		btnLarger.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 75), 178, 25);


/*
 * ActionListeners
 */
		
		
		//adds event from info in text box and date
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eventString = eventTextField.getText();
				String dateString = dateTextField.getText();
				dateString = dateValidator(dateString);
				if (dateString == "error"){
					/*
					 * error detected in function, error message already displayed
					 * return so we do not add event or create the node
					 */
					return;
				}
				int i = getiValue(month,day,year);
				String colorHex = colorOptions.getColor((String) colorBox.getSelectedItem());
				Node newEvent = new Node(dateString, eventString, i, colorHex);
				
				
				addNode(newEvent);
			}
		});
		
		//deletes event selected in history
		btnDeleteSelectedEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = historyList.getSelectedIndex();
					if(selectedIndex != -1) {
						removeNode(selectedIndex);
					}
			}
			
		});
		
		//decreases the size of the GUI when btnSmaller is pressed

		btnLarger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiManager.dimAll.changeSize(50);
				refreshElementLocations();
			}
			
		});
		
		//increases the size of the GUI when btnLarger is pressed
		btnSmaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiManager.dimAll.changeSize(-50);
				refreshElementLocations();
			}
			
		});
		
/*
 * Add everything to the panel now
 */
		
		add(lblEventName);
		add(lblEventDate);
		add(lblEventHistory);
		add(lblEventTextColor);
		
		add(eventTextField);
		add(dateTextField);
		add(colorBox);
		
		add(historyListScroll);
		
		add(btnAddEvent);
		add(btnDeleteSelectedEvent);
		add(btnSmaller);
		add(btnLarger);
	}
	
	
	//this function dynamically moves/changes size of items as you change GUI size
	public void refreshElementLocations() {
		historyListScroll.setBounds(37, 247, 325, (int) (GuiManager.dimAll.getCalendarY() - 200)-247);
		btnSmaller.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 25), 178, 25);
		btnLarger.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 75), 178, 25);
		btnDeleteSelectedEvent.setBounds(102, (int) (GuiManager.dimAll.getCalendarY() - 175), 178, 25);
	}
	

	
	/*
	 * This first gets the node from the history list
	 * then, removes the event from the grid
	 * finally removes the node from the history list
	 */
	public void removeNode(int index) {
		Node toRemove = (Node) listModel.getElementAt(index);
		gui.removeNode(toRemove);
		listModel.remove(index);
		this.revalidate();
		
	}
	
	
	/*
	 * Gui.addNode() tries to add it to the grid
	 * If it cannot add it to the grid because of a duplicate, do not add it to the history list 
	 */
	public void addNode(Node add) {
		if(!gui.addNode(add)) {
			return;
		}
		listModel.addElement(add);
		this.revalidate();

	}
	
	
	
	
	/*
	 * This checks to make sure a valid date is entered, and that a date is actually entered
	 * If not, it stops the add process and displays an error
	 */
	public String dateValidator(String checkString) {
		try {
		month = Integer.parseInt(checkString.substring(0, 2));
		day = Integer.parseInt(checkString.substring(3, 5));
		year = Integer.parseInt(checkString.substring(6, 10));
		}
		catch(Exception e) {
			//if no date is entered it will stop the process
			gui.displayError("No date entered.");
			return ("error");
		
		}
		//checks to make sure the date is in 2019 or is Dec 30/31st of 2018
		if (year == 2018 && month == 12 && (day == 30 || day == 31) || (year == 2019 )) {
			return checkString;
		}
		else {
			//stop process if it is out of those bounds
			gui.displayError("Error: Date is not a valid value for this 2019 calendar.");
			return("error");
		}
	}
	
	
	
	
	/*
	 * This loops through getting the index of what grid number the event is to be added to
	 */
	
	public int getiValue(int m, int d, int y) {
		
		if (y == 2018 && m == 12 && (d == 30 || d == 31)) {
			if(d == 30) return 0;
			else return 1;
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
		/*
		 * counter is always incremented at end of loop for next loop
		 * must decriment counter if object is at the end
		 */
		return --counter;
		
	}
}
