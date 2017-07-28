package Calsii_application;

/**
 * <p> Title: FloatingPointRecognizer Class. </p>
 * 
 * <p> Description: A demonstration application showing how code can be systematically created
 *     using a finite state machine diagram.  This code will serves as a baseline for class
 *     exercises. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2016 </p>
 * 
 * @author Lynn Robert Carter
 * @modifer Venkatesh Bejjenki
 * 
 * @version 2.00	2016-12-10 Enhance documentation to make the linkage to the FSM more clear 
 * 
 */

public class FPR {
	/*****
	 * Process the remainder of a String to deal with the exponent portion of a floating point number
	 * 
	 * @param ndx	- An int that specifies the starting position within the string
	 * @param in	- A String the contains the floating point number
	 * @return		- A String that indicates whether or not the string was recognized
	 */
	
	static boolean deci=false;
	private static String jumpToState6(int ndx, String in) {
		// Part 3
		//return false;	// This is a stub that will be completed in a future exercise.
		if (ndx ==in.length())
			//returns this message if end of input string is encountered
			return "end of string encountered before final state";
		
		char ch = in.charAt(ndx++);
		if (ch == '+' ||ch == '-'){
			//state 7
			//Goes to state 7 if we encounter + or -
			return jumpToState7(ndx, in);
		}
		else if(ch >='0' && ch <='9'){
			//state 8
			//Goes to state 7 if we encounter 0 to 9 digits
			return jumpToState8(ndx, in);
		}
		else{ 
			//returns error with the character index
			return "entered invalid character: "+ch+" at index "+ndx;
		}

	}
	// state 7
	//Checking the input for empty string and no from 0 to 9
	private static String jumpToState7(int ndx, String in) {
		if (ndx ==in.length())
			return "end of string encountered before final state";
		
		char ch = in.charAt(ndx++);
		if (ch >= '0' && ch <= '9') {
			//State 8
			return jumpToState8(ndx, in);
			
		}
		//returns error if invalid input is entered
		return "entered invalid character: "+ch+" at index "+ndx;
		
	}
	//state8
	//Checking the input for empty string and no from 0 to 9
	private static String jumpToState8(int ndx, String in) {
		//This code checks for end of the input string
		if (ndx >= in.length()) 
			return "";
		
		// This code checks for the State 8 - a single digit 0 - 9

		char ch = in.charAt(ndx++);
		while (ch >= '0' && ch <= '9') {
			if (ndx >= in.length()) 
				return "";
			ch = in.charAt(ndx++);
		}
		//returns error if invalid input is entered
		return "entered invalid character: "+ch+" at index "+ndx;
	}

