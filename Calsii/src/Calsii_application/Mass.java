package Calsii_application;
/**
 * <p> Title: Mass </p>
 * 
 * <p> Description: Mass Measurements conversion </p>
 * 
 * <p> Copyright: Venkatesh Bejjenki and Vikas Reddem  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki and Vikas Reddem 
 * 
 * @version 1.00	Initial mass conversions
 * 
 */

public class Mass {
	
	
	HelperUNumber con=new HelperUNumber();
	UNumber value1,value2;
	String unit1,unit2,measurement,resultUnit;
	UNumber tonne=new UNumber("1000000",7,true,10);
	UNumber pound=new UNumber("453592",3,true,10);
	UNumber kilogram=new UNumber("1000",4,true,10);
	UNumber gram=new UNumber("1",1,true,10);
	

	/**********
	 * This method computes the value1 and value2 based on the operation. This method
	 * returns the Mass computed value in result units
	 */
	
	public UNumber mass(String unit1,UNumber value1,String unit2,UNumber value2,String resultUnit,String operation){
		
		value1=convertMass(value1,unit1);										// Calling convertMass Method with value1 and unit1 as inputs
		value2=convertMass(value2,unit2);										// Calling convertMass Method with value2 and unit2 as inputs
		UNumber temp=new UNumber(value1);										// Creating UNumber temp of value1 type
		if(operation.equals("+"))												// Checking whether the operation is '+' or not
			temp.add(value2);													// Adding value2 to temp
		else if(operation.equals("-"))											// Checking whether the operation is '-' or not
			temp.sub(value2);													// Subtracting value2 from temp
		else if(operation.equals("*"))											// Checking whether the operation is '*' or not
			temp.mpy(value2);													// Multiplying value2 to temp
		else																	// Else the operation is '/'
			temp.div(value2);													// Dividing temp with value2 
		
		return convertResultMass(temp,resultUnit);
	}
	/**********
	 * This method the computes values to required result units. This method
	 * returns the computed value  UNumber in result units
	 */
	public UNumber convertResultMass(UNumber value,String resultUnit) {
		if(resultUnit.equals("Grams")){
			return value;														// Returns the Grams converted value
		}
		else if(resultUnit.equals("Kilograms")){
			value.mpy(new UNumber(0.001));
			return value;														// Returns the Kilograms converted value
		}
		else if(resultUnit.equals("Pounds")){
			value.mpy(new UNumber(0.00220462));
			return value;														// Returns the Pounds converted value
		}
		else{
			value.mpy(new UNumber(0.000001));						
			return value;														// Returns the Tones converted value
		}
	}


	/**********
	 * This method the converts the values to required centimeter units. This method
	 * returns the UNumber converted of grams Units
	 */

	public UNumber convertMass(UNumber value,String unit){
		
		if(unit.equals("Grams")){
			value.mpy(gram);   												// converting the value from Grams to Grams 
		}	
		else if (unit.equals("Kilograms")){
			
			value.mpy(kilogram);   											// converting the value from Kilograms to Grams 
		}
		else if (unit.equals("Pounds")){
			
			value.mpy(pound);   											// converting the value from Pounds to Grams 
		}
		else{
			value.mpy(tonne);   											// converting the value from Tonne to Grams 
		}
		return value;
	}


}

