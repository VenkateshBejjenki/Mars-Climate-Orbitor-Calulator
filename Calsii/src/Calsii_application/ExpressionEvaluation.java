package Calsii_application;


import java.util.Stack;

/**
 * <p> Title: Expression Evaluation </p>
 * 
 * <p> Description: Evaluation class for the calculator application </p>
 * 
 * <p> Copyright: Venkatesh Bejjenki  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki 
 * @Credits GeeksforGeeks 
 * 
 * @version 1.00	Initial baseline
 * 
 */

public class ExpressionEvaluation{
	HelperUNumber help =new HelperUNumber();
	private boolean sign=true;


	//Calculates the power of the operands
	/**********
	 * This method calculates the power of the given no with the associated power. This method
	 * returns the value in string format
	 */
	public String power(String in){

		int index=in.indexOf('^');											// Storing the index value
		String a=in.substring(0, index);									// Storing the base value
		String b=in.substring(index+1);										// Storing the power value
		b=help.getInteger(b);
		if(b.charAt(0)=='0'){												// Return '1' when power is '0'
			return "1.0";
		}
		// creating base object of UNumber type
		UNumber base = new UNumber(help.getTrueVal(a),help.getDotPoint(a),true,Math.max(a.length(),10));  

		UNumber temp = new UNumber(base);									// creating the duplicate for base 
		UNumber one=new UNumber(1);											// Creating UNumber of one
		// Creating power object of UNumber type
		UNumber  power= new UNumber(help.getTrueVal(b),b.length(),true,Math.max(b.length(),10));

		power.sub(one);														// Subtracting one from power
		// Running loop to get base multiplied buy it self power no of times
		while(power.compareTo(one)>0)		{
			System.out.println("power= "+power.toString()+ "temp= "+temp.toString());
			temp.mpy(base);
			power.sub(one);
		}

		return temp.toString();												// Returning the string value
	}

	//Calculates the modulus of the operands
	/**********
	 * This method calculates the power of the given no with the associated power. This method
	 * returns the value in string format
	 */
	public String mod(String in) {

		String[] parts = in.split("mod");
		String a = parts[0]; 
		String b = parts[1]; 
		// creating part1 object of UNumber type
		System.out.println("a="+a+"b="+b);
		UNumber part1 = new UNumber(help.getTrueVal(a),help.getDotPoint(a),true,Math.max(a.length(),10));
		// creating part2 object of UNumber type
		UNumber part2 = new UNumber(help.getTrueVal(b),help.getDotPoint(b),true,Math.max(b.length(),10));
		// creating temp object of part1 UNumber type
		//System.out.println("part1="+part1.toString()+"part2="+part2.toString());
		UNumber temp = new UNumber(part1);
		temp.div(part2);															// Dividing the temp by part2
		//System.out.println("temp div = "+temp.toString());
		String s = noBeforeDecimal(temp.toDecimalString());							// Calling removeNoBeforeDecimal
		//System.out.println("s="+s);
		UNumber temp2 = new UNumber(help.getTrueVal(s),help.getDotPoint(s	),true,Math.max(s.length(),10));
		temp2.mpy(part2);
		part1.sub(temp2);
		
		return part1.toString();
		
	}

	//Returns the value removing digits before decimal point
	/**********
	 * This method is an helper function for the above method mod(). This method
	 * returns the value in string format starting from decimal value
	 */
	public String noBeforeDecimal(String s) {
		for(int i = 0; i<s.length();i++)
			if(s.charAt(i)=='.')
				return s.substring(0,i);										// Returns string from zero character to '.' is encountered
		return "0";

	}
	
	/**********
	 * This method is an helper function for the below method evaluate. This method
	 * returns the value in string format after adding zeros
	 */
	public String addSpaces(String in){

		String result="";
		for(int i=0;i<in.length();i++){
			if(!isDigit(in.charAt(i))){
				result+=" "+in.charAt(i)+" ";								// Adding spaces between the operator and operands
			}				
			else
				result+=in.charAt(i);										// Adding to result directly
		}
		return result;
	}

