package CalcProject;
/* Java implementation to convert infix expression to postfix*/
//Note that here we use Stack class for Stack operations 

import java.util.Stack; 

class InfixToPostfix 
{ 
 // A utility function to return precedence of a given operator 
 // Higher returned value means higher precedence 
 public static int Prec(String s) 
 { 
     switch (s) 
     { 
     case "+": 
     case "\u2013": 
         return 1; 
    
     case "*": 
     case "\u00F7": 
         return 2;
     case "(":
    	 return -2;
     case ")":
    	 return -3;
     } 
     return -1; 
 } 
    
 // The main method that converts given infix expression 
 // to postfix expression.  
 public static String[] infixToPostfix(String[] exp) 
 { 
     // initializing empty String for result 
     String[] result = new String[50];
     
     int count = 0 ; 
     // initializing empty stack 
     Stack<String> stack = new Stack<>(); 
     
    //loop for the length of the array
     for (int i = 0; i<exp.length; ++i) 
     { 
    	 // but only carry out actions while there is an element in the array
    	 if (exp[i] != null) {
    		 // take the element in the array position i and store it in variable s(String)
    		 String s = exp[i]; 
    		 
    		 // if its a number write it to the array(do this by checking precedence)
    		 if(Prec(s) == -1) {
    			 result[count] = s;
    			 count++;
    		 }
    		 // opening bracket push to stack
    		 else if(Prec(s) == -2) {
    			 stack.push(s);
    		 }
    		 // if its a closing bracket pop the stack into the array until a "(" is found.
    		 //also do nothing with the ")", do not put it anywhere
    		 else if(Prec(s) == -3) {
    			 while(Prec(stack.peek()) != -2) {
    				 result[count] = stack.pop();
    				 count++;
    			 }
    			 // when open bracket is found pop it off the stack and save it no where
    			 if(Prec(stack.peek()) == -2) {
    				 stack.pop();
    			 }
    			
    		 }
    		 // if the stack is empty or the element on the stack has a lower precedence to s, push s to the stack
    		 else if(stack.isEmpty() || Prec(s) > Prec(stack.peek())) {
    			 stack.push(s);
    		 }
    		 else {
    			 // in the end if the precedence of the element in the stack is greater than s, pop everything from the stack to the array until
    			 // the stack is empty or s is greater precedence to whats on the stack
    			 while (!stack.isEmpty() && Prec(stack.peek()) >= Prec(s)) {
    				 result[count] = stack.pop();
    				 count++;
    			 }
    			 //then push on the stack its self after that
    			 stack.push(s);
    		 }
    		 
    	 }
    	 
         
     }
    // final house cleaning, if there is anything left in the stack pop it to the array
     while(!stack.isEmpty()) {
    	 result[count] =  stack.pop();
     }
     // send the post fix array to the method call
     return result; 
 } 
 
 
} 