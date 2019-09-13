package CalcProject;
//Java proram to evaluate value of a postfix expression 

import java.util.Stack;

import javax.swing.JOptionPane; 

public class Postfix  
{ 
 // Method to evaluate value of a postfix expression 
 static double evaluate(String[] exp) 
 { 
     //create a stack 
     Stack<Double> stack=new Stack<>(); 
     
     // Scan all characters one by one 
     for(int i=0;i<exp.length;i++) 
     { 
    	 if (exp[i] != null) {
    		 String s = exp[i];
    		 double d = 0; 
             // If the scanned character is an operand (number here), 
             // push it to the stack. 
             if(isNumeric(s)) {
            	 d = Double.parseDouble(s);
            	 stack.push(d);
            	 System.out.println(d);
             }    
             //  If the scanned character is an operator, pop two 
             // elements from stack apply the operator 
             else
             { 
                 double val1 = stack.pop();
                 double val2 = stack.pop();
                   
                 switch(s) 
                 { 
                     case "+": 
                     stack.push(val2+val1); 
                     break; 
                       
                     case "\u2013": 
                     stack.push(val2- val1); 
                     break; 
                   
                     case "\u00F7": 
                    	 try {
                		 Verifier.verifyNum(val1);
                		 stack.push(val2/val1);
                        
                	 }
                	 catch(DivZero e) {
                		 JOptionPane.showMessageDialog(null, "You can't divide by 0 stoopid head!)","Insulting Calculator", JOptionPane.ERROR_MESSAGE);
                	 }
                     break;
                    	
                     
                  
                     case "*": 
                     stack.push(val2*val1); 
                     break; 
               } 
             } 
    	 }
         
     }
     if (!stack.empty())
    	 return stack.pop();
     else
    	 return 0;
 } 
 public static boolean isNumeric(String str)
 {
   return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
 }
}