	/**********
	 * This method is an helper function for the below method evaluate. This method
	 * returns boolean value whether the given character is digit or not
	 */
	public boolean isDigit(char c){
		if(c=='.' ||c=='E'|| c>='0' && c<='9'){								// Checking for digits, E and dot characters
			return true;
		}
		return false;
	}	

	/**********
	 * This method is used to evaluate the given expression. This method uses character tokens array to store each 
	 * character and performs computations based on the operators and operands. This method return the UNumber value.
	 * returns the value in string format after adding zeros
	 */
	public String evaluate(String expression){
		try{
			if(expression.charAt(0)=='+')
				expression=expression.substring(1);
		expression="("+expression+")";
		expression=addSpaces(expression);									// Calling addSpaces method
		System.out.println("expression="+expression);
		char[] tokens = expression.toCharArray();							// Creating token String array from the input expression
		// Stack for numbers: 'values'

		Stack<UNumber> values = new Stack<UNumber>();						// Stack for numbers: 'values'
		
		Stack<Character> ops = new Stack<Character>();						// Stack for Operators: 'ops'

		for (int i = 0; i < tokens.length; i++)		{

			if (tokens[i] == ' ')											// Current token is a whitespace, skip it
				continue;
			// Current token is a number, push it to stack for numbers
			if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i]=='.' || tokens[i]=='E'){

				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number

				while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i]=='.'))
					sbuf.append(tokens[i++]);
				String n=sbuf.toString();
				//				n = removeStartingZeros(n);
				UNumber un=new UNumber(help.getTrueVal(n),help.getDotPoint(n),sign,Math.max(n.length(), 20));
				sign=true;
				values.push(un);
			}
			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);
			// Closing brace encountered, solve entire brace

			else if (tokens[i] == ')'){
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}
			// Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'){
				
				if(tokens[i]=='-' && (i-3>=0) && (values.empty() || tokens[i-3]=='/' || tokens[i-3]=='*' || tokens[i-3]=='(')){
					sign=false;
					continue;
				}
				// While top of 'ops' has same or greater precedence to current

				// token, which is an operator. Apply operator on top of 'ops'

				// to top two elements in values stack

				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))

					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
			
				ops.push(tokens[i]);										// Push current token to 'ops'.
			}
		}
		// Entire expression has been parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
		// Top of 'values' contains result, return it
		String temp=values.pop().toString();
		System.out.println("temp="+temp);
		return temp;
		}
		catch (Exception e) {
			return "Invalid input";
		}
	}

	/**********
	 * This method is an helper function for the above method evaluate. This method
	 * returns the string removing the heading number before decimal
	 */
	private String removeStartingZeros(String n) {

		for(int i = 0; i < n.length(); i++ ){
			if(n.charAt(i)!='0'){
				return n.substring(i);
			}
		}
		return 0+"";
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	/**********
	 * This method is an helper function for the above method evaluate. This method
	 * returns boolean value whether the given character is among the operators or parenthesis or not
	 */
	public boolean hasPrecedence(char op1, char op2){

		if (op2 == '(' || op2 == ')')										// Returns false if op2 is either '(' or ')'
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;													// Returns false if op2 is among '*', '/', '+', '-'
		else
			return true;

	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	/**********
	 * This method is an helper function for the above method evaluate. This method
	 * returns UNumber value after applying an operator 'op' on 'a' and 'b'.
	 */
	public UNumber applyOp(char op, UNumber b, UNumber a){
		switch (op){

		case '+': a.add(b); return a;										// For '+' case 'b' is added to 'a' 
		case '-': a.sub(b); return a;										// For '-' case 'b' is subtracted from to 'a'
		case '*': a.mpy(b);													// For '*' case 'b' is multiplied to 'a'
				  //UNumber.normalize(a);										// Normalizing the a value
				  return a;
		case '/': a.div(b); return a;										// For '/' case 'a' is divided by 'b'
		}
		
		return null;

	}

}

