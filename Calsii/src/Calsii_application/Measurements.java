package Calsii_application;
/**
 * <p> Title: Measurements. </p>
 * 
 * <p> Description: Measurements class  </p>
 * 
 *<p> Copyright: Venkatesh Bejjenki and Vikas Reddem  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki and Vikas Reddem 
 * 
 * @version 1.00	Initial Measurements converter
 * 
 */

public class Measurements {
	
	
	HelperUNumber help =new HelperUNumber();									// Creating a HelperUNumber class object 
	
	/**********
	 * This method acts as an interface between the SampleController and the individual measurement conversions. This method
	 * returns the computed units in the required measurement units
	 */
	public UNumber operationUnits(String measurement,String unit1,String value1,String unit2,String value2,String resultUnit,String operation){
		//		System.out.println("value1="+value1+" value2="+value2);
		//Creating UNumber object for value1 
		UNumber val1=new UNumber(help.getTrueVal(value1),help.getDotPoint(value1),true,Math.max(value1.length(), 10));
		//Creating UNumber object for value1
		UNumber val2=new UNumber(help.getTrueVal(value2),help.getDotPoint(value2),true,Math.max(value2.length(), 10));
		
		if(measurement.equals("Length")){										// Checking whether the given measurement is length or not
			
			Length l=new Length();												// Creating length object l
			System.out.println("length is called "+val1.toString()+" "+val2.toString());
		
			return l.length(unit1,val1,unit2,val2,resultUnit,operation);		// Calling the length method in Length class
			
		}
		else if(measurement.equals("Mass")){									// Checking whether the given measurement is Mass or not
			
			Mass m=new Mass();													// Creating Mass object m
			System.out.println("Mass is called "+val1.toString()+" "+val2.toString());
			
			return m.mass(unit1,val1,unit2,val2,resultUnit,operation);			// Calling the mass method in Mass class

		}
		else if(measurement.equals("Time")){									// Checking whether the given measurement is Time or not
			
			Time t=new Time();													// Creating Time object t
			System.out.println("Time is called "+val1.toString()+" "+val2.toString());
			
			return t.time(unit1,val1,unit2,val2,resultUnit,operation);			// Calling the time method in Time class
		}
		
		else if(measurement.equals("Current")){									// Checking whether the given measurement is Current or not
			
			Current c=new Current();											// Creating Current object c
			System.out.println("Current is called "+val1.toString()+" "+val2.toString());
			
			return c.current(unit1,val1,unit2,val2,resultUnit,operation);		// Calling the current method in Current class
		}
		
		else if(measurement.equals("Temperature")){								// Checking whether the given measurement is Temperature or not
			
			Temperature te=new Temperature();									// Creating Temperature object te
			System.out.println("Temperature is called "+val1.toString()+" "+val2.toString());
			
			return te.temperature(unit1,val1,unit2,val2,resultUnit,operation);  // Calling the current method in Temperature class
		}
		
		return null;															// Returns null if all of the above cases are false
	}



		

}
