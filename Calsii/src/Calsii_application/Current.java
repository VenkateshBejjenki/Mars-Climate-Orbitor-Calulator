package Calsii_application;

/**
 * <p> Title: Length </p>
 * 
 * <p> Description: Length conversion Measurements  </p>
 * 
 *<p> Copyright: Venkatesh Bejjenki and Vikas Reddem  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki and Vikas Reddem 
 * 
 * @version 1.00	Initial length conversions
 * 
 */

public class Current {

	UNumber value1,value2;   					      // declaring two values, two units and the result unit to be displayed
	String unit1,unit2,resultUnit;
	UNumber ampere=new UNumber(1000);
	UNumber milliAmpere=new UNumber(1);
	
	// method to find the result of computation on two perands of different units
	
	public UNumber current(String unit1,UNumber value1,String unit2,UNumber value2,String resultUnit,String operation){

		value1=convertCurrent(value1,unit1);							// Calling convertCurrent Method with value1 and unit1 as inputs
		value2=convertCurrent(value2,unit2);							// Calling convertMass Method with value2 and unit2as inputs
		UNumber temp=new UNumber(value1);								// Creating UNumber temp of value1 type
		if(operation.equals("+"))										// Checking whether the operation is '+' or not
			temp.add(value2);											// Adding value2 to temp
		else															// Else perform subtraction
			temp.sub(value2);											// Subtracting value2 from temp

		return convertResultCurrent(temp,resultUnit);
	}
	
	// method to convert the length of the result to required units
	/**********
	 * This method the computes values to required result units. This method
	 * returns the computed value  UNumber in result units
	 */
	public UNumber convertResultCurrent(UNumber value,String resultUnit){
		
		if(resultUnit.equals("Ampere")){    		
			value.div(ampere);
			return value;												// Returns the Amperes converted value
		}
		return value;													// Returns the Milliampere  value

	}


	/**********
	 * This method the converts the values to required centimeter units. This method
	 * returns the UNumber converted of Milliamperes Units
	 */

	public UNumber convertCurrent(UNumber value,String unit){
		
		if(unit.equals("Ampere")) {     			
			value.mpy(ampere);  										// converting the value from Ampere to Milliampere
		}
		return value;													// Returning the Milliampere value
	}
}
