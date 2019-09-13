package CalcProject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Split {

	
	
	public static String[] StringArray(String str) {
		
		int count = 0;
		// regex for finding the division symbol, my custom subtraction symbol and the other operators plus and negative or positive digits decimal points and all
	    String regex = "(\u00F7)|(\u2013)|(-\\d+\\.\\d+)|(\\d+\\.\\d+)|(\\d+)|(-\\d+)|([+-/*///^])|([/(/)])";
		//String regex = "[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?|[-^+*/\\u00F7()]|\\w+"; this was another possibility but i did not like it as much
	    Matcher m = Pattern.compile(regex).matcher(str);

	    // make a new array called list
	    String[] list = new String[50];

	    //runs the String through a regex matcher and once a match is found store it in the array, add to count and repeat
	    while (m.find()) {
	        list[count] = m.group();
	        count++;
	    }
	    // this was just for me to print out the results in the console to see how it was doing
	    // added the if(i != null) to ignore the empty sections of the array
	    for(String i : list) {
			if (i != null) {
				   System.out.println(i);
			}
     }
		return list;
	}
}
