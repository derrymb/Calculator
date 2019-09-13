package CalcProject;

import javax.swing.JOptionPane;

public class Verifier {
	static String e = "You can't divide by 0 stoopid head!)";
	
		public static void verifyNum(double val1) throws DivZero{
			if(val1 == 0) throw new DivZero(e);
		}
		public static void verifyEquation(double val1) throws DivZero{
			
		}

	}//end class


