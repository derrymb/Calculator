package CalcProject;

import javax.swing.JOptionPane;

public class isEquation extends Exception {
	public isEquation(String e)
	{
		super(e);
		JOptionPane.showMessageDialog(null, "Thats not an valid equation doofus!)","Insulting Calculator", JOptionPane.ERROR_MESSAGE);
	}

}
