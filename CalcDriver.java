package CalcProject;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CalcDriver {
public static void main(String[] args) {
		
		//Create a new frame for the calculator
		JFrame myFrame = new JFrame("Calculator");
		//Provides a way to close the application
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Does not allow my beautiful creation to be deformed by being resized!
		myFrame.setResizable(false);
		//Makes the frame have a borderLayout
		myFrame.setLayout(new BorderLayout());
		//Uses the center panel for the calculator
		myFrame.add(new CalcProjectV2(), BorderLayout.CENTER);
		//Wraps elements to fit the screen
		myFrame.pack();
		//Opens the application in the center of the screen
		myFrame.setLocationRelativeTo(null);
		//Makes the Frame visible
		myFrame.setVisible(true);

	}//main
}//end class
