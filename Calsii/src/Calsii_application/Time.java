/**
 * <p> Title: Time </p>
 * 
 * <p> Description: Time Measurements conversion  </p>
 * 
 * <p> Copyright: Venkatesh Bejjenki and Vikas Reddem  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki and Vikas Reddem 
 * 
 * @version 1.00	Initial Time conversions
 * 
 */
package Calsii_application;

public class Time {
	
	
	HelperUNumber con=new HelperUNumber();
	UNumber value1,value2;
	String unit1,unit2,measurement,resultUnit;
	UNumber year=new UNumber("31540000",8,true,10);
	UNumber day=new UNumber("86400",5,true,10);
	UNumber hour=new UNumber("3600",4,true,10);
	UNumber minuate=new UNumber("60",2,true,10);
	UNumber second=new UNumber("1",1,true,10);

	/**********
	 * This method computes the value1 and value2 based on the operation. This method
	 * returns the Time computed value in result units
	 */	
	
	public UNumber time(String unit1,UNumber value1,String unit2,UNumber value2,String resultUnit,String operation){
		
		value1=convertTime(value1,unit1);										// Calling convertTime Method with value1 and unit1 as inputs
		value2=convertTime(value2,unit2);										// Calling convertTime Method with value2 and unit2 as inputs
		UNumber temp=new UNumber(value1);										// Creating UNumber temp of value1 type
		if(operation.equals("+"))												// Checking whether the operation is '+' or not
			temp.add(value2);													// Adding value2 to temp
		else if(operation.equals("-"))											// Checking whether the operation is '-' or not
			temp.sub(value2);													// Subtracting value2 from temp
		else if(operation.equals("*"))											// Checking whether the operation is '*' or not
			temp.mpy(value2);													// Multiplying value2 to temp
		else																	// Else the operation is '/'
			temp.div(value2);													// Dividing temp with value2 
		
		return convertResultTime(temp,resultUnit);
	}
	/**********
	 * This method the computes values to required result units. This method
	 * returns the computed value  UNumber in result units
	 */
	public UNumber convertResultTime(UNumber value,String resultUnit) {
		
		if(resultUnit.equals("Seconds")){
			
			return value;														// Returns the Seconds converted value
		}
		else if(resultUnit.equals("Minutes")){
			
			value.mpy(new UNumber(0.016667));
			return value;														// Returns the Minutes converted value
		}
		else if(resultUnit.equals("Hours")){
			
			value.mpy(new UNumber(0.000277778));
			return value;														// Returns the Hours converted value
		}
		else if(resultUnit.equals("Days")){
			
			value.mpy(new UNumber(0.000011574));
			return value;														// Returns the Days converted value
		}
		else{
			
			value.mpy(new UNumber(0.00000003171));
			return value;														// Returns the Years converted value
		}
		
	}


	/**********
	 * This method the converts the values to required centimeter units. This method
	 * returns the UNumber converted of Seconds Units
	 */

	public UNumber convertTime(UNumber value,String unit){
		
		if(unit.equals("Seconds")){
			value.mpy(second);  												// converting the value from Seconds to Seconds 
		}
		else if (unit.equals("Minutes")){
			value.mpy(minuate);  												// converting the value from Minutes to Seconds 
		}
		else if (unit.equals("Hours")){
			value.mpy(hour);  													// converting the value from Hours to Seconds 
		}
		else if (unit.equals("Days")){
			value.mpy(day);  													// converting the value from Days to Seconds 
		}
		else{
			value.mpy(year);  													// converting the value from year to Seconds 
		}
		return value;
	}


}
