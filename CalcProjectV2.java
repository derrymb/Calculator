package CalcProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CalcProjectV2 extends JPanel implements ActionListener{
	
	
	//variables
	private static final int WIDTH = 480;
	private static final int HEIGHT = 640;
	
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	private String[] test = new String[50];
	
	private JButton[] numberButtons;
	private JButton[] operatorButtons;
	
	private JTextField text;
	
	private String memory = "";
	
	// [0] = gridx, [1] = gridy, [2] = gridwidth, [3] = gridheight
	private int [][] numConstraints = new int[][] {
		{1, 7, 1, 1},//zero
		{0, 6, 1, 1},//one
		{1, 6, 1, 1},//two
		{2, 6, 1, 1},//three
		{0, 5, 1, 1},//four
		{1, 5, 1, 1},//five
		{2, 5, 1, 1},//six
		{0, 4, 1, 1},//seven
		{1, 4, 1, 1},//eight
		{2, 4, 1, 1},//nine
	};
	
	private int [][] operatorConstraints = new int [][]	{
		{2, 7, 2, 1}, //equals
		{3, 5, 1, 2}, //plus
		{3, 4, 1, 1}, //minus
		{3, 3, 1, 1}, //multiply
		{3, 2, 1, 1}, //divide
		{0, 1, 2, 1}, //clearscreen
		{2, 2, 1, 1}, //period
		{0, 7, 1, 1}, //negative
		{0, 2, 1, 1}, //left bracket
		{1, 2, 1, 1}, //right bracket
		{2, 1, 2, 1}, //backspace
		{0, 3, 1, 1}, //MSave
		{1, 3, 1, 1}, //MClear
		{2, 3, 1, 1}, //MRecall
		
	};
	
	//constructor
	public CalcProjectV2() {
		super();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		layout = new GridBagLayout();
		layout.columnWidths = new int[] {120,120,120,120};
		layout.rowHeights = new int[] {80,80,80,80,80,80,80,80};
		setLayout(layout);
		
		constraints = new GridBagConstraints();
		
		// number buttons setup
		numberButtons = new JButton[10];
		
		for(int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton("" + i);
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(numberButtons[i].getFont().deriveFont(35f));
			numberButtons[i].setBackground(Color.YELLOW);
			constraints.gridx = numConstraints[i][0];
			constraints.gridy = numConstraints[i][1];
			constraints.gridwidth = numConstraints[i][2];
			constraints.gridheight = numConstraints[i][3];
			constraints.fill = GridBagConstraints.BOTH;
			constraints.insets = new Insets(1,1,1,1);
			
			add(numberButtons[i], constraints);
		}
		// operator buttons setup
		operatorButtons = new JButton[14];
		
		operatorButtons[0] = new JButton("=");
		operatorButtons[1] = new JButton("+");
		operatorButtons[2] = new JButton("\u2013");
		operatorButtons[3] = new JButton("*");
		operatorButtons[4] = new JButton("\u00F7");
		operatorButtons[5] = new JButton("cls");
		operatorButtons[6] = new JButton(".");
		operatorButtons[7] = new JButton("+/-");
		operatorButtons[8] = new JButton("(");
		operatorButtons[9] = new JButton(")");
		operatorButtons[10] = new JButton("del");
		operatorButtons[11] = new JButton("MSave");
		operatorButtons[12] = new JButton("MClear");
		operatorButtons[13] = new JButton("MRecall");
		
		operatorButtons[0].setBackground(Color.CYAN);
		operatorButtons[7].setBackground(Color.MAGENTA);
		operatorButtons[6].setFont(operatorButtons[6].getFont().deriveFont(35f));
		
		for(int i = 0; i < operatorButtons.length; i++) {
			
			operatorButtons[i].addActionListener(this);
			constraints.gridx = operatorConstraints[i][0];
			constraints.gridy = operatorConstraints[i][1];
			constraints.gridwidth = operatorConstraints[i][2];
			constraints.gridheight = operatorConstraints[i][3];
			constraints.fill = GridBagConstraints.BOTH;
			operatorButtons[i].setFont(operatorButtons[i].getFont().deriveFont(25f));
			constraints.insets = new Insets(1,1,1,1);
			
			add(operatorButtons[i], constraints);
		}
		// Text field setup
		text = new JTextField();
		text.setText("0");
		text.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		text.setHorizontalAlignment(JTextField.TRAILING);
		text.setFont(new Font("Arial", Font.BOLD, 24));
		text.setEditable(false);
		//Start at the very top left
		constraints.gridx = 0;
		constraints.gridy = 0;
		//spans all colounms
		constraints.gridwidth = 4;
		//normal height
		constraints.gridheight = 1;
		
		add(text, constraints);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < numberButtons.length; i++) {
			if(e.getSource() == numberButtons[i]) {
				text.setText(text.getText() + i);
			}
		}
		//conditions to add a decimal point
		if(e.getSource() == operatorButtons[6]) {
			text.setText(text.getText() + ".");
		}
		//clear screen(set text field to blank string)
		if(e.getSource() == operatorButtons[5]) {
			text.setText("0");
		}
		//convert to negative/positive
		if(e.getSource() == operatorButtons[7]) {
			//text.setText("" + (-1 * Integer.parseInt(text.getText())));
			text.setText(text.getText() + "-");
		}
		if(e.getSource() == operatorButtons[10] && text.getText().length() > 0) {
			text.setText(text.getText().substring(0, text.getText().length()-1) );
		}
		if(e.getSource() == operatorButtons[0]) {
			try {
				test = InfixToPostfix.infixToPostfix(Split.StringArray(text.getText()));
				text.setText("" + Postfix.evaluate(test));
			}
			catch(EmptyStackException x){
				JOptionPane.showMessageDialog(null, "Thats not a valid equation Doofus!)","Insulting Calculator", JOptionPane.ERROR_MESSAGE);
				text.setText("0");
			}
			 /*for(String i : test) {
					if (i != null) {
						   System.out.println(i);
					}
		     }*/
			 
			//Split.StringArray(text.getText());
			//text.setText(text.getText() + "=");  // do not put = symbol in there!!!!!!
		}
		if(e.getSource() == operatorButtons[1]) {
			text.setText(text.getText() + "+");
		}
		if(e.getSource() == operatorButtons[2]) {
			text.setText(text.getText() + "\u2013");
		}
		if(e.getSource() == operatorButtons[3]) {
			text.setText(text.getText() + "*");
		}
		if(e.getSource() == operatorButtons[4]) {
			text.setText(text.getText() + "\u00F7");
		}
		if(e.getSource() == operatorButtons[8]) {
			text.setText(text.getText() + "(");
		}
		if(e.getSource() == operatorButtons[9]) {
			text.setText(text.getText() + ")");
		}
		if(e.getSource() == operatorButtons[11]) {
			memory = "" + Double.valueOf(text.getText());
		}
		if(e.getSource() == operatorButtons[12]) {
			memory = "";
		}
		if(e.getSource() == operatorButtons[13]) {
			text.setText(text.getText() + memory);
		}
	}

}//class
