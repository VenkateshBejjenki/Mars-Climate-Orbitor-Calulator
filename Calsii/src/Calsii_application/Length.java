package Calsii_application;
/**

 * <p> Title: Length </p>
 * 
 * <p> Description: Length Measurements conversion </p>
 * 
 *<p> Copyright: Venkatesh Bejjenki and Vikas Reddem  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki and Vikas Reddem 
 * 
 * @version 1.00	Initial Length conversions
 * 
 */


public class Length {
	
	
	HelperUNumber help =new HelperUNumber();
	UNumber value1,value2;
	String unit1,unit2,measurement,resultUnit;
	UNumber kilometer=new UNumber("100000",6,true,10);
	UNumber mile=new UNumber("160934",6,true,10);
	UNumber meter=new UNumber("100",3,true,10);
	UNumber foot=new UNumber("3048",2,true,10);
	UNumber inch=new UNumber("254",1,true,10);
	UNumber centimeter=new UNumber("1",1,true,10);
	
	/**********
	 * This method computes the value1 and value2 based on the operation. This method
	 * returns the Length computed value in result units
	 */
	public UNumber length(String unit1,UNumber value1,String unit2,UNumber value2,String resultUnit,String operation){
		
		value1=convertLength(value1,unit1);										// Calling convertLength Method with value1 and unit1 as inputs
		value2=convertLength(value2,unit2);										// Calling convertLength Method with value2 and unit2 as inputs
		UNumber temp=new UNumber(value1);										// Creating UNumber temp of value1 type
		if(operation.equals("+"))												// Checking whether the operation is '+' or not
			temp.add(value2);													// Adding value2 to temp
		else if(operation.equals("-"))											// Checking whether the operation is '-' or not
			temp.sub(value2);													// Subtracting value2 from temp
		else if(operation.equals("*"))											// Checking whether the operation is '*' or not
			temp.mpy(value2);													// Multiplying value2 to temp
		else																	// Else the operation is '/'
			temp.div(value2);													// Dividing temp with value2 
		
		return convertResultLength(temp,resultUnit);
	}
	
	/**********
	 * This method the computes values to required result units. This method
	 * returns the computed value  UNumber in result units
	 */
	public UNumber convertResultLength(UNumber value,String resultUnit) {
		
		if(resultUnit.equals("Centimeters")){
			return value;														// Returns the Centimeter converted value
		}
		else if(resultUnit.equals("Inches")){
			
			value.mpy(new UNumber(0.393701));
			return value;														// Returns the Inches converted value
		}
		else if(resultUnit.equals("Feets")){
			
			value.mpy(new UNumber(0.0328084));
			return value;														// Returns the Feet converted value
		}
		else if(resultUnit.equals("Meters")){
			
			value.mpy(new UNumber(0.01));
			return value;														// Returns the Meters converted value
		}
		else if(resultUnit.equals("Kilometers")){
			
			value.mpy(new UNumber(0.00001));
			return value;														// Returns the Kilometers converted value
		}
		else{
			value.mpy(new UNumber(0.0000062137));
			return value;
		}
		
	}

	/**********
	 * This method the converts the values to required centimeter units. This method
	 * returns the UNumber converted of Centimeter Units
	 */

	public UNumber convertLength(UNumber value,String unit){
		
		if(unit.equals("Centimeters")){
			
			value.mpy(centimeter);    											// converting the value from centimeter to centimeter 
		}
		else if (unit.equals("Inches")){
			
			value.mpy(inch);									    			// converting the value from Inches to centimeter 
		}
		else if (unit.equals("Feets")){
			
			value.mpy(foot);    												// converting the value from Foot to centimeter 
		}
		else if (unit.equals("Meters")){
			
			value.mpy(meter);    												// converting the value from Meters to centimeter 
		}
		else if (unit.equals("Kilometers")){
			
			value.mpy(kilometer);    											// converting the value from Kilometers to centimeter 
		}
		else{
			value.mpy(mile);    												// converting the value from Mile to centimeter 
		}
		
		return value;
	}
}
