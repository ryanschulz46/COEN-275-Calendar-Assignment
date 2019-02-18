package calendar;
import java.awt.*;
import javax.swing.*;

public class TitleAndDays extends JPanel {
	TitleAndDays(double CellX)
	{

		//Create dimension using passed through value to size this JPanel
		Dimension cellDimension = new Dimension();
		cellDimension.setSize(CellX, 100.00);
		
		this.setLayout(new GridLayout(1,7));

		/*
		 * Add SCU Logo to its own JPanel
		 * 
		 * To add the picture into a JLabel, I used the following site for help:
		 * https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
		 * 
		 * SCU Logo was found from:
		 * https://www.logolynx.com/images/logolynx/84/84318b8a8ace2c7b1e84a65c2c929476.png
		 */
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/sculogo.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		JLabel label = new JLabel(imageIcon);
		
		JLabel calTitle = new JLabel("2019");
		calTitle.setForeground(Color.WHITE);
	
		//Combines both Logo and 2019 Title JPanel into a new JPanel
		JPanel title = new JPanel();
		title.setLayout(new FlowLayout());
		title.setBackground(Color.BLACK);
		title.setForeground(Color.WHITE);
		title.add(label);
		title.add(calTitle);
		
		/*
		 * Initialize the JLabels for each day
		 * Create daysCell, which are JPanels that the JLabels will be added to
		 */
		
		JPanel[] daysCell = new JPanel[7];
		JLabel[] daysLabel = new JLabel[7];
		daysLabel[0] = new JLabel("Sunday", SwingConstants.CENTER);
		daysLabel[1]= new JLabel("Monday", SwingConstants.CENTER);
		daysLabel[2] = new JLabel("Tuesday", SwingConstants.CENTER);
		daysLabel[3] = new JLabel("Wednesday", SwingConstants.CENTER);
		daysLabel[4] = new JLabel("Thursday", SwingConstants.CENTER);
		daysLabel[5] = new JLabel("Friday", SwingConstants.CENTER);
		daysLabel[6] = new JLabel("Saturday", SwingConstants.CENTER);
		
		//Initialize each Panel for the day Label, set the colors, add the Day JLabel
		for (int i = 0; i < 7; i++) {
			daysCell[i] = new JPanel();
			daysCell[i].setLayout(new BorderLayout());
			daysCell[i].setPreferredSize(cellDimension);
			daysLabel[i].setForeground(Color.WHITE);
			daysCell[i].setBackground(Color.BLACK);
			
			/*
			 * If statement checks if Wednesday is being added
			 * If so, adds the SCU Logo and 2019 Title above it
			 * Wednesday was chosen since it is centered in the grid
			 */
			
			if (i == 3) {
				daysCell[3].add(title,BorderLayout.NORTH);
			}
			daysCell[i].add(daysLabel[i],BorderLayout.SOUTH);
			//Adds each array incrementally to the grid
			this.add(daysCell[i]);
		}
	}

}