	/*****
	 * Process a string to see if it conforms to a definition of a floating point number given in the
	 * form of a diagram using a finite state machine
	 * 
	 * @param in	- A String that holds the proposed floating point number
	 * @return		- A String that indicates whether or not the entire string conforms to definition
	 */
	public static String floatingPointRecognizer(String in){
		// State 0
		// There are four situations for State 0:
		//		1) no input, 
		//		2) a single digit 0 - 9, 
		//		3) decimal point,
		//		4) some other character

		// This code checks for the first of the four situations of State 0 - no input
		if (in.length() <= 0)
			return "Please enter something";

		// This code checks for the second of State 0 - a single digit 0 - 9
		int ndx = 0;
		char ch = in.charAt(ndx++);
		if (ch >= '0' && ch <= '9') {
			// Part 1
			// State 1

			// There are five situations for State 1:
			//		1) no more characters,
			//		2) sequence of zero or more digits 0 - 9, 
			//		3) decimal point,
			//		4) letter 'e' or 'E'
			//		5) some other character

			// This code checks for the first of the four situations of State 1 - no input
			if (ndx >= in.length()) 
				return "";

			// This code checks for the second of State 1 - sequence of zero or more digits 0 - 9
			ch = in.charAt(ndx++);
			while (ch >= '0' && ch <= '9') {
				if (ndx >= in.length()) 
					return "";
				ch = in.charAt(ndx++);
			}

			// This code checks for the third of State 1 - decimal point
			if (ch == '.') {
				deci=true;
				// State 2

				// There are four situations for state 2
				//		1) no more characters
				//		2) a single digit 0 - 9
				//		3) letter 'e' or 'E'
				//		4) some other character

				// This code checks for the first of the four situations for State 2 - no more characters
				if (ndx >= in.length())
					return "Floating point number can not end with '.' ";

				ch = in.charAt(ndx++);
				//Checks the second occurrence of '.'
				if (ch=='.') {
					return "input can not contain multiple decimal points";
					
				}
				// This code checks for the second - a single digit 0 - 9

				if (ch >= '0' && ch <= '9') {
					// State 3

					// There are four situations for state 3
					//		1) no more characters,
					//		2) sequence of zero or more digits 0 - 9,
					//		3) letter 'e' or 'E',
					//		4) some other character

					// This code deals with the first of the four situations of State 3 - no more characters
					if (ndx >= in.length())
						return "";

					ch = in.charAt(ndx++);		
					//second occurrence of '.'
					if (ch=='.') {
						return "input can not contain multiple decimal points";
						
					}
					// This code deals with the second of State 3 - sequence of zero or more digits 0 - 9

					while (ch >= '0' && ch <= '9') {
						if (ndx >= in.length()) 
							return "";
						ch = in.charAt(ndx++);
						if(ch=='.') return "input can not contain multiple decimal points";   //checking second occurrence of '.'
					}

					// This code deals with the third of State 3 - letter 'e' or 'E' 
					if (ch == 'e' || ch == 'E') {
						return jumpToState6(ndx, in);
					}

					// This code deals with the fourth of State 3 - some other character
					else
						return "entered invalid character: "+ch+" at index "+ndx;
				}

				// Continuation of State 2

				// This code checks for the fourth of State 2 - the character 'e' or 'E' 
				
				else if (ch == 'e' || ch == 'E') {
					return "float can not have e/E followed by decimal '.'";
				}

				// Continuation of State 2

				// This code deals with the fourth of State 2 - some other character
				else
					return "entered invalid character: "+ch+" at index "+ndx;
			}

			// Continuation of State 1

			// This code checks for the fourth of State 1 - the character 'e' or 'E'
			else if (ch == 'e' || ch == 'E') {
				return jumpToState6(ndx, in);
				//return "float can not have e/E followed by decimal '.'";
			}
			// Continuation of State 1

			// This code deals with the fifth of State 1h - some other character
			else
				return "entered invalid character: "+ch+" at index "+ndx;
		}

		// continuation of State 0
		//
		// This code checks for the third of State 0 - decimal point
		else if (ch == '.') {
			deci=true;
			// Part 2
			// State 4

			// There are three situations for State 4
			//		1) no more characters,
			//		2) a single digit 0 - 9,
			//		3) some other character

			// This code checks for the first of the three situations of State 4 - no more characters 
			if (ndx >= in.length()) 
				return "end of string encountered before final state";

			// This code checks for the second of State 4 - a single digit 0 - 9 
			ch = in.charAt(ndx++);
			if(ch=='.') return "input can not contain multiple decimal points";   //checking second occurrence of '.'

			if (ch >= '0' && ch <= '9') {
				// State 5

				// There are four states for State 5
				//		1) no more characters,
				//		2) sequence of zero or more digits 0 - 9,
				//		3) letter 'e' or 'E',
				//		4) some other character

				// This code checks for the first of the three situations of State 5 - no more characters 
				if (ndx >= in.length())
					return "";

				// This code checks for the second of State 5 - a sequence of zero or more digits 0 - 9
				ch = in.charAt(ndx++);
				if(ch=='.') return "input can not contain multiple decimal points";   //checking second occurrence of '.'

				while (ch >= '0' && ch <= '9') {
					if (ndx >= in.length()) 
						return "";
					ch = in.charAt(ndx++);
					if(ch=='.') return "input can not contain multiple decimal points";   //checking second occurrence of '.'

				}

				// This code checks for the third of State 5 - letter 'e' or 'E'
				if (ch == 'e' || ch == 'E') {
					return jumpToState6(ndx, in);
				}

				// This code handles the fourth of State 5 - some other character
				else
					return "entered invalid character: "+ch+" at index "+ndx;
			}

			// Continuation of State 4

			// This code handles the third of State 4 - some other character
			else
				return "entered invalid character: "+ch+" at index "+ndx;
		}

		// Continuation of State 0
		//
		// This code handles the fourth of State 0 - some other character
		else
			return "entered invalid character: "+ch+" at index "+ndx;
	}
}
