package Calsii_application;

import java.util.Scanner;

/**
 * <p> Title: NewtonMethod. </p>
 * 
 * <p> Description: Newton-Raphson square root method </p>
 * 
 * <p> Copyright: Venkatesh Bejjenki © 2017 </p>
 * 
 * @author Venkatesh Bejjenki
 * 
 */


public class SquareRoot {
	/**********
	 * This method the computes values to  Square root of the given input using Newton Raphson Method. This method
	 * returns the computed Square Root in String format
	 */

	public String squareRoot(String str){	

		String n="";											// initialing string variable
		int dP=str.length();								
		for(int i=0;i<str.length();i++){						// To remove . and to find index of decimal point
			if(str.charAt(i)!='.')								// concat the input if the character is not '.'
				n+=str.charAt(i);
			else												// finding index number of character'.'
				dP=str.indexOf('.');
		}
		UNumber input=new UNumber(n,dP,true,20);				// object creation
		UNumber temp;
		UNumber two=new UNumber(2.0);

		UNumber newGuess=new UNumber(input);					 
		UNumber epslon=new UNumber(0.000001);					// Epsilon object creation
		newGuess.div(two);										// newGuess = newGuess/2.0
		UNumber oldGuess;										// oldGuess object creation
		do{
			oldGuess=new UNumber(newGuess);						// oldGuess stored in newGuess
			newGuess=new UNumber(input);						
			newGuess.div(oldGuess);								// newGuess = (1/2)(input/oldGuess)+oldGuess)
			newGuess.add(oldGuess);
			newGuess.div(two);
			temp=new UNumber(newGuess);							
			temp.sub(oldGuess);									// newGuess - oldGuess
			temp.abs();
			System.out.println(newGuess.toString());
		}
		while(temp.compareTo(epslon)>0);						//iterating while loop until the difference ,abs(newGuess - oldGuess) 
		System.out.println(newGuess.toString());				// is greater than epsilon

		System.out.println("\nThe computed square root is:");
		System.out.println(newGuess.toString());						// Display the result squared	
		return newGuess.toString();
	}
	public static void main(String args[]){
		Scanner sc =new Scanner(System.in);
		SquareRoot s=new SquareRoot();
		System.out.println("Enter a double value");
		String input;
		input=sc.nextLine();
		s.squareRoot(input);
	}

}